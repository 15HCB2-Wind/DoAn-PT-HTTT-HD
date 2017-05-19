using DomainData;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Service.Models
{
    public abstract class BodyResponse
    {
        public object Data { get; set; }
        public bool IsError { get; set; }
        public List<string> Errors { get; set; }
        public bool IsTokenTimeout { get; set; }

        public BodyResponse()
        {
            Data = null;
            IsError = false;
            Errors = new List<string>();
            IsTokenTimeout = false;
        }
    }

    public class CheckTokenResponse : BodyResponse
    {
        public TokenData Data { get; set; }
    }
}