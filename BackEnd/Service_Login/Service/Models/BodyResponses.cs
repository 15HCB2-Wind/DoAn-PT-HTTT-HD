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

    public class LoginBodyResponse : BodyResponse { }

    public class CPassBodyRessponse : BodyResponse
    {
        public List<string> OldPassword_Errors { get; set; }
        public List<string> NewPassword_Errors { get; set; }
    }

    public class CFPassBodyRessponse : BodyResponse
    {
        public List<string> NewPassword_Errors { get; set; }
    }

    public class FPassBodyRessponse : BodyResponse { }
}