using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repositories
{
    public class ChiTietXuatSuaRepository : DataAccessInsertUpdate<ChiTietXuatSua>
    {
        #region Singleton
        private ChiTietXuatSuaRepository() { }
        private static ChiTietXuatSuaRepository _singleton;
        public static ChiTietXuatSuaRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new ChiTietXuatSuaRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_getAll()
        {
            return "select * from ChiTietXuatSua";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from ChiTietXuatSua where machungtu = '{0}'", identity);
        }
        protected override string SQL_insert(ChiTietXuatSua addingObject)
        {
            return string.Format("insert into ChiTietXuatSua " +
                "(machungtu,makho,luongsuaxuat) values " +
                "(N'{0}',N'{1}','{2}')",
                addingObject.MaChungTu, addingObject.MaKho, addingObject.LuongSuaXuat);
        }

        protected override string SQL_update(ChiTietXuatSua updatingObject)
        {
            return string.Format("update ChiTietXuatSua " +
                "set luongsuaxuat = '{2}'" +
                "where machungtu = N'{0}' and makho = N'{1}'", updatingObject.MaChungTu, updatingObject.MaKho, updatingObject.LuongSuaXuat);
        }

        protected override ChiTietXuatSua SqlRow2Object(SqlDataReader row)
        {
            return new ChiTietXuatSua()
            {
                MaChungTu = row.GetValueDefault<string>(0),
                MaKho = row.GetValueDefault<string>(1),
                LuongSuaXuat = row.GetValueDefault<float>(2)
            };
        }

    }
}


