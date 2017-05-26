using System.Net;
using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Manage.Controllers
{
    public class StaffController : Controller
    {
        // GET: Manage/Staff
        public ActionResult Index()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { ReturnUrl = "/Manage/Staff/Index" });
        }

        public ActionResult Create()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { ReturnUrl = "/Manage/Staff/Create" });
        }

        public ActionResult Edit(string id)
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2")
                {
                    if (id == null) return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
                    ViewBag.MaNV = id;
                    return View();
                }
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { ReturnUrl = "/Manage/Staff/Edit/" + id });
        }
    }
}