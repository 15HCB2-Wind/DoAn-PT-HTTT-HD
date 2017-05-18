using System.Web.Mvc;

namespace FrontEnd_DrinkSmile.Areas.Manage.Controllers
{
    public class StaffController : Controller
    {
        // GET: Manage/Staff
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Create()
        {
            return View();
        }
    }
}