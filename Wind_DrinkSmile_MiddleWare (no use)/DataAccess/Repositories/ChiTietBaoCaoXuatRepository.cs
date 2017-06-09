using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repositories
{
    public class ChiTietBaoCaoXuatRepository : DataAccessInsertUpdate<ChiTietBaoCaoXuat>
    {
        #region Singleton
        private ChiTietBaoCaoXuatRepository() { }
        private static ChiTietBaoCaoXuatRepository _singleton;
        public static ChiTietBaoCaoXuatRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new ChiTietBaoCaoXuatRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_getAll()
        {
            return "select * from ChiTietBaoCaoXuat";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from ChiTietBaoCaoXuat where mabaocao = '{0}'", identity);
        }

        protected override string SQL_insert(ChiTietBaoCaoXuat addingObject)
        {
            return string.Format("insert into ChiTietBaoCaoXuat " + 
                "(mabaocao,makho,luongsuaxuat) values " + "(N'{0}',N'{1}','{2}')", 
                addingObject.MaBaoCao, addingObject.MaKho, addingObject.LuongSuaXuat);
        }

        protected override string SQL_update(ChiTietBaoCaoXuat updatingObject)
        {
            return string.Format("update ChiTietBaoCaoXuat " +
                "set luongsuaxuat = '{2}' " +
                "where mabaocao = N'{0}' and makho = N'{1}'", updatingObject.MaBaoCao, updatingObject.MaKho, updatingObject.LuongSuaXuat);
        }

        protected override ChiTietBaoCaoXuat SqlRow2Object(SqlDataReader row)
        {
            return new ChiTietBaoCaoXuat()
            {
                MaBaoCao = row.GetValueDefault<string>(0),
                MaKho = row.GetValueDefault<string>(1),
                LuongSuaXuat = row.GetValueDefault<float>(2),
            };
        }
    }
}
