using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Service.Models
{
    public abstract class BodyRequest
    {
        public string Token { get; set; }
    }

    public class PhanCongRequest : BodyRequest
    {
        public string IdPhanCong { get; set; }
        public DateTime FromDate { get; set; }
        public DateTime ToDate { get; set; }
        public string Days { get; set; }
        public string IdNhanVien { get; set; }
        public string IdChuong { get; set; }
    }
}