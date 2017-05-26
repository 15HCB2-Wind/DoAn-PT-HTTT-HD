using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Manage.Controllers
{
    public class BarnController : Controller
    {
        // GET: Manage/Barn
        public ActionResult Index()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Barn/Index" });
        }

        public ActionResult Create()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Barn/Create" });
        }

        public ActionResult Edit(string id)
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2")
                {
                    if (id == null) return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
                    ViewBag.MaCT = id;
                    return View();
                }
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Barn/Edit/" + id });
        }
    }
}