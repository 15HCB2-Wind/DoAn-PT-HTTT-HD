using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repositories
{
    public class NhanVienRepository : DataAccessOrigin<NhanVien>
    {
        #region Singleton
        private NhanVienRepository() { }
        private static NhanVienRepository _singleton;
        public static NhanVienRepository GetInstance()
        {
            if (_singleton == null)
                _singleton = new NhanVienRepository();
            return _singleton;
        }
        #endregion

        public NhanVien IsExistAndGet(string username, string password)
        {
            NhanVien result = null;
            try
            {
                result = DataProvider.ExecuteReaderOne((SqlDataReader row) =>
                {
                    return new NhanVien()
                    {
                        MaNV = row.GetValueDefault<string>(0),
                        _PhanQuyen = PhanQuyenRepository.GetInstance().GetFrom(row.GetValueDefault<string>(1)),
                    };

                }, string.Format("select top 1 nv.manv, nv.mapq from nhanvien nv where nv.tentaikhoan = '{0}' and nv.matkhau = '{1}'", username, password));
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return result;
        }

        public int Add(NhanVien obj)
        {
            int result = -1;
            try
            {
                result = DataProvider.ExecuteNonQuery(string.Format("insert into nhanvien(manv, tentaikhoan, matkhau, mapq, email) values ('{0}', '{1}', '{2}', '{3}', '{4}')", obj.MaNV, obj.TenTaiKhoan, obj.MatKhau, obj.MaPQ, obj.Email));
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return result;
        }
    }
}
