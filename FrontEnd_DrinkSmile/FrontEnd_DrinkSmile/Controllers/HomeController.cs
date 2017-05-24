using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "1")
                {
                    return RedirectToAction("Index1", "Home");
                }
                else if (Request.Cookies["role"].Value == "2")
                {
                    return RedirectToAction("Index2", "Home");
                }
                else if (Request.Cookies["role"].Value== "3")
                {
                    return RedirectToAction("Index3", "Home");
                }
            }
            return RedirectToAction("Login", "Account", new { ReturnUrl = "/Home/Index" });
        }

        public ActionResult Index1()
        {
            if (Request.Cookies["token"] != null)
                return View();
            return RedirectToAction("Login", "Account", new { ReturnUrl = "/Home/Index" });
        }

        public ActionResult Index2()
        {
            if (Request.Cookies["token"] != null)
                return View();
            return RedirectToAction("Login", "Account", new { ReturnUrl = "/Home/Index" });
        }

        public ActionResult Index3()
        {
            if (Request.Cookies["token"] != null)
                return View();
            return RedirectToAction("Login", "Account", new { ReturnUrl = "/Home/Index" });
        }
    }
}