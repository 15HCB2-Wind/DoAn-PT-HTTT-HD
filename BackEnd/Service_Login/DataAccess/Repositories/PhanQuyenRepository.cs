using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repositories
{
    public class PhanQuyenRepository : DataAccessGetOnly<PhanQuyen>
    {
        #region Singleton
        private PhanQuyenRepository() { }
        private static PhanQuyenRepository _singleton;
        public static PhanQuyenRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new PhanQuyenRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_getAll()
        {
            return "select * from phanquyen";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from phanquyen where mapq = '{0}'", identity);
        }

        protected override PhanQuyen SqlRow2Object(SqlDataReader row)
        {
            return new PhanQuyen()
            {
                MaPQ = row.GetValueDefault<string>(0),
                CapPQ = row.GetValueDefault<int>(2),
                TenPQ = row.GetValueDefault<string>(1),
            };
        }
    }
}
