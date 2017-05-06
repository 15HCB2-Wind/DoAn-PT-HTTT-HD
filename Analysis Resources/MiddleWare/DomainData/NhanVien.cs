using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DomainData
{
    public class NhanVien
    {
        public string MaNV { get; set; }
        public string HoTen { get; set; }
        public string GioiTinh { get; set; }
        public DateTime NgaySinh { get; set; }
        public string Email { get; set; }
        public string TaiKhoan { get; set; }
        public string MatKhau { get; set; }
        public string SoDT { get; set; }
        public string DiaChi { get; set; }
        public string TinhTrang { get; set; }
        public bool DaXoa { get; set; }
        public string MaChiNhanh { get; set; }
        public string MaPhanQuyen { get; set; }
    }
}
