using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Service.Models
{
    public abstract class BodyRequest
    {
        public string Token { get; set; }
        public object TokenPredicate1 { get; set; }
        public object TokenPredicate2 { get; set; }
    }

    public class LoginBodyRequest : BodyRequest
    {
        public string Username { get; set; }
        public string Password { get; set; }
    }

    public class FPassBodyRequest : BodyRequest
    {
        public string Email { get; set; }
    }

    public class CPassBodyRequest : BodyRequest
    {
        public string OldPassword { get; set; }
        public string NewPassword { get; set; }
    }

    public class CFPassBodyRequest : BodyRequest
    {
        public string NewPassword { get; set; }
    }

    public class SyncBodyRequest : BodyRequest
    {
        public string Id { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public int PermissionLevel { get; set; }
        public int SyncType { get; set; }
    }

    public class CheckTokenBodyRequest : BodyRequest { }
}