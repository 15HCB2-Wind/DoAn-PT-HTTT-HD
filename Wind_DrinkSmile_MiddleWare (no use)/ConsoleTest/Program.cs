using DataAccess.Repositories;
using DomainData;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleTest
{
    class Program
    {
        static void Main(string[] args)
        {
            //  test DataAccess
            {
                foreach (var item in PhanQuyenRepository.GetInstance().GetAll())
                {
                    Console.WriteLine(item.TenPhanQuyen);
                }
            }

            //  test DataService
            {
                //  Get
                //var result = new HttpClient().GetAsync("http://localhost:29370/hcm/nhanvien/getall").Result;
                //Console.Write(result.Content.ReadAsStringAsync().Result);

                //  Post
                //var test = new NhanVien();
                //var result = new HttpClient().PostAsync("http://localhost:29370/hcm/nhanvien/insert", new StringContent(JsonConvert.SerializeObject(test), Encoding.UTF8, "application/json")).Result;
                //Console.Write(result.Content.ReadAsStringAsync().Result);

                //  Put
                //var test = new NhanVien();
                //var result = new HttpClient().PutAsync("http://localhost:29370/hcm/nhanvien/update", new StringContent(JsonConvert.SerializeObject(test), Encoding.UTF8, "application/json")).Result;
                //Console.Write(result.Content.ReadAsStringAsync().Result);

                //  Delete
                //var result = new HttpClient().DeleteAsync("http://...").Result;
                //Console.Write(result.Content.ReadAsStringAsync().Result);
            }

            //  pause
            Console.Read();
        }
    }
}
