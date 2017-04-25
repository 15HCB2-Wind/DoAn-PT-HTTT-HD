using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repositories
{
    public class ChiTietBaoCaoTinhTrangBoRepository : DataAccessInsertUpdate<ChiTietBaoCaoTinhTrangBo>
    {
        #region Singleton
        private ChiTietBaoCaoTinhTrangBoRepository() { }
        private static ChiTietBaoCaoTinhTrangBoRepository _singleton;
        public static ChiTietBaoCaoTinhTrangBoRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new ChiTietBaoCaoTinhTrangBoRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_getAll()
        {
            return "select * from ChiTietBaoCaoTinhTrangBo";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from ChiTietBaoCaoTinhTrangBo where mabo = '{0}'", identity);
        }
        protected override string SQL_insert(ChiTietBaoCaoTinhTrangBo addingObject)
        {
            return string.Format("insert into ChiTietBaoCaoTinhTrangBo " +
                "(mabo,mabaocao,tinhtrang) values " +
                "(N'{0}',N'{1}',N'{2}')",
                addingObject.MaBo, addingObject.MaBaoCao, addingObject.TinhTrang);
        }

        protected override string SQL_update(ChiTietBaoCaoTinhTrangBo updatingObject)
        {
            return string.Format("update ChiTietBaoCaoTinhTrangBo " +
                "set tinhtrang = N'{2}'" +
                "where mabo = '{0}' and mabaocao = '{1}'", updatingObject.MaBo, updatingObject.MaBaoCao, updatingObject.TinhTrang);
        }

        protected override ChiTietBaoCaoTinhTrangBo SqlRow2Object(SqlDataReader row)
        {
            return new ChiTietBaoCaoTinhTrangBo()
            {
                MaBo = row.GetValueDefault<string>(0),
                MaBaoCao = row.GetValueDefault<string>(1),
                TinhTrang = row.GetValueDefault<string>(2)
            };
        }

    }
}

