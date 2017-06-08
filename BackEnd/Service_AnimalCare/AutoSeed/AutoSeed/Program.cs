using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoSeed
{
    class Program
    {
        //SELECT a.*, b.machinhanh FROM service_management_drinksmile.bo a, service_management_drinksmile.chuongtrai b where a.machuong = b.machuong;
        public static List<dynamic> Cows { get; set; }
        public static Dictionary<dynamic, double> Statistic { get; set; }

        //=====

        static void AddMilk(dynamic key, double value)
        {
            if (Statistic.ContainsKey(key))
            {
                Statistic[key] += value;
            }
            else
            {
                Statistic.Add(key, value);
            }
        }

        static void LoadCowData()
        {
            using (var reader = new StreamReader("cow-data.json"))
            {
                Cows = JsonConvert.DeserializeObject<List<dynamic>>(reader.ReadToEnd());
            }
        }

        static void WriteStatisticData()
        {
            using (var writer = new StreamWriter("statistic-data.json"))
            {
                writer.WriteLine(JsonConvert.SerializeObject(Statistic));
            }
        }

        static string createId(string areaid)
        {
            try
            {
                if (CounterRepository.updateCounter(areaid, "index_chamsoc"))
                {
                    var index = CounterRepository.getIndex(areaid, "index_chamsoc");
                    if (index != -1)
                    {
                        return string.Format("{0}{1}{2:D10}", areaid, "CS", index);
                    }
                    return null;
                }
            }
            catch (Exception ex) { }
            return null;
        }

        static void Main(string[] args)
        {
            Console.WriteLine("Setup variables...");
            Statistic = new Dictionary<dynamic, double>();
            Console.WriteLine("Wait for ready...");
            LoadCowData();
            //try
            //{
                Console.WriteLine("Clearing old data...");
                DataProvider.ExecuteNonQuery("delete chamsoc");
                DataProvider.ExecuteNonQuery("delete tinhtrangbo");
                DataProvider.ExecuteNonQuery("update counter set index_chamsoc = 0");
                Console.WriteLine("Seeding data...");
                foreach (var eachPC in DataProvider.ExecuteReader<dynamic>((SqlDataReader row) => {
                    return new
                    {
                        id = row.GetValueDefault<string>(0),
                        userid = row.GetValueDefault<string>(1),
                        barnid = row.GetValueDefault<string>(2),
                        start = row.GetValueDefault<DateTime>(3),
                        end = row.GetValueDefault<DateTime>(4),
                        daysOfWeek = row.GetValueDefault<string>(5).Replace("CN", "1"),
                    };
                }, "select maphancong, manv, machuong, ngaybatdau, ngayketthuc, ngaytrongtuan from phancong")){

                    string areaId = eachPC.userid.Split(new string[] { "NV" }, 2, StringSplitOptions.RemoveEmptyEntries)[0];
                    DateTime run = new DateTime(eachPC.start.Year, eachPC.start.Month, eachPC.start.Day);

                    while (run.CompareTo(DateTime.Now) == -1 && run.CompareTo(eachPC.end) == -1)
                    {
                        if (eachPC.daysOfWeek.Contains(((int)run.DayOfWeek + 1).ToString()))
                        {
                            foreach (var cow in Cows.Where(x => x.machuong == eachPC.barnid && x.daxoa == "0"))
                            {
                                string newId = createId(areaId);
                                bool milk = false;
                                double milkValue = 0d;
                                if (new Random().NextDouble() > 0.5)
                                {
                                    milk = true;
                                    milkValue = (double)(new Random().Next(500, 1000)) / 100d;
                                    AddMilk(cow.machinhanh, milkValue);
                                }

                                DataProvider.ExecuteNonQuery(
                                    string.Format("insert into chamsoc(machamsoc, ngayghinhan, tinhtrangcongviec, luongsua, dachoan, dadonvesinh, davatsua, maphancong, mabo)" + 
                                    " values ('{0}', '{1}', N'{2}', {3}, '{4}', '{5}', '{6}', '{7}', '{8}')", 
                                    newId, run, "Đã hoàn thành.", milkValue, true, true, milk, eachPC.id, cow.mabo));

                                //loop 2-4 times
                                DateTime a = new DateTime(run.Year, run.Month, run.Day, 7, 0, 0);
                                for (int i = 0; i < new Random().Next(2, 4); i++)
                                {
                                    a = a.Add(new TimeSpan(new Random().Next(1, 2), new Random().Next(1, 59), new Random().Next(1, 59)));
                                    if ((int)DataProvider.ExecuteScalar(string.Format("select count(mabo) from tinhtrangbo a, chamsoc b where a.machamsoc = b.machamsoc and b.mabo = '{0}'", cow.mabo)) > 0)
                                    {
                                        DataProvider.ExecuteNonQuery(
                                            string.Format("insert into tinhtrangbo(machamsoc, thoigianghinhan, cannang, chieucao)" +
                                            " values ('{0}', '{1}', {2}, {3})",
                                            newId, a, 
                                            (double)DataProvider.ExecuteScalar(string.Format("select max(a.cannang) from tinhtrangbo a, chamsoc b where a.machamsoc = b.machamsoc and b.mabo = '{0}'", cow.mabo)) + new Random().NextDouble() > 0.5 ? new Random().Next(40, 120) : 0,
                                            (double)DataProvider.ExecuteScalar(string.Format("select max(a.chieucao) from tinhtrangbo a, chamsoc b where a.machamsoc = b.machamsoc and b.mabo = '{0}'", cow.mabo)) + new Random().NextDouble() > 0.5 ? new Random().Next(0, 2) : 0));
                                    }
                                    else
                                    {
                                        DataProvider.ExecuteNonQuery(
                                            string.Format("insert into tinhtrangbo(machamsoc, thoigianghinhan, cannang, chieucao)" +
                                            " values ('{0}', '{1}', {2}, {3})",
                                            newId, a, 
                                            (double)(new Random().Next(9000, 13000)) / 100d, 
                                            (double)(new Random().Next(120, 140)) / 100d));
                                    }
                                }
                            }
                        }
                        run = run.AddDays(1);
                    }
                }
            //}
            //catch (Exception ex)
            //{
            //    throw ex;
            //}
            Console.WriteLine("Wait for write statistic to file...");
            WriteStatisticData();
            Console.WriteLine("Finish! Press any to exit...");
            Console.Read();
        }
    }
}
