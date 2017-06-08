using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Manage.Controllers
{
    public class SaleMilkController : Controller
    {
        //
        // GET: /Manage/SaleMilk/
        public ActionResult Index()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/SaleMilk/Index" });
        }

        public ActionResult Export()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/SaleMilk/Export" });
        }

        public ActionResult Edit(string id)
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2")
                {
                    if (id == null) return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
                    ViewBag.machungtu = id;
                    return View();
                }
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/SaleMilk/Index" });
        }
	}
}