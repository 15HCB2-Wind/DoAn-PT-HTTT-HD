using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DomainData
{
    public class ChamSoc
    {
        public string MaChamSoc { get; set; }
        public DateTime NgayGhiNhan { get; set; }
        public string TinhTrangCongViec { get; set; }
        public float LuongSua { get; set; }
        public bool DaChoAn { get; set; }
        public bool DaDonVeSinh { get; set; }
        public bool DaVatSua { get; set; }
        public string MaPhanCong { get; set; }
        public string MaBo { get; set; }
        public string MaChuong { get; set; }
        public string MaChiNhanh { get; set; }
        public ChamSoc()
        {
            MaChamSoc = "";
            NgayGhiNhan = DateTime.Now;
            TinhTrangCongViec = "";
            LuongSua = 0;
            DaChoAn = false;
            DaDonVeSinh = false;
            DaVatSua = false;
            MaPhanCong = "";
            MaBo = "";
        }
    }
}
