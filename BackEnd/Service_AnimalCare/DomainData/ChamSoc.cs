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

        //tinhtrang
        public float ChieuCao1 { get; set; }
        public float CanNang1 { get; set; }
        public float ChieuCao2 { get; set; }
        public float CanNang2 { get; set; }

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
            ChieuCao1 = 0;
            CanNang1 = 0;
            ChieuCao2 = 0;
            CanNang2 = 0;
        }
    }
}
