using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using DomainData;
using System.Data.SqlClient;

namespace DataAccess.Repositories
{
    public class PhanCongRepository : DataAccessOrigin<PhanCong>
    {
        public static string Insert(PhanCong pc)
        {
            //tạo mã phân công
            TempCount count = null;
            try
            {
                count = DataProvider.ExecuteReaderOne((SqlDataReader row) =>
                {
                    return new TempCount()
                    {
                        Count = row.GetInt32(0)
                    };
                }, "select count(MaPhanCong) as Count from PhanCong");

            }
            catch (Exception ex)
            {
                return ex.Message;
            }

            //thêm mới phân công
            pc.MaPhanCong = String.Format("PC{0:D4}", count.Count + 1);
            int result = -1;
            try
            {
                result = DataProvider.ExecuteNonQuery(string.Format("insert into phancong(maphancong,ngaybatdau,ngayketthuc,ngaytrongtuan,manv,machuong) values ('{0}', '{1}', '{2}', '{3}', '{4}', '{5}')", pc.MaPhanCong, pc.NgayBatDau, pc.NgayKetThuc, pc.NgayTrongTuan, pc.MaNV, pc.MaChuong));
            }
            catch (Exception ex)
            {
                return ex.Message;
            }
            return result.ToString();
        }
        public static string Update(PhanCong pc)
        {
            int result = -1;
            try
            {
                result = DataProvider.ExecuteNonQuery(string.Format("update phancong set ngaybatdau='{0}',ngayketthuc='{1}',ngaytrongtuan='{2}',manv='{3}',machuong='{4}' where maphancong='{5}'", pc.NgayBatDau, pc.NgayKetThuc, pc.NgayTrongTuan, pc.MaNV, pc.MaChuong, pc.MaPhanCong));
            }
            catch (Exception ex)
            {
                return ex.Message;
            }
            return result.ToString();
        }
        public static List<PhanCong> GetAllFromNhanVien(PhanCong pc)
        {
            List<PhanCong> result=null;
            try
            {
                result = DataProvider.ExecuteReader((SqlDataReader row) =>
                {
                    return new PhanCong()
                    {
                        MaPhanCong = row.GetValueDefault<string>(0),
                        NgayBatDau = row.GetValueDefault<DateTime>(1),
                        NgayKetThuc = row.GetValueDefault<DateTime>(2),
                        NgayTrongTuan = row.GetValueDefault<string>(3),
                        MaNV = row.GetValueDefault<string>(4),
                        MaChuong = row.GetValueDefault<string>(5)
                    };
                },string.Format("select * from phancong where manv = '{0}'",pc.MaNV));
            }
            catch (Exception)
            {
                return result;
            }
            return result;
        }
        public static PhanCong GetOneFromPhanCong(PhanCong pc)
        {
            PhanCong result = null;
            try
            {
                result = DataProvider.ExecuteReaderOne((SqlDataReader row) =>
                {
                    return new PhanCong()
                    {
                        MaPhanCong = row.GetValueDefault<string>(0),
                        NgayBatDau = row.GetValueDefault<DateTime>(1),
                        NgayKetThuc = row.GetValueDefault<DateTime>(2),
                        NgayTrongTuan = row.GetValueDefault<string>(3),
                        MaNV = row.GetValueDefault<string>(4),
                        MaChuong = row.GetValueDefault<string>(5)
                    };
                }, string.Format("select top 1 * from phancong where maphancong = '{0}'", pc.MaPhanCong));
            }
            catch (Exception)
            {
                return result;
            }
            return result;
        }
        public static List<PhanCong> GetAllFromChuongTrai(PhanCong pc)
        {
            List<PhanCong> result = null;
            try
            {
                result = DataProvider.ExecuteReader((SqlDataReader row) =>
                {
                    return new PhanCong()
                    {
                        MaPhanCong = row.GetValueDefault<string>(0),
                        NgayBatDau = row.GetValueDefault<DateTime>(1),
                        NgayKetThuc = row.GetValueDefault<DateTime>(2),
                        NgayTrongTuan = row.GetValueDefault<string>(3),
                        MaNV = row.GetValueDefault<string>(4),
                        MaChuong = row.GetValueDefault<string>(5)
                    };
                }, string.Format("select * from phancong where machuong = '{0}'", pc.MaChuong));
            }
            catch (Exception)
            {
                return result;
            }
            return result;
        }
    }
}
