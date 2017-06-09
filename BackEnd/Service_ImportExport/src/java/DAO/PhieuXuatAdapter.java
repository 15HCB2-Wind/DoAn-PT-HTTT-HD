/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Configs;
import Ultility.HibernateUtil;
import java.util.List;
import pojos.Phieuxuat;

/**
 *
 * @author Shin-Desktop
 */
public class PhieuXuatAdapter {
    private static void getNewID(Phieuxuat obj) {
        if (CounterAdapter.updateCounter("indexPhieuxuat")){
            obj.setMachungtu(String.format("%s%s%05d", Configs.AREA_ID, "PX", CounterAdapter.getAreaCounter().getIndexPhieuxuat()));
        }
    }

    public static List<Phieuxuat> getAll() {
        return HibernateUtil.getList("from Phieuxuat", null);
    }
    
    public static Phieuxuat getSingle(Object userid){
        List<Phieuxuat> list = HibernateUtil.getSingle("from Phieuxuat where machungtu = :p0", new Object[]{ userid });
        if (list.size()>0) {
            Phieuxuat phieuxuat = list.get(0);
            phieuxuat.setManv(null);
            return phieuxuat;
        }
        return null;
    }
    
    public static boolean add(Phieuxuat obj) {
        getNewID(obj);
        obj.setDahuy(false);
        return HibernateUtil.save(obj);
    }
    
    public static int delete(Object userid) {
        return HibernateUtil.execute("update Phieuxuat set dahuy = :p1 where machungtu = :p0", new Object[]{ userid, true });
    }
    
    public static int recover(Object userid) {
        return HibernateUtil.execute("update Phieuxuat set dahuy = :p1 where machungtu = :p0", new Object[]{ userid, false });
    }

    public static boolean update(Phieuxuat obj) {
        Phieuxuat pnb = getSingle(obj.getMachungtu());
        pnb.setNgaylap(obj.getNgaylap());
        pnb.setNgayxuat(obj.getNgayxuat());
        //nv.setTentaikhoan(obj.getTentaikhoan());
        //nv.setMatkhau(obj.getMatkhau());
        pnb.setLydo(obj.getLydo());
//        pnb.setDahuy(obj.getDahuy());
        pnb.setTongluongsua(obj.getTongluongsua());
        pnb.setManv(obj.getManv());
        return HibernateUtil.update(pnb);
    }
}
