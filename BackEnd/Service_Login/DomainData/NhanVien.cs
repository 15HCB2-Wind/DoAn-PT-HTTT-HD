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
        public string TenTaiKhoan { get; set; }
        public string MatKhau { get; set; }
        public string Email { get; set; }
        public string MaPQ { get; set; }
        //
        public PhanQuyen _PhanQuyen { get; set; }
    }
}
