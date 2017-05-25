using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DomainData
{
    public class Bo
    {
        public string MaBo { get; set; }

        public string MaChip { get; set; }

        public string MauSac { get; set; }

        public bool CoDiTat { get; set; }

        public string NhanDang { get; set; }

        public string TinhTrang { get; set; }

        public bool DaXoa { get; set; }

        //khóa ngoại
        public string MaChuong { get; set; }
    }
}
