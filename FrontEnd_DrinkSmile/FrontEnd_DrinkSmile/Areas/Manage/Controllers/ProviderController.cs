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
            return View();
        }
        public ActionResult Create()
        {
            return View();
        }
        public ActionResult Update(string getmanhacungcap)
        {
            ViewBag.getmanhacungcap = getmanhacungcap;
            return View();
        }
    }
}
