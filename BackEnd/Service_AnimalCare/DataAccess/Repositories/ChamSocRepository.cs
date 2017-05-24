using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;

namespace DataAccess.Repositories
{
    public class ChamSocRepository :DataAccessOrigin<ChamSoc>
    {
        public static bool createIDAssignment(ChamSoc cs)
        {
            //tạo mã phân công
            object result = -1;
            try
            {
                result = DataProvider.ExecuteScalar("select count(MaChamSoc) as Count from ChamSoc");
            }
            catch (Exception ex)
            {
                return false;
            }
            cs.MaChamSoc = String.Format("cs{0:D4}", Convert.ToInt32(result) + 1);
            return true;
        }

        public static int Insert(ChamSoc cs)
        {
            //thêm mới phân công
            int result = -1;
            if (createIDAssignment(cs))
            {
                try
                {
                    result = DataProvider.ExecuteNonQuery(string.Format("insert into ChamSoc(MaChamSoc,NgayGhiNhan,TinhTrangCongViec,LuongSua,DaChoAn,DaDonVeSinh,DaVatSua,MaPhanCong,MaBo) values ('{0}', '{1}', N'{2}', {3}, {4}, {5},{6},'{7}','{8}')", cs.MaChamSoc,cs.NgayGhiNhan,cs.TinhTrangCongViec,cs.LuongSua,cs.DaChoAn,cs.DaDonVeSinh,cs.DaVatSua,cs.MaPhanCong,cs.MaBo));
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
            //thêm mới phân công
            int result = -1;
            if (createIDAssignment(cs))
            {
                try
                {
                    result = DataProvider.ExecuteNonQuery(string.Format("update ChamSoc set NgayGhiNhan = '{1}',TinhTrangCongViec = N'{2}',LuongSua = {3},DaChoAn = {4},DaDonVeSinh = {5},DaVatSua = {6},MaPhanCong = '{7}',MaBo = '{8}' where MaChamSoc = '{0}'", cs.MaChamSoc, cs.NgayGhiNhan, cs.TinhTrangCongViec, cs.LuongSua, cs.DaChoAn, cs.DaDonVeSinh, cs.DaVatSua, cs.MaPhanCong, cs.MaBo));
                }
                catch (Exception)
                {
                    return result;
                }
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
                        TinhTrangCongViec=row.GetValueDefault<string>(2),
                        LuongSua = row.GetValueDefault<float>(3),
                        DaChoAn = row.GetValueDefault<bool>(4),
                        DaDonVeSinh = row.GetValueDefault<bool>(5),
                        DaVatSua = row.GetValueDefault<bool>(6),
                        MaPhanCong = row.GetValueDefault<string>(7),
                        MaBo = row.GetValueDefault<string>(8)
                    };
                }, string.Format("select * from ChamSoc where maphancong = '{0}'", pc.MaPhanCong));
            }
            catch (Exception)
            {
                return result;
            }
            return result;
        }
    }
}
