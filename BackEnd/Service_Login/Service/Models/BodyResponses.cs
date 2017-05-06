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

    public class LoginResponse : BodyResponse { }

    public class CPassResponse : BodyResponse
    {
        public List<string> OldPassword_Errors { get; set; }
        public List<string> NewPassword_Errors { get; set; }

        public CPassResponse()
        {
            OldPassword_Errors = new List<string>();
            NewPassword_Errors = new List<string>();
        }
    }

    public class CFPassResponse : BodyResponse
    {
        public List<string> NewPassword_Errors { get; set; }

        public CFPassResponse()
        {
            NewPassword_Errors = new List<string>();
        }
    }

    public class FPassResponse : BodyResponse { }

    public class SyncResponse : BodyResponse { }

    public class CheckTokenResponse : BodyResponse { }

    public class ChangePasswordResponse : BodyResponse { }
}