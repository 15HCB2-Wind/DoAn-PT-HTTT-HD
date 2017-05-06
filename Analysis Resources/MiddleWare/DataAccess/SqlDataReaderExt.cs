using System.Data.SqlClient;

public static class SqlDataReaderExt
{
    public static T GetValueDefault<T>(this SqlDataReader reader, int index)
    {
        return reader.IsDBNull(index) ? default(T) : (T)reader.GetValue(index);
    }

    public static T GetValueDefault<T>(this SqlDataReader reader, int index, T defaultValue)
    {
        return reader.IsDBNull(index) ? defaultValue : (T)reader.GetValue(index);
    }
}
