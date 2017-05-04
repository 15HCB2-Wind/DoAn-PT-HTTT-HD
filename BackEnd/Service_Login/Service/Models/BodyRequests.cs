using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Service.Models
{
    public class LoginBodyRequest
    {
        public string Username { get; set; }
        public string Password { get; set; }
    }

    public class FPassBodyRequest
    {
        public string Email { get; set; }
    }

    public class CPassBodyRequest
    {
        
    }

    public class SyncBodyRequest
    {
        public string Id { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public string PermissionId { get; set; }
    }
}