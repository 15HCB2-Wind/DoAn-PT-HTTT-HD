using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Report.Controllers
{
    public class AnimalCountController : Controller
    {
        // GET: Report/AnimalState
        public ActionResult Index2()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Report/AnimalCount/Index2" });
        }
        public ActionResult Index3()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "3") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Report/AnimalCount/Index3" });
        }
    }
}