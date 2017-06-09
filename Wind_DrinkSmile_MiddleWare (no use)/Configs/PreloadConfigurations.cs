using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Configs
{
    public class PreloadConfigurations
    {
        private const string FILE_INIT                  = "Config.ini";
        private const string KEY_CONNECTION_STRING      = "DbCString";
        private const string KEY_AREA_ID                = "AId";
        private const string DEFAULT_CONNECTION_STRING  = @"Server=CHIPHONG\SQLEXPRESS_2012;Database=DrinkSmile;Trusted_Connection=True;";
        private const string DEFAULT_AREA_ID            = "hcm";

        #region Singleton
        private PreloadConfigurations()
        {
            var ini = FileIniCreater.Load(FILE_INIT);
            DatabaseConnectionString = ini.Get(KEY_CONNECTION_STRING, DEFAULT_CONNECTION_STRING);
            DatabaseAreaId = ini.Get(KEY_AREA_ID, DEFAULT_AREA_ID);
            ini.Save();
        }
        private static PreloadConfigurations _singleton;
        public static PreloadConfigurations GetInstance()
        {
            if (_singleton == null)
                _singleton = new PreloadConfigurations();
            return _singleton;
        }
        #endregion

        public string DatabaseConnectionString { get; private set; }

        public string DatabaseAreaId { get; private set; }
    }
}
