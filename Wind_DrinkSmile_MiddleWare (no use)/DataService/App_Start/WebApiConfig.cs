using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;

namespace DataService
{
    public static class WebApiConfig
    {
        public static string AreaId
        {
            get
            {
                return Configs.PreloadConfigurations.GetInstance().DatabaseAreaId;
            }
        }

        public static void Register(HttpConfiguration config)
        {
            // Web API configuration and services

            // Web API routes
            config.MapHttpAttributeRoutes(new CentralizedPrefixProvider(AreaId));
            
            //config.Routes.MapHttpRoute(
            //    name: "DefaultApi",
            //    routeTemplate: "api/{controller}/{id}",
            //    defaults: new { id = RouteParameter.Optional }
            //);
        }
    }
}
