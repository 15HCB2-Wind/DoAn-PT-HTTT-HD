/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Configs;
import Ultility.HibernateUtil;
import java.util.Date;
import java.util.List;
import pojos.Bo;

/**
 *
 * @author 19101994
 */
public class BoAdapter {
    private static void getNewID(Bo obj) {
        if (CounterAdapter.updateCounter("indexBo")){
            obj.setMabo(String.format("%s%s%06d", Configs.AREA_ID, "B", CounterAdapter.getAreaCounter().getIndexBo()));
        }
    }
    
    public static Bo getSingle(Object id){
        List<Bo> list = HibernateUtil.getSingle("from Bo where mabo = :p0", new Object[]{ id });
        if (list.size() > 0) {
            Bo result = list.get(0);
            return result;
        }
        return null;
    }
    
    public static List<Bo> getAllOfAgency(Object id){
        return HibernateUtil.getList("select a from Bo a, Chuongtrai b where a.machuong = b.machuong and b.machinhanh = :p0", new Object[]{ id });
    }
    
    public static List<Bo> getAllOfBarn(Object id){
        return HibernateUtil.getList("from Bo where machuong = :p0", new Object[]{ id });
    }
    
    public static boolean add(Bo obj) {
        getNewID(obj);
        obj.setNgaynhan(new Date());
        obj.setDaxoa(false);
        obj.setTinhtrang("Khỏe mạnh.");
        return HibernateUtil.save(obj);
    }

    public static int delete(Object id) {
        return HibernateUtil.execute("update Bo set daxoa = :p1, tinhtrang = :p2 where mabo = :p0", new Object[]{ id, true, "- - - - -" });
    }
    
    public static int recover(Object id) {
        return HibernateUtil.execute("update Bo set daxoa = :p1, tinhtrang = :p2 where mabo = :p0", new Object[]{ id, false, "Khỏe mạnh." });
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
    
    public static boolean updateState(String id, String newState) {
        return HibernateUtil.execute("update Bo set tinhtrang = :p1 where mabo = :p0", new Object[]{ id, newState }) > 0;
    }
}
