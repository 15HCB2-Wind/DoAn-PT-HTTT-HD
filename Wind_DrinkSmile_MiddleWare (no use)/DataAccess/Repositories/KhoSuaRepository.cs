using DomainData;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DataAccess.Repositories
{
    public class KhoSuaRepository : DataAccessFull<KhoSua>
    {
        #region Singleton
        private KhoSuaRepository() { }
        private static KhoSuaRepository _singleton;
        public static KhoSuaRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new KhoSuaRepository();
            return _singleton;
        }
        #endregion
        protected override string SQL_delete(KhoSua deletingObject)
        {
            return string.Format("update KhoSua set DaXoa = {0} where MaKho='{1}'",deletingObject.DaXoa, deletingObject.MaKho);
        }

        protected override string SQL_insert(KhoSua addingObject)
        {
            return string.Format("insert into KhoSua(MaKho,TenKho,SucChua,LuongSuaCo,DiaChi,DaXoa,TinhTrang,MaChiNhanh) values " +
                "('{0}',N'{1}',{2},{3},N'{4}',{5},N'{6}','{7}')");
        }

        protected override string SQL_update(KhoSua updatingObject)
        {
            return string.Format("update KhoSua set TenKho=N'{0}',SucChua={1},LuongSuaCo={2},DiaChi=N'{3}'," +
                "DaXoa={4},TinhTrang={5},MaChiNhanh={6} where MaKho={7}",
                updatingObject.TenKho,
                updatingObject.SucChua,
                updatingObject.LuongSuaCo,
                updatingObject.DiaChi,
                updatingObject.DaXoa,
                updatingObject.TinhTrang,
                updatingObject.MaChiNhanh,
                updatingObject.MaKho);
        }

        protected override string SQL_getAll()
        {
            return string.Format("select * from KhoSua");
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from KhoSua where makho = '{0}'", identity);
        }

        protected override KhoSua SqlRow2Object(System.Data.SqlClient.SqlDataReader row)
        {
            return new KhoSua()
            {
                MaKho = row.GetValueDefault<string>(0),
                TenKho = row.GetValueDefault<string>(1),
                SucChua = row.GetValueDefault<float>(2),
                LuongSuaCo = row.GetValueDefault<float>(3),
                DiaChi = row.GetValueDefault<string>(4),
                DaXoa = row.GetValueDefault<bool>(5),
                TinhTrang = row.GetValueDefault<string>(6),
                MaChiNhanh = row.GetValueDefault<string>(7)
            };
        }
    }
}
