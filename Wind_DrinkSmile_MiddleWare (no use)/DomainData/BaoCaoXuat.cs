using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DomainData
{
    public class BaoCaoXuat
    {
        public string MaBaoCao { get; set; }

        public DateTime NgayBatDau { get; set; }

        public DateTime NgayKetThuc { get; set; }

        public DateTime NgayLap { get; set; }

        public float TongLuongSua { get; set; }

        //khóa ngoại
        public string MaNV { get; set; }
    }
}
