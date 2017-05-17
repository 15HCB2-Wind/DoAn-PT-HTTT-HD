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
        public string Insert(PhanCong addingObject)
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
            addingObject.MaPhanCong = String.Format("PC{0:D4}", count.Count + 1);
            int result = -1;
            try
            {
                result = DataProvider.ExecuteNonQuery(string.Format("insert into phancong(maphancong,ngaybatdau,ngayketthuc,ngaytrongtuan,manv,machuong) values ('{0}', '{1}', '{2}', '{3}', '{4}', '{5}')", addingObject.MaPhanCong, addingObject.NgayBatDau, addingObject.NgayKetThuc, addingObject.NgayTrongTuan, addingObject.MaNV, addingObject.MaChuong));
            }
            catch (Exception ex)
            {
                return ex.Message;
            }
            return result.ToString();
        }
    }
}
