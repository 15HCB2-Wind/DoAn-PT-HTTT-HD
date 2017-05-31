/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Configs;
import Ultility.HibernateUtil;
import java.util.List;
import pojos.Khosua;


/**
 *
 * @author 19101994
 */
public class KhoSuaAdapter {
    private static void getNewID(Khosua obj) {
        if (CounterAdapter.updateCounter("indexKhosua")){
            obj.setMakho(String.format("%s%s%04d", Configs.AREA_ID, "KS", CounterAdapter.getAreaCounter().getIndexKhosua()));
        }
    }
    
    public static Khosua getSingle(Object id){
        List<Khosua> list = HibernateUtil.getSingle("from Khosua where makho = :p0", new Object[]{ id });
        if (list.size() > 0) {
            Khosua result = list.get(0);
            return result;
        }
        return null;
    }
    
    public static List<Khosua> getAllOfAgency(Object id){
        return HibernateUtil.getList("from Khosua where machinhanh = :p0", new Object[]{ id });
    }
    
    public static boolean add(Khosua obj) {
        getNewID(obj);
        obj.setLuongsuaco(0d);
        obj.setDaxoa(false);
        obj.setTinhtrang("Đang được sử dụng.");
        return HibernateUtil.save(obj);
    }

    public static int delete(Object id) {
        return HibernateUtil.execute("update Khosua set daxoa = :p1, tinhtrang = :p2 where makho = :p0", new Object[]{ id, true, "Ngưng hoạt động." });
    }
    
    public static int recover(Object id) {
        return HibernateUtil.execute("update Khosua set daxoa = :p1, tinhtrang = :p2 where makho = :p0", new Object[]{ id, false, "Đang được sử dụng." });
    }

    public static boolean update(Khosua obj) {
        Khosua updated = getSingle(obj.getMakho());
        updated.setDiachi(obj.getDiachi());
        updated.setSucchua(obj.getSucchua());
        updated.setTenkho(obj.getTenkho());
        updated.setTinhtrang(obj.getTinhtrang());
        return HibernateUtil.update(updated);
    }

    public static boolean transfer(String From, String To, Double Value) {
        if (getSingle(From).getLuongsuaco() >= Value){
            HibernateUtil.execute("update Khosua set luongsuaco = (luongsuaco - :p1) where makho = :p0", new Object[]{ From, Value });
            HibernateUtil.execute("update Khosua set luongsuaco = (luongsuaco + :p1) where makho = :p0", new Object[]{ To, Value });
            return true;
        }
        return false;
    }

    public static Object getAllAvailables(String id) {
        return HibernateUtil.getList("from Khosua where machinhanh = :p0 and daxoa = :p1", new Object[]{id, false});
    }
    
    public static Object getAllAvailablesForTransfer(String id) {
        return HibernateUtil.getList("from Khosua where machinhanh = :p0 and daxoa = :p1 and luongsuaco > 0", new Object[]{id, false});
    }

    public static boolean updateMilk(String id, Double value) {
        return HibernateUtil.execute("update Khosua set luongsuaco = luongsuaco + :p1 where makho = :p0", new Object[]{ id, value }) > 0;
    }
}
