using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repositories
{
    public class ChuongTraiRepository: DataAccessFull<ChuongTrai>
    {
        #region Singleton
        private  ChuongTraiRepository() { }
        private static ChuongTraiRepository _singleton;
        public static ChuongTraiRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new ChuongTraiRepository();
            return _singleton;
        }
        #endregion

        protected override string SQL_insert(ChuongTrai addingObject)
        {
            return string.Format("insert into ChuongTrai(MaChuong,TenChuong,SucChua,TinhTrang,DaXoa,MaChiNhanh)" +
                "values ('{0}',N'{1}',{2},N'{3}',{4},'{5}')",
                addingObject.MaChuong,
                addingObject.TenChuong,
                addingObject.SucChua,
                addingObject.TinhTrang,
                addingObject.DaXoa,
                addingObject.MaChiNhanh);
        }

        protected override string SQL_update(ChuongTrai updatingObject)
        {
            return string.Format("update ChuongTrai set TenChuong=N'{0}',SucChua='{1}',TinhTrang=N'{2}',DaXoa={3}," +
            "MaChiNhanh={4} where MaChuong={5}",
            updatingObject.TenChuong,
            updatingObject.SucChua,
            updatingObject.TinhTrang,
            updatingObject.DaXoa,
            updatingObject.MaChiNhanh,
            updatingObject.MaChuong);
        }

        protected override string SQL_getAll()
        {
            return "select * from ChuongTrai";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from chuongtrai where machuong = '{0}'", identity);
        }

        protected override ChuongTrai SqlRow2Object(SqlDataReader row)
        {
            return new ChuongTrai()
            {
                MaChuong = row.GetValueDefault<string>(0),
                TenChuong = row.GetValueDefault<string>(1),
                SucChua = row.GetValueDefault<float>(2),
                TinhTrang = row.GetValueDefault<string>(3),
                DaXoa = row.GetValueDefault<bool>(4),
                MaChiNhanh = row.GetValueDefault<string>(5)
            };
        }

        protected override string SQL_delete(ChuongTrai deletingObject)
        {
            return string.Format("update ChuongTrai set DaXoa = {0} where MaChuong = '{1}'", deletingObject.DaXoa, deletingObject.MaChuong);
        }
    }
}
