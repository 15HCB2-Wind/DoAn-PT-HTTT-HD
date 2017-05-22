using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Manage.Controllers
{
    public class AssignmentController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Create()
        {
            return View();
        }

        public ActionResult Update(string getmaphancong)
        {
            ViewBag.getmaphancong = getmaphancong;
            return View();
        }
    }
}