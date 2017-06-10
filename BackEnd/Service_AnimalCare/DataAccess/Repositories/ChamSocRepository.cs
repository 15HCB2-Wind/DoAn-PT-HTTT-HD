using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;

namespace DataAccess.Repositories
{
    public class ChamSocRepository : DataAccessOrigin<ChamSoc>
    {
        public static bool createIDAssignment(ChamSoc cs)
        {
            try
            {
                if (CounterRepository.updateCounter("index_chamsoc"))
                {
                    var index = CounterRepository.getIndex("index_chamsoc");
                    if (index != -1)
                    {
                        cs.MaChamSoc = string.Format("{0}{1}{2:D10}", Configs.AREA_ID, "CS", index);
                        return true;
                    }
                    return false;
                }
            }
            catch (Exception ex) { }
            return false;
        }

        public static int Insert(ChamSoc cs)
        {
            //thêm mới cham soc
            int result = -1;

            if (createIDAssignment(cs))
            {

                try
                {
                    result = DataProvider.ExecuteNonQuery(string.Format("insert into ChamSoc(MaChamSoc,NgayGhiNhan,TinhTrangCongViec,LuongSua,DaChoAn,DaDonVeSinh,DaVatSua,MaPhanCong,MaBo) values ('{0}', '{1}', N'{2}', {3}, '{4}', '{5}','{6}','{7}','{8}')", cs.MaChamSoc, cs.NgayGhiNhan, cs.TinhTrangCongViec, cs.LuongSua, cs.DaChoAn, cs.DaDonVeSinh, cs.DaVatSua, cs.MaPhanCong, cs.MaBo));
                }
                catch (Exception)
                {
                    return result;
                }
            }
            return result;
        }

        public static int Update(ChamSoc cs)
        {
            //update cham soc
            int result = -1;
            {
                try
                {
                    result = DataProvider.ExecuteNonQuery(string.Format("update ChamSoc set NgayGhiNhan = '{1}',TinhTrangCongViec = N'{2}',LuongSua = {3},DaChoAn = '{4}',DaDonVeSinh = '{5}',DaVatSua = '{6}',MaPhanCong = '{7}',MaBo = '{8}' where MaChamSoc = '{0}'", cs.MaChamSoc, cs.NgayGhiNhan, cs.TinhTrangCongViec, cs.LuongSua, cs.DaChoAn, cs.DaDonVeSinh, cs.DaVatSua, cs.MaPhanCong, cs.MaBo));
                }
                catch (Exception)
                {
                    return result;
                }
            }
            return result;
        }

        public static ChamSoc IsExistsChamSoc(ChamSoc cs)
        {
            ChamSoc result = null;
            try
            {
                result = DataProvider.ExecuteReaderOne((SqlDataReader row) =>
                {
                    return new ChamSoc()
                    {
                        MaChamSoc = row.GetValueDefault<string>(0),
                        NgayGhiNhan = row.GetValueDefault<DateTime>(1),
                        TinhTrangCongViec = row.GetValueDefault<string>(2),
                        LuongSua = row.GetValueDefault<float>(3),
                        DaChoAn = row.GetValueDefault<bool>(4),
                        DaDonVeSinh = row.GetValueDefault<bool>(5),
                        DaVatSua = row.GetValueDefault<bool>(6),
                        MaPhanCong = row.GetValueDefault<string>(7),
                        MaBo = row.GetValueDefault<string>(8)
                    };
                }, string.Format("select * from ChamSoc where maphancong = '{0}' and mabo ='{1}' and ngayghinhan ='{2}'", cs.MaPhanCong, cs.MaBo, cs.NgayGhiNhan));
            }
            catch (Exception)
            {
                return result;
            }
            return result;
        }

        public static List<ChamSoc> GetListChamSocFromPhanCong(PhanCong pc)
        {
            List<ChamSoc> result = null;
            try
            {
                result = DataProvider.ExecuteReader((SqlDataReader row) =>
                {
                    return new ChamSoc()
                    {
                        MaChamSoc = row.GetValueDefault<string>(0),
                        NgayGhiNhan = row.GetValueDefault<DateTime>(1),
                        TinhTrangCongViec = row.GetValueDefault<string>(2),
                        LuongSua = row.GetValueDefault<float>(3),
                        DaChoAn = row.GetValueDefault<bool>(4),
                        DaDonVeSinh = row.GetValueDefault<bool>(5),
                        DaVatSua = row.GetValueDefault<bool>(6),
                        MaPhanCong = row.GetValueDefault<string>(7),
                        MaBo = row.GetValueDefault<string>(8)
                    };
                }, string.Format("select * from ChamSoc where maphancong = '{0}' and NgayGhiNhan='{1}'", pc.MaPhanCong, DateTime.Now));
            }
            catch (Exception)
            {
                return result;
            }
            //foreach (var item in result)
            //{
            //    IncludeTinhTrangBo(item);
            //}
            return result;
        }

        //add tinh trang bo
        public static ChamSoc AddTinhTrangBo(TinhTrangBo tinhTrangBo)
        {
            ChamSoc result = null;
            try
            {
                result = DataProvider.ExecuteReaderOne((SqlDataReader row) =>
                {
                    return new ChamSoc()
                    {
                        MaChamSoc = row.GetValueDefault<string>(0),
                        NgayGhiNhan = row.GetValueDefault<DateTime>(1),
                        TinhTrangCongViec = row.GetValueDefault<string>(2),
                        LuongSua = row.GetValueDefault<float>(3),
                        DaChoAn = row.GetValueDefault<bool>(4),
                        DaDonVeSinh = row.GetValueDefault<bool>(5),
                        DaVatSua = row.GetValueDefault<bool>(6),
                        MaPhanCong = row.GetValueDefault<string>(7),
                        MaBo = row.GetValueDefault<string>(8)
                    };
                }, string.Format("select top 1 * from chamsoc where maphancong = '{0}' and mabo = '{1}'", tinhTrangBo.MaPhanCong, tinhTrangBo.MaBo));
            }
            catch (Exception)
            {
                return null;
            }
            try
            {
                DataProvider.ExecuteNonQuery(string.Format("insert into TinhTrangBo(MaChamSoc,ThoiGianGhiNhan,CanNang,ChieuCao) values ('{0}', '{1}', {2}, {3})", result.MaChamSoc, DateTime.Now, tinhTrangBo.CanNang, tinhTrangBo.ChieuCao));
            }
            catch (Exception)
            {
                return null;
            }
            PhanCong temp = null;
            try
            {
                temp = DataProvider.ExecuteReaderOne((SqlDataReader row) =>
                {
                    return new PhanCong()
                    {
                        MaPhanCong = row.GetValueDefault<string>(0),
                        MaChuong = row.GetValueDefault<string>(5)
                    };
                }, string.Format("select top 1 * from phancong where maphancong = '{0}'", result.MaPhanCong));
            }
            catch (Exception)
            {
                return null;
            }
            result.MaChuong = temp.MaChuong;
            return result;
        }


        public static List<TinhTrangBo> GetTinhTrangBo(TinhTrangBo tinhTrangBo)
        {
            List<TinhTrangBo> result = null;
            try
            {
                result = DataProvider.ExecuteReader((SqlDataReader row) =>
                {
                    return new TinhTrangBo()
                    {
                        MaChamSoc = row.GetValueDefault<string>(0),
                        MaPhanCong = row.GetValueDefault<string>(1),
                        MaBo = row.GetValueDefault<string>(2),
                        ChieuCao = row.GetValueDefault<float>(3),
                        CanNang = row.GetValueDefault<float>(4)
                    };
                }, string.Format("select ttb.MaChamSoc,pc.MaPhanCong,cs.MaBo,ttb.ChieuCao,ttb.CanNang " +
                                 "from TinhTrangBo ttb,ChamSoc cs,PhanCong pc " +
                                 "where ttb.machamsoc = '{0}' and ttb.MaChamSoc=cs.MaChamSoc and cs.MaPhanCong = pc.MaPhanCong order by ThoiGianGhiNhan desc", tinhTrangBo.MaChamSoc));
            }
            catch (Exception)
            {
                return result;
            }
            return result;
        }

        public static ThongSo ReportTinhTrangBo_Day(List<BoTemp> list, DateTime ngaybatdau, DateTime ngayketthuc, string thoigian, int value)
        {
            string temp = "";
            for (int i = 0; i < list.Count; i++)
            {
                if (i == list.Count - 1)
                {
                    temp += "MaBo = '" + list[i].MaBo + "'";
                }
                else
                {
                    temp += "MaBo = '" + list[i].MaBo + "' or ";
                }
            }
            string query = string.Format("select Avg(CanNang) as CanNangTB,Avg(ChieuCao) as ChieuCaoTB " +
                                    "from TinhTrangBo,ChamSoc " +
                                    "where TinhTrangBo.MaChamSoc = ChamSoc.MaChamSoc and ( " +
                                    "{0}" +
                                    " ) and DATEDIFF(DAY,ThoiGianGhiNhan,'{1}') = {2}",
                                    temp, ngaybatdau.Date, value
                                    );
            ThongSo result = null;
            try
            {
                result = DataProvider.ExecuteReaderOne((SqlDataReader row) =>
                {
                    return new ThongSo()
                    {
                        CanNang = row.GetValueDefault<double>(0),
                        ChieuCao = row.GetValueDefault<double>(1)
                    };
                }, query);
            }
            catch (Exception)
            {
                return result;
            }
            return result;
        }
    }
}
