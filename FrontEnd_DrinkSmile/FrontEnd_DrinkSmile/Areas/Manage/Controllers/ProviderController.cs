using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Manage.Controllers
{
    public class ProviderController : Controller
    {
        // GET: Manage/Provider
        public ActionResult Index()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Provider/Index" });
        }
        public ActionResult Create()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Provider/Create" });
        }
        public ActionResult Update(string getmanhacungcap)
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3")
                {
                    ViewBag.getmanhacungcap = getmanhacungcap;
                    return View();
                }
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Provider/Index" });
        }
    }
}
