using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DomainData
{
    public class BoTemp
    {
        public string MaChiNhanh { get; set; }
        public string MaBo { get; set; }
    }

    public class ThongSo
    {
        public double CanNang { get; set; }
        public double ChieuCao { get; set; }
        public DateTime Ngay { get; set; }
        public string MaChiNhanh { get; set; }
    }
}
