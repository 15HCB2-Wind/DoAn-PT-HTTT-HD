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

    public class CPassBodyResponse : BodyResponse
    {
        public List<string> OldPassword_Errors { get; set; }
        public List<string> NewPassword_Errors { get; set; }

        public CPassBodyResponse()
        {
            OldPassword_Errors = new List<string>();
            NewPassword_Errors = new List<string>();
        }
    }

    public class CFPassBodyResponse : BodyResponse
    {
        public List<string> NewPassword_Errors { get; set; }

        public CFPassBodyResponse()
        {
            NewPassword_Errors = new List<string>();
        }
    }

    public class FPassBodyResponse : BodyResponse { }

    public class SyncBodyResponse : BodyResponse { }

    public class CheckTokenBodyResponse : BodyResponse { }
}