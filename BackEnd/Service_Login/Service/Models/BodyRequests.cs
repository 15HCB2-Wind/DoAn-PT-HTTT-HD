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

    public class LoginRequest : BodyRequest
    {
        public string Username { get; set; }
        public string Password { get; set; }
    }

    public class FPassRequest : BodyRequest
    {
        public string Email { get; set; }
    }

    public class CPassRequest : BodyRequest
    {
        public string OldPassword { get; set; }
        public string NewPassword { get; set; }
    }

    public class CFPassRequest : BodyRequest
    {
        public string NewPassword { get; set; }
    }

    public class SyncRequest : BodyRequest
    {
        public string Id { get; set; }
        public string FullName { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public int PermissionLevel { get; set; }
        public string AgencyId { get; set; }
        public string PermissionId { get; set; }
        public int SyncType { get; set; }
    }

    public class CheckTokenRequest : BodyRequest
    {
        public string TokenPassword { get; set; }
        public int Role { get; set; }
    }

    public class ChangePasswordRequest : BodyRequest
    {
        public string UserId { get; set; }
        public string NewPass { get; set; }
    }

    public class ChangeRoleRequest : BodyRequest
    {
        public bool IsUp { get; set; }
        public string AgencyId { get; set; }
    }
}