using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DomainData
{
    public class BaoCaoTinhTrangBo
    {
        public string MaBaoCao { get; set; }

        public DateTime NgayBatDau { get; set; }

        public DateTime NgayKetThuc { get; set; }

        public DateTime NgayLap { get; set; }

        public int TongSoBo { get; set; }

        public int SoBoBenh { get; set; }

        //khóa ngoại 
        public String MaNV { get; set; }
    }
}
