using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Threading.Tasks;
using DomainData;

namespace DataAccess.Repositories
{
    public class BoRepository : DataAccessFull<Bo>
    {
        #region Singleton
        private BoRepository() { }
        private static BoRepository _singleton;
        public static BoRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new BoRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_getAll()
        {
            return "select * from bo";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from bo where mabo = '{0}'", identity);
        }

        protected override string SQL_insert(Bo addingObject)
        {
            return string.Format("insert into bo " +
                "(mabo,machip,mausac,coditat,nhandang,tinhtrang,daxoa,machuong) values " +
                "('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}')",
                addingObject.MaBo, addingObject.MaChip, addingObject.MauSac, addingObject.CoDiTat, addingObject.NhanDang, addingObject.TinhTrang, addingObject.DaXoa,addingObject.MaChuong);
        }

        protected override string SQL_update(Bo updatingObject)
        {
            return string.Format("update baocaotinhtrangbo " +
                "set machip = N'{1}', mausac = N'{2}', coditat = '{3}', nhandang = '{4}', tinhtrang = N'{5}', daxoa = '{6}', machuong = '{7}'" +
                "where mabo = '{0}'", updatingObject.MaBo,
                updatingObject.MaChip, updatingObject.MauSac, updatingObject.CoDiTat, updatingObject.NhanDang, updatingObject.TinhTrang, updatingObject.DaXoa,updatingObject.MaChuong);
        }

        protected override string SQL_delete(Bo deletingObject)
        {
            return string.Format("delete from bo where mabo = '{0}'", deletingObject.MaBo);
        }

        protected override Bo SqlRow2Object(SqlDataReader row)
        {
            return new Bo()
            {
                MaBo = row.GetValueDefault<string>(0),
                MaChip = row.GetValueDefault<string>(1),
                MauSac = row.GetValueDefault<string>(2),
                CoDiTat = row.GetValueDefault<bool>(3),
                NhanDang = row.GetValueDefault<string>(4),
                TinhTrang = row.GetValueDefault<string>(5),
                DaXoa = row.GetValueDefault<bool>(5),
                MaChuong = row.GetValueDefault<string>(6),
            };
        }
    }
}
