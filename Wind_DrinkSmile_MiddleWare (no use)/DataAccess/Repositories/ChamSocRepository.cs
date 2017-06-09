using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Threading.Tasks;
using DomainData;

namespace DataAccess.Repositories
{
    public class ChamSocRepository : DataAccessFull<ChamSoc>
    {
        #region Singleton
        private ChamSocRepository() { }
        private static ChamSocRepository _singleton;
        public static ChamSocRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new ChamSocRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_getAll()
        {
            return "select * from chamsoc";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from chamsoc where machansoc = '{0}'", identity);
        }

        protected override string SQL_insert(ChamSoc addingObject)
        {
            return string.Format("insert into chamsoc " +
                "(machamsoc,maphancong,mabo,luongsuavat,tinhtrangcongviec,dadonvesinh,dachoan,davatsua,ngayghinhan) values " +
                "('{0}','{1}','{2}','{3}',N'{4}','{5}','{6}','{7}','{8}')",
                addingObject.MaChamSoc, addingObject.MaPhanCong, addingObject.MaBo, addingObject.LuongSuaVat, addingObject.TinhTrangCongViec,
                addingObject.DaDonVeSinh, addingObject.DaChoAn, addingObject.DaVatSua, addingObject.NgayGhiNhan);
        }

        protected override string SQL_update(ChamSoc updatingObject)
        {
            return string.Format("update baocaotinhtrangbo " +
                "set maphancong = '{1}', mabo = '{2}', luongsuavat = '{3}', tinhtrangcongviec = N'{4}', dadonvesinh = '{5}', dachoan = '{6}', davatsua = '{7}', ngayghinhan = '{8}'" +
                "where machamsoc = '{0}'", updatingObject.MaChamSoc,
                updatingObject.MaPhanCong, updatingObject.MaBo, updatingObject.LuongSuaVat, updatingObject.TinhTrangCongViec, 
                updatingObject.DaDonVeSinh, updatingObject.DaChoAn,updatingObject.DaVatSua,updatingObject.NgayGhiNhan);
        }

        protected override string SQL_delete(ChamSoc deletingObject)
        {
            return string.Format("delete from baocaotinhtrangbo where mabaocao = '{0}'", deletingObject.MaChamSoc);
        }

        protected override ChamSoc SqlRow2Object(SqlDataReader row)
        {
            return new ChamSoc()
            {
                MaPhanCong = row.GetValueDefault<string>(0),
                MaBo = row.GetValueDefault<string>(1),
                MaChamSoc = row.GetValueDefault<string>(2),
                LuongSuaVat = row.GetValueDefault<float>(3),
                TinhTrangCongViec = row.GetValueDefault<string>(4),
                DaDonVeSinh = row.GetValueDefault<bool>(5),
                DaVatSua = row.GetValueDefault<bool>(6),
                DaChoAn = row.GetValueDefault<bool>(7),
                NgayGhiNhan = row.GetValueDefault<DateTime>(8),
            };
        }
    }
}
