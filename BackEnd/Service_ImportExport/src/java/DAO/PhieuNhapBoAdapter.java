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
import pojos.Phieunhapbo;

/**
 *
 * @author Shin-Desktop
 */
public class PhieuNhapBoAdapter {
    private static void getNewID(Phieunhapbo obj) {
        if (CounterAdapter.updateCounter("indexPhieunhapbo")){
            obj.setMachungtu(String.format("%s%s%05d", Configs.AREA_ID, "PN", CounterAdapter.getAreaCounter().getIndexPhieunhap()));
        }
    }
    
    public static Phieunhapbo getSingle(Object userid){
        List<Phieunhapbo> list = HibernateUtil.getSingle("from Phieunhapbo where machungtu = :p0", new Object[]{ userid });
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }
    
    public static List<Phieunhapbo> getAll(){
        return HibernateUtil.getList("from Phieunhapbo", null);
    }
    
    public static boolean add(Phieunhapbo obj) {
        getNewID(obj);
        obj.setNgaylap(new Date());
        obj.setDahuy(false);
        return HibernateUtil.save(obj);
    }

    public static int delete(Object userid) {
        return HibernateUtil.execute("update Phieunhapbo set dahuy = :p1 where machungtu = :p0", new Object[]{ userid, true });
    }
    
    public static int recover(Object userid) {
        return HibernateUtil.execute("update Phieunhapbo set dahuy = :p1 where machungtu = :p0", new Object[]{ userid, false });
    }

    public static boolean update(Phieunhapbo obj) {
        Phieunhapbo pnb = getSingle(obj.getMachungtu());
        pnb.setNgaynhap(obj.getNgaynhap());
        pnb.setSoluong(obj.getSoluong());
        return HibernateUtil.update(pnb);
    }

}
