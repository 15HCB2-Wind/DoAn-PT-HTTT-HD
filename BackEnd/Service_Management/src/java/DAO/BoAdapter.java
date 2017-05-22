/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Ultility.HibernateUtil;
import java.util.List;
import pojos.Bo;

/**
 *
 * @author Shin'sLaptop
 */
public class BoAdapter {
    private static void getNewID(Bo obj) {
        int count = HibernateUtil.count("select count(mabo) from Bo");
        obj.setMabo(String.format("COW%05d", count + 1));
    }
    
    public static Bo getSingle(String id){
        List<Bo> list = HibernateUtil.getSingle("from Bo where mabo = :p0", new Object[]{ id });
        if (list.size() > 0) {
            Bo result = list.get(0);
            return result;
        }
        return null;
    }
    
    public static List<Bo> getAllOfAgency(String id){
        return HibernateUtil.getList("from Bo where machinhanh <> :p0", new Object[]{ id });
    }
    
    public static List<Bo> getAllOfBarn(String id){
        return HibernateUtil.getList("from Bo where machuong <> :p0", new Object[]{ id });
    }
    
    public static boolean add(Bo obj) {
        getNewID(obj);
        obj.setDaxoa(false);
        return HibernateUtil.save(obj);
    }

    public static int delete(Object userid) {
        return HibernateUtil.execute("update Bo set daxoa = :p1 where mabo = :p0", new Object[]{ userid, true });
    }
    
    public static int recover(Object userid) {
        return HibernateUtil.execute("update Bo set daxoa = :p1 where mabo = :p0", new Object[]{ userid, false });
    }

    public static boolean update(Bo obj) {
        Bo updated = getSingle(obj.getMabo());
        updated.setCoditat(obj.getCoditat());
        updated.setMachip(obj.getMachip());
        updated.setMachuong(obj.getMachuong());
        updated.setMausac(obj.getMausac());
        updated.setNhandang(obj.getNhandang());
        updated.setTinhtrang(obj.getTinhtrang());
        return HibernateUtil.update(updated);
    }
}
