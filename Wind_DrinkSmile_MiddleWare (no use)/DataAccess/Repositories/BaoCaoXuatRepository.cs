using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DomainData;
using System.Data.SqlClient;

namespace DataAccess.Repositories
{
    public class BaoCaoXuatRepository : DataAccessFull<BaoCaoXuat>
    {
        #region Singleton
        private BaoCaoXuatRepository() { }
        private static BaoCaoXuatRepository _singleton;
        public static BaoCaoXuatRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new BaoCaoXuatRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_getAll()
        {
            return "select * from baocaoxuat";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from baocaoxuat where mabaocao = '{0}'", identity);
        }

        protected override string SQL_insert(BaoCaoXuat addingObject)
        {
            return string.Format("insert into baocaoxuat " +
                "(mabaocao,ngaybatdau,ngayketthuc,ngaylap,tongluongsua,manv) values " +
                "('{0}','{1}','{2}','{3}','{4}','{5}')",
                addingObject.MaBaoCao, addingObject.NgayBatDau, addingObject.NgayKetThuc, addingObject.NgayLap, addingObject.TongLuongSua, addingObject.MaNV);
        }

        protected override string SQL_update(BaoCaoXuat updatingObject)
        {
            return string.Format("update baocaotinhtrangbo " +
                "set ngaybatdau = '{1}', ngayketthuc = '{2}', ngaylap = '{3}', tongluongsua = '{4}', manv = '{5}'" +
                "where mabaocao = '{0}'", updatingObject.MaBaoCao,
                updatingObject.NgayBatDau, updatingObject.NgayKetThuc, updatingObject.NgayLap, updatingObject.TongLuongSua, updatingObject.MaNV);
        }

        protected override string SQL_delete(BaoCaoXuat deletingObject)
        {
            return string.Format("delete from baocaoxuat where mabaocao = '{0}'", deletingObject.MaBaoCao);
        }

        protected override BaoCaoXuat SqlRow2Object(SqlDataReader row)
        {
            return new BaoCaoXuat()
            {
                MaBaoCao = row.GetValueDefault<string>(0),
                NgayBatDau = row.GetValueDefault<DateTime>(1),
                NgayKetThuc = row.GetValueDefault<DateTime>(2),
                NgayLap = row.GetValueDefault<DateTime>(3),
                TongLuongSua = row.GetValueDefault<float>(4),
                MaNV = row.GetValueDefault<string>(5),
            };
        }
    }
}
