using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repositories
{
    public class ChiNhanhRepository : DataAccessInsertUpdate<ChiNhanh>
    {
        #region Singleton
        private ChiNhanhRepository() { }
        private static ChiNhanhRepository _singleton;
        public static ChiNhanhRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new ChiNhanhRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_getAll()
        {
            return "select * from chinhanh";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from chinhanh where machinhanh = '{0}'", identity);
        }
        protected override string SQL_insert(ChiNhanh addingObject)
        {
            return string.Format("insert into chinhanh " +
                "(machinhanh,tenchinhanh,sodt,diachi,tinhtrang,daxoa) values " +
                "('{0}',N'{1}',N'{2}',N'{3}',N'{4}','{5}')",
                addingObject.MaChiNhanh, addingObject.TenChiNhanh, addingObject.SoDT, addingObject.DiaChi, addingObject.TinhTrang, addingObject.DaXoa);
        }

        protected override string SQL_update(ChiNhanh updatingObject)
        {
            return string.Format("update chinhanh " +
                "set tenchinhanh = N'{1}', sodt = N'{2}', diachi = N'{3}', tinhtrang = N'{4}', daxoa = '{5}' " +
                "where machinhanh = '{0}'", updatingObject.MaChiNhanh, updatingObject.TenChiNhanh, updatingObject.SoDT, updatingObject.DiaChi, updatingObject.TinhTrang, updatingObject.DaXoa);
        }

        protected override ChiNhanh SqlRow2Object(SqlDataReader row)
        {
            return new ChiNhanh()
            {
                MaChiNhanh = row.GetValueDefault<string>(0),
                TenChiNhanh = row.GetValueDefault<string>(1),
                SoDT = row.GetValueDefault<string>(2),
                DiaChi = row.GetValueDefault<string>(3),
                TinhTrang = row.GetValueDefault<string>(4),
                DaXoa = row.GetValueDefault<bool>(5),
                QuanLy = row.GetValueDefault<string>(6),
            };
        }

    }
}
