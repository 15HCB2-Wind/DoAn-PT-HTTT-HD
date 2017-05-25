/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Ultility.HibernateUtil;
import java.util.List;
import pojos.Phieunhapbo;

/**
 *
 * @author Shin-Desktop
 */
public class PhieuNhapBoAdapter {
    private static void getNewID(Phieunhapbo obj) {
        int count = HibernateUtil.count("select count(machungtu) from Phieunhapbo");
        obj.setMachungtu(String.format("MCT%05d", count + 1));
    }
    
    public static Phieunhapbo getSingle(Object userid){
        List<Phieunhapbo> list = HibernateUtil.getSingle("from Phieunhapbo where machungtu = :p0", new Object[]{ userid });
        if (list.size()>0) {
            Phieunhapbo phieunhapbo = list.get(0);
            phieunhapbo.setMancc(null);
            phieunhapbo.setManv(null);
            return phieunhapbo;
        }
        return null;
    }
    
    public static List<Phieunhapbo> getAll(){
        return HibernateUtil.getList("from Phieunhapbo", null);
    }
    
    public static boolean add(Phieunhapbo obj) {
        getNewID(obj);
        obj.setDahuy(false);
        return HibernateUtil.save(obj);
    }

    public static int delete(Phieunhapbo userid) {
        return HibernateUtil.execute("update Phieunhapbo set dahuy = :p1 where machungtu = :p0", new Object[]{ userid, true });
    }
    
    public static int recover(Object userid) {
        return HibernateUtil.execute("update Nhanvien set dahuy = :p1 where machungtu = :p0", new Object[]{ userid, false });
    }

    public static boolean update(Phieunhapbo obj) {
        Phieunhapbo pnb = getSingle(obj.getMachungtu());
        pnb.setNgaylap(obj.getNgaylap());
        pnb.setNgaynhap(obj.getNgaynhap());
        //nv.setTentaikhoan(obj.getTentaikhoan());
        //nv.setMatkhau(obj.getMatkhau());
        pnb.setDahuy(obj.getDahuy());
        pnb.setMancc(obj.getMancc());
        pnb.setSoluong(obj.getSoluong());
        pnb.setManv(obj.getManv());
        return HibernateUtil.update(pnb);
    }

}
