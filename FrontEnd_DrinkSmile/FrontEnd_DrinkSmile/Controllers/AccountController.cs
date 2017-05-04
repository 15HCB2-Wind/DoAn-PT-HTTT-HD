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
                foreach (var cookie in Request.Cookies.AllKeys)
                {
                    var c = Request.Cookies[cookie];
                    c.Expires = DateTime.Now.AddDays(-1);
                    Response.Cookies.Add(c);
                }
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