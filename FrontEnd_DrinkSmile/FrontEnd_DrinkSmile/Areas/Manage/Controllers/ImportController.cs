using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Manage.Controllers
{
    public class ImportController : Controller
    {
        //
        // GET: /Manage/Import/
        public ActionResult Index()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Import/Index" });
        }

        public ActionResult Import()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Import/Import" });
        }

        public ActionResult Edit(string id)
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3")
                {
                    if (id == null) return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
                    ViewBag.machungtu = id;
                    return View();
                }
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Import/Index" });
        }
	}
}