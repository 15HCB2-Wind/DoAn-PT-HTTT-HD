using System;
using System.Collections.Generic;
using System.Data.SqlClient;

//originer
public abstract class DataAccessOrigin<T>
{
}

//  Getter
public abstract class DataAccessGetOnly<T>
{
    protected abstract string SQL_getAll();

    protected abstract string SQL_getFrom(string identity);

    //==================================================
    //==================================================
    //==================================================

    protected abstract T SqlRow2Object(SqlDataReader row);

    public virtual List<T> GetAll()
    {
        List<T> result = null;
        try
        {
            result = DataProvider.ExecuteReader(SqlRow2Object, SQL_getAll());
        }
        catch (Exception ex)
        {
            throw ex;
        }
        return result;
    }

    public virtual T GetFrom(string identity)
    {
        T result = default(T);
        try
        {
            result = DataProvider.ExecuteReaderOne(SqlRow2Object, SQL_getFrom(identity));
        }
        catch (Exception ex)
        {
            throw ex;
        }
        return result;
    }
}

//  Creator
public abstract class DataAccessInsertUpdate<T> : DataAccessGetOnly<T>
{
    protected abstract string SQL_insert(T addingObject);

    protected abstract string SQL_update(T updatingObject);

    //==================================================
    //==================================================
    //==================================================

    public int Insert(T obj)
    {
        int result = -1;
        try
        {
            result = DataProvider.ExecuteNonQuery(SQL_insert(obj));
        }
        catch (Exception ex)
        {
            throw ex;
        }
        return result;
    }

    public int Update(T obj)
    {
        int result = -1;
        try
        {
            result = DataProvider.ExecuteNonQuery(SQL_update(obj));
        }
        catch (Exception ex)
        {
            throw ex;
        }
        return result;
    }
}

//  Remover
public abstract class DataAccessFull<T> : DataAccessInsertUpdate<T>
{
    protected abstract string SQL_delete(T deletingObject);

    //==================================================
    //==================================================
    //==================================================

    public int Delete(T obj)
    {
        int result = -1;
        try
        {
            result = DataProvider.ExecuteNonQuery(SQL_delete(obj));
        }
        catch (Exception ex)
        {
            throw ex;
        }
        return result;
    }
}