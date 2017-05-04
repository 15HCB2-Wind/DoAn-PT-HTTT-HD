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
            if (Request.Cookies["token"] != null)
                return RedirectToAction("Index", "Home");
            return View();
        }

        [AllowAnonymous]
        public ActionResult ForgotPassword()
        {
            return View();
        }

        public ActionResult LogOff()
        {
            if (Request.Cookies["token"] != null)
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

        public ActionResult ChangePassword()
        {
            if (Request.Cookies["token"] != null)
                return View();
            return RedirectToAction("Login", "Account");
        }
    }
}