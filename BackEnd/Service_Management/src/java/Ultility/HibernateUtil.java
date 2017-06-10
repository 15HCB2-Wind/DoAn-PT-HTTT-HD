package Ultility;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javafx.collections.ObservableList;
import org.hibernate.Query;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void tryConnectDatabase(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
    }

    public static <T> List<T> getList(String hql, Object[] params){
        List<T> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery(hql);
            if (params != null){
                int i = 0;
                for (Object p : params) {
                    q.setParameter("p" + i++, p);
                }
            }
            result = (List<T>) q.list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> List<T> getList(int skip, int limit, String hql, Object[] params){
        List<T> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery(hql);
            if (params != null){
                int i = 0;
                for (Object p : params) {
                    q.setParameter("p" + i++, p);
                }
            }
            result = (List<T>) q.setFirstResult(skip).setMaxResults(limit).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> List<T> getSingle(String hql, Object[] params){
        List<T> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery(hql);
            if (params != null){
                int i = 0;
                for (Object p : params) {
                    q.setParameter("p" + i++, p);
                }
            }
            result = q.setMaxResults(1).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public static int execute(String hql, Object[] params){
        int result = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery(hql);
            if (params != null){
                int i = 0;
                for (Object p : params) {
                    q.setParameter("p" + i++, p);
                }
            }
            result = q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> boolean save(T obj){
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
            success = true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return success;
    }

    public static <T> boolean saveList(ObservableList<T> obj){
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            for (T t : obj) {
                session.save(t);
            }
            session.getTransaction().commit();
            success = true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return success;
    }

    public static <T> boolean saveOrUpdate(T obj){
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(obj);
            session.getTransaction().commit();
            success = true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return success;
    }

    public static <T> boolean delete(T obj){
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
            success = true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return success;
    }

    public static <T> boolean update(T obj){
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
            success = true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return success;
    }

    public static <T> boolean refresh(T obj){
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.refresh(obj);
            session.getTransaction().commit();
            success = true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return success;
    }

    public static int count(String hql){
        int result = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Object obj = session.createQuery(hql).uniqueResult();
            result = Integer.parseInt(obj.toString());
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
    
    //SQL
    public static <T> List<T> getListSQL(String sql, Object[] params){
        List<T> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createSQLQuery(sql);
            if (params != null){
                int i = 0;
                for (Object p : params) {
                    q.setParameter("p" + i++, p);
                }
            }
            result = (List<T>) q.list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> List<T> getListSQL(int skip, int limit, String sql, Object[] params){
        List<T> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createSQLQuery(sql);
            if (params != null){
                int i = 0;
                for (Object p : params) {
                    q.setParameter("p" + i++, p);
                }
            }
            result = (List<T>) q.setFirstResult(skip).setMaxResults(limit).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> List<T> getSingleSQL(String sql, Object[] params){
        List<T> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createSQLQuery(sql);
            if (params != null){
                int i = 0;
                for (Object p : params) {
                    q.setParameter("p" + i++, p);
                }
            }
            result = q.setMaxResults(1).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public static int executeSQL(String sql, Object[] params){
        int result = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createSQLQuery(sql);
            if (params != null){
                int i = 0;
                for (Object p : params) {
                    q.setParameter("p" + i++, p);
                }
            }
            result = q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
    
    public static int countSQL(String sql){
        int result = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Object obj = session.createSQLQuery(sql).uniqueResult();
            result = Integer.parseInt(obj.toString());
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
}
