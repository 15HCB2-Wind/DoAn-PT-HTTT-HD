using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DomainData
{
    public class ChamSoc
    {
        public string MaChamSoc { get; set; }

        public float LuongSuaVat { get; set; }

        public string TinhTrangCongViec { get; set; }

        public bool DaDonVeSinh { get; set; }

        public bool DaChoAn { get; set; }

        public bool DaVatSua { get; set; }

        public DateTime NgayGhiNhan { get; set; }

        //khóa ngoại
        public string MaPhanCong { get; set; }
        public string MaBo { get; set; }
    }
}
