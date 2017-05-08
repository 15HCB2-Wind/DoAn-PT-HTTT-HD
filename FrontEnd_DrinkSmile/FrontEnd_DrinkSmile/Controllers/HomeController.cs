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
                return View();
            return RedirectToAction("Login", "Account", new { ReturnUrl = "/Home/Index" });
        }
    }
}