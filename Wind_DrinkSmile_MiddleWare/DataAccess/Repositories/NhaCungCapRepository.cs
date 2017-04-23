using DomainData;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DataAccess.Repositories
{
    public class NhaCungCapRepository : DataAccessInsertUpdate<NhaCungCap>
    {
        #region Singleton
        private NhaCungCapRepository() { }
        private static NhaCungCapRepository _singleton;
        public static NhaCungCapRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new NhaCungCapRepository();
            return _singleton;
        }
        #endregion
        protected override string SQL_insert(NhaCungCap addingObject)
        {
            return string.Format("insert into NhaCungCap(MaNCC,TenNCC,TinhTrang,DiaChi) values ('{0}',N'{1}',N'{2}',N'{3}')",
                addingObject.MaNCC,
                addingObject.TenNCC,
                addingObject.TinhTrang,
                addingObject.DiaChi);
        }

        protected override string SQL_update(NhaCungCap updatingObject)
        {
            return string.Format("update NhaCungCap set TenNCC=N'{0}',TinhTrang=N'{1}',DiaChi=N'{2}' where MaNCC='{3}'",
                updatingObject.TenNCC,
                updatingObject.TinhTrang,
                updatingObject.DiaChi,
                updatingObject.MaNCC);
        }

        protected override string SQL_getAll()
        {
            return string.Format("select * from NhaCungCap");
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from NhaCungCap where MaNCC = '{0}')", identity);
        }

        protected override NhaCungCap SqlRow2Object(System.Data.SqlClient.SqlDataReader row)
        {
            return new NhaCungCap()
            {
                MaNCC = row.GetValueDefault<string>(0),
                TenNCC = row.GetValueDefault<string>(1),
                TinhTrang = row.GetValueDefault<string>(2),
                DiaChi = row.GetValueDefault<string>(3)
            };
        }
    }
}
