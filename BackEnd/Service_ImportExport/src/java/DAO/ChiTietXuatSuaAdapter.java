/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Ultility.HibernateUtil;
import java.util.List;
import pojos.Chitietxuatsua;

/**
 *
 * @author Shin-Desktop
 */
public class ChiTietXuatSuaAdapter {
    public static List<Chitietxuatsua> getAll() {
        return HibernateUtil.getList("from Chitietxuatsua", null);
    }
    
    public static Chitietxuatsua getSingle(Object userid){
        List<Chitietxuatsua> list = HibernateUtil.getSingle("from Chitietxuatsua where machungtu = :p0", new Object[]{ userid });
        if (list.size()>0) {
            Chitietxuatsua chitietxuatsua = list.get(0);
            chitietxuatsua.setLuongsuaxuat(null);
            return chitietxuatsua;
        }
        return null;
    }
    
    private static void getNewID(Chitietxuatsua obj) {
        int count = HibernateUtil.count("select count(machungtu) from Phieuxuat");
        obj.setMachungtu(String.format("PX%05d", count+1));
    }
    
    public static boolean add(Chitietxuatsua obj) {
        getNewID(obj);
//        obj.setLuongsuaxuat(null);
        return HibernateUtil.save(obj);
    }

//    public static int delete(Object userid) {
//        return HibernateUtil.execute("update Chitietxuatsua set dahuy = :p1 where machungtu = :p0", new Object[]{ userid, true });
//    }
    
//    public static int recover(Object userid) {
//        return HibernateUtil.execute("update Chitietxuatsua set dahuy = :p1 where machungtu = :p0", new Object[]{ userid, false });
//    }

    public static boolean update(Chitietxuatsua obj) {
        Chitietxuatsua pnb = getSingle(obj.getMachungtu());
        pnb.setMakho(obj.getMakho());
        pnb.setLuongsuaxuat(obj.getLuongsuaxuat());
        //nv.setTentaikhoan(obj.getTentaikhoan());
        //nv.setMatkhau(obj.getMatkhau());
        return HibernateUtil.update(pnb);
    }
}
