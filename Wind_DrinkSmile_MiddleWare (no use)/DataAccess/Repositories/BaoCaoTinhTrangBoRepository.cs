using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using DomainData;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace DataAccess.Repositories
{
    public class BaoCaoTinhTrangBoRepository : DataAccessFull<BaoCaoTinhTrangBo>
    {
        #region Singleton
        private BaoCaoTinhTrangBoRepository() { }
        private static BaoCaoTinhTrangBoRepository _singleton;
        public static BaoCaoTinhTrangBoRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new BaoCaoTinhTrangBoRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_getAll()
        {
            return "select * from baocaotinhtrangbo";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from baocaotinhtrangbo where mabaocao = '{0}'", identity);
        }

        protected override string SQL_insert(BaoCaoTinhTrangBo addingObject)
        {
            return string.Format("insert into baocaotinhtrangbo " +
                "(mabaocao,ngaybatdau,ngayketthuc,ngaylap,tongsobo,sobobenh,manv) values " +
                "('{0}','{1}','{2}','{3}','{4}','{5}','{6}')",
                addingObject.MaBaoCao, addingObject.NgayBatDau, addingObject.NgayKetThuc, addingObject.NgayLap, addingObject.TongSoBo, addingObject.SoBoBenh, addingObject.MaNV);
        }

        protected override string SQL_update(BaoCaoTinhTrangBo updatingObject)
        {
            return string.Format("update baocaotinhtrangbo " +
                "set ngaybatdau = '{1}', ngayketthuc = '{2}', ngaylap = '{3}', tongsobo = '{4}', sobobenh = '{5}', manv = '{6}'"+
                "where mabaocao = '{0}'", updatingObject.MaBaoCao,
                updatingObject.NgayBatDau, updatingObject.NgayKetThuc, updatingObject.NgayLap, updatingObject.TongSoBo, updatingObject.SoBoBenh, updatingObject.MaNV);
        }

        protected override string SQL_delete(BaoCaoTinhTrangBo deletingObject)
        {
            return string.Format("delete from baocaotinhtrangbo where mabaocao = '{0}'", deletingObject.MaBaoCao);
        }

        protected override BaoCaoTinhTrangBo SqlRow2Object(SqlDataReader row)
        {
            return new BaoCaoTinhTrangBo()
            {
                MaBaoCao = row.GetValueDefault<string>(0),
                NgayBatDau = row.GetValueDefault<DateTime>(1),
                NgayKetThuc = row.GetValueDefault<DateTime>(2),
                NgayLap = row.GetValueDefault<DateTime>(3),
                TongSoBo = row.GetValueDefault<int>(4),
                SoBoBenh = row.GetValueDefault<int>(5),
                MaNV = row.GetValueDefault<string>(6),
            };
        }
    }
}
