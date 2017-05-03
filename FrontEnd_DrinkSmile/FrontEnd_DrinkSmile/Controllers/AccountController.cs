using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Controllers
{
    public class AccountController : Controller
    {
        [AllowAnonymous]
        public ActionResult Login()
        {
            if (Request.Cookies["manv"] != null)
                return RedirectToAction("Index", "Home");
            return View();
        }

        public ActionResult LogOff()
        {
            if (Request.Cookies["manv"] != null)
            {
                HttpCookie MaNV = new HttpCookie("manv");
                MaNV.Expires = DateTime.Now.AddDays(-1d);
                Response.Cookies.Add(MaNV);

                HttpCookie Role = new HttpCookie("role");
                Role.Expires = DateTime.Now.AddDays(-1d);
                Response.Cookies.Add(Role);
            }
            return RedirectToAction("Login", "Account");
        }

        [AllowAnonymous]
        public ActionResult ForgotPassword()
        {
            return View();
        }
    }
}