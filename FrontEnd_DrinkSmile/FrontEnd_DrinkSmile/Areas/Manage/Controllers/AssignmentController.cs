using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Manage.Controllers
{
    public class AssignmentController : Controller
    {
        public ActionResult Index()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Assignment/Index" });
        }

        public ActionResult Create()
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2") return View();
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Assignment/Create" });
        }

        public ActionResult Update(string getmaphancong)
        {
            if (Request.Cookies["token"] != null)
            {
                if (Request.Cookies["role"].Value == "2")
                {
                    ViewBag.getmaphancong = getmaphancong;
                    return View();
                }
                return RedirectToAction("Index");
            }
            return RedirectToAction("Login", "Account", new { area = "", ReturnUrl = "/Manage/Assignment/Index" });
        }
    }
}