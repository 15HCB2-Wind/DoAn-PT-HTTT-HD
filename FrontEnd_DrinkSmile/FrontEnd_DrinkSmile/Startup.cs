using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(FrontEnd_DrinkSmile.Startup))]
namespace FrontEnd_DrinkSmile
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
