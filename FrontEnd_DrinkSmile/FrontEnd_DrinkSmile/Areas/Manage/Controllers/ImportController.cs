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
        // GET: /Manage/BillImportCow/
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Import()
        {
            return View();
        }

        public ActionResult Edit(string id)
        {
            if (id == null) return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            ViewBag.machungtu = id;
            return View();
        }
	}
}