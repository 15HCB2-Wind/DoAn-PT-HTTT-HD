using DomainData;
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
        public PhanCong Data { get; set; }
    }

    public class CheckTokenRequest : BodyRequest
    {
        public string TokenPassword;
        public int Role;
    }
}