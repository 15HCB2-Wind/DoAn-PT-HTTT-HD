using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoSeed
{
    public class CounterRepository : DataAccessOrigin<object>
    {
        public static bool updateCounter(string areaid, string col_name)
        {
            try
            {
                return DataProvider.ExecuteNonQuery(string.Format("update counter set {0} = ({0} + 1) where areaid = '{1}'", col_name, areaid)) > 0;
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        public static int getIndex(string areaid, string col_name)
        {
            try
            {
                return (int)DataProvider.ExecuteScalar(string.Format("select top 1 {0} from counter where areaid = '{1}'", col_name, areaid));
            }
            catch (Exception ex)
            {
                return -1;
            }
        }
    }
}
