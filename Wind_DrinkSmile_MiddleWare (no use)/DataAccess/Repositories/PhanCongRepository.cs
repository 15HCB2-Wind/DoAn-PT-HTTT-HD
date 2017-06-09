using DomainData;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DataAccess.Repositories
{
    public class PhanCongRepository: DataAccessInsertUpdate<PhanCong>
    {
        #region Singleton
        private PhanCongRepository() { }
        private static PhanCongRepository _singleton;
        public static PhanCongRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new PhanCongRepository();
            return _singleton;
        }
        #endregion
        protected override string SQL_insert(PhanCong addingObject)
        {
            return string.Format("insert into PhanCong(MaPhanCong,NgayBatDau,NgayKetThuc,NgayTrongTuan,MaNV,MaChuong) values " +
            "('{0}','{1}','{2}','{3}','{4}','{5}')",
            addingObject.MaPhanCong,
            addingObject.NgayBatDau,
            addingObject.NgayKetThuc,
            addingObject.NgayTrongTuan,
            addingObject.MaNV,
            addingObject.MaChuong);
        }

        protected override string SQL_update(PhanCong updatingObject)
        {
            return string.Format("update PhanCong set NgayBatDau='{0}',NgayKetThuc='{1}',NgayTrongTuan='{2}',MaNV='{3}',MaChuong='{4}' where MaPhanCong='{5}'",
                updatingObject.NgayBatDau,
                updatingObject.NgayKetThuc,
                updatingObject.NgayTrongTuan,
                updatingObject.MaNV,
                updatingObject.MaChuong,
                updatingObject.MaPhanCong);
        }

        protected override string SQL_getAll()
        {
            return string.Format("select * from PhanCong");
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from PhanCong where MaPhanCong='{0}'", identity);
        }

        protected override PhanCong SqlRow2Object(System.Data.SqlClient.SqlDataReader row)
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
        }
    }
}
