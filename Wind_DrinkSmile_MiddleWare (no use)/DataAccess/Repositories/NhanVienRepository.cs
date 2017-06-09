using DomainData;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repositories
{
    public class NhanVienRepository : DataAccessInsertUpdate<NhanVien>
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

        protected override string SQL_getAll()
        {
            return "select * from nhanvien";
        }

        protected override string SQL_getFrom(string identity)
        {
            return string.Format("select top 1 * from nhanvien where manv = '{0}'", identity);
        }

        protected override string SQL_insert(NhanVien addingObject)
        {
            return string.Format("insert into nhanvien " + 
                "(manv,hoten,gioitinh,ngaysinh,email,taikhoan,matkhau,sodt,diachi,tinhtrang,daxoa,chinhanh,maphanquyen) values " + 
                "('{0}',N'{1}',N'{2}','{3}','{4}','{5}','{6}','{7}',N'{8}',N'{9}','{10}','{11}','{12}')",
                addingObject.MaNV, addingObject.HoTen, addingObject.GioiTinh, addingObject.NgaySinh, addingObject.Email, addingObject.TaiKhoan, addingObject.MatKhau,
                addingObject.SoDT, addingObject.DiaChi, addingObject.TinhTrang, addingObject.DaXoa, addingObject.MaChiNhanh, addingObject.MaPhanQuyen);
        }

        protected override string SQL_update(NhanVien updatingObject)
        {
            return string.Format("update nhanvien " +
                "set hoten = N'{1}', gioitinh = N'{2}', ngaysinh = '{3}', email = '{4}', taikhoan = '{5}', matkhau = '{6}', sodt = '{7}', diachi = N'{8}', tinhtrang = N'{9}', daxoa = '{10}', chinhanh = '{11}', maphanquyen = '{12}' " +
                "where manv = '{0}'", updatingObject.MaNV, 
                updatingObject.HoTen, updatingObject.GioiTinh, updatingObject.NgaySinh, updatingObject.Email, updatingObject.TaiKhoan, updatingObject.MatKhau,
                updatingObject.SoDT, updatingObject.DiaChi, updatingObject.TinhTrang, updatingObject.DaXoa, updatingObject.MaChiNhanh, updatingObject.MaPhanQuyen);
        }

        protected override NhanVien SqlRow2Object(SqlDataReader row)
        {
            return new NhanVien()
            {
                Email = row.GetValueDefault<string>(0),
                GioiTinh = row.GetValueDefault<string>(1),
                HoTen = row.GetValueDefault<string>(2),
                MaNV = row.GetValueDefault<string>(3),
                MatKhau = row.GetValueDefault<string>(4),
                NgaySinh = row.GetValueDefault<DateTime>(5),
                SoDT = row.GetValueDefault<string>(6),
                TaiKhoan = row.GetValueDefault<string>(7),
                TinhTrang = row.GetValueDefault<string>(8),
                DaXoa = row.GetValueDefault<bool>(9),
                DiaChi = row.GetValueDefault<string>(10),
                MaChiNhanh = row.GetValueDefault<string>(11),
                MaPhanQuyen = row.GetValueDefault<string>(12),
            };
        }
    }
}
