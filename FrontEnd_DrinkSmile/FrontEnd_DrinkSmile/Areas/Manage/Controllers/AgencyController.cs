using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Manage.Controllers
{
    public class AgencyController : Controller
    {
        // GET: Manage/Agency
        public ActionResult Index()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Agency/Index" });
        }
        public ActionResult Create()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Agency/Create" });
        }
        public ActionResult Update(string getchinhanh)
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3")
                {
                    ViewBag.getchinhanh = getchinhanh;
                    return View();
                }
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Assignment/Index" });
        }
    }
}
