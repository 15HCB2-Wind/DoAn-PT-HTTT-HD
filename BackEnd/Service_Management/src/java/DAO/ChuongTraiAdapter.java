package DAO;

import Ultility.HibernateUtil;
import java.util.List;
import pojos.*;

public class ChuongTraiAdapter {

    private static void getNewID(Chuongtrai obj) {
        int count = HibernateUtil.count("select count(machuong) from Chuongtrai");
        obj.setMachuong(String.format("CT%05d", count + 1));
    }

    public static Chuongtrai getSingle(Object id) {
        List<Chuongtrai> list = HibernateUtil.getSingle("from Chuongtrai where machuong = :p0", new Object[]{id});
        if (list.size() > 0) {
            Chuongtrai c = list.get(0);
            return c;
        }
        return null;
    }

    public static List<Chuongtrai> getAll(Object id) {
        return HibernateUtil.getList("from Chuongtrai where machinhanh = :p0", new Object[]{id});
    }
    
    public static List<Chuongtrai> getAllAvailables(Object id) {
        return HibernateUtil.getList("from Chuongtrai where machinhanh = :p0 and dangchua < succhua and daxoa = :p1", new Object[]{id, false});
    }

    public static boolean add(Chuongtrai obj) {
        getNewID(obj);
        obj.setDangchua(0);
        obj.setDaxoa(false);
        return HibernateUtil.save(obj);
    }
    
    public static int updateNumber(Object id, int value) {
        return HibernateUtil.execute("update Chuongtrai set dangchua = (dangchua + :p1) where machuong = :p0", new Object[]{id, value});
    }

    public static int delete(Object id) {
        return HibernateUtil.execute("update Chuongtrai set daxoa = :p1 where machuong = :p0", new Object[]{id, true});
    }

    public static int recover(Object id) {
        return HibernateUtil.execute("update Chuongtrai set daxoa = :p1 where machuong = :p0", new Object[]{id, false});
    }

    public static boolean update(Chuongtrai obj) {
        Chuongtrai c = getSingle(obj.getMachuong());
        c.setTenchuong(obj.getTenchuong());
        c.setSucchua(obj.getSucchua());
        c.setTinhtrang(obj.getTinhtrang());
        return HibernateUtil.update(c);
    }
}
