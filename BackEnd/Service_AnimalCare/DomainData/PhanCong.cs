using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DomainData
{
    public class PhanCong
    {
        public string MaPhanCong { get; set; }
        public DateTime NgayBatDau { get; set; }
        public DateTime NgayKetThuc { get; set; }
        public string NgayTrongTuan { get; set; }
        public string MaNV { get; set; }
        public string MaChuong { get; set; }
        public string HoTen { get; set; }
        //
        public string NgayBatDauFormatted { get; set; }
        public string NgayKetThucFormatted { get; set; }
    }
}
