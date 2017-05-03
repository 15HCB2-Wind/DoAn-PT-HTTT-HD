using System.Web;
using System.Web.Optimization;

namespace FrontEnd_DrinkSmile
{
    public class BundleConfig
    {
        // For more information on bundling, visit http://go.microsoft.com/fwlink/?LinkId=301862
        public static void RegisterBundles(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/bundles/jquery").Include(
                        "~/Scripts/jquery-{version}.js",
                        "~/Scripts/jquery.cookie.js"));

            bundles.Add(new ScriptBundle("~/bundles/jqueryval").Include(
                        "~/Scripts/jquery.validate*"));

            // Use the development version of Modernizr to develop with and learn from. Then, when you're
            // ready for production, use the build tool at http://modernizr.com to pick only the tests you need.
            bundles.Add(new ScriptBundle("~/bundles/js").Include(
                      //"~/Scripts/respond.min.js",
                      //"~/Scripts/bootstrap.min.js",
                      "~/Scripts/compiled.min.js",
                      "~/Scripts/style.js"));

            bundles.Add(new StyleBundle("~/Content/css").Include(
                      "~/Content/bootstrap.min.css",
                      "~/Content/font-awesome.min.css",
                      "~/Content/compiled.min.css",
                      "~/Content/style.css"));

            //BundleTable.EnableOptimizations = true;
        }
    }
}
