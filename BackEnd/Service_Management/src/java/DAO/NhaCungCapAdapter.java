    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Configs;
import Ultility.HibernateUtil;
import java.util.List;
import pojos.Nhacungcap;

/**
 *
 * @author Shin'sLaptop
 */
public class NhaCungCapAdapter {
    private static void getNewID(Nhacungcap obj) {
        if (CounterAdapter.updateCounter("indexNhacungcap")){
            obj.setManhacungcap(String.format("%s%s%04d", Configs.AREA_ID, "NCC", CounterAdapter.getAreaCounter().getIndexNhacungcap()));
        }
    }
    
    public static List<Nhacungcap> getAll(){
        return HibernateUtil.getList("from Nhacungcap order by manhacungcap asc", null);
    }
    
    public static boolean add(Nhacungcap obj) {
        getNewID(obj);
        obj.setTinhtrang("Đang hoạt động.");
        return HibernateUtil.save(obj);
    }   
    
     public static Nhacungcap getSingle(Object id){
        List<Nhacungcap> list = HibernateUtil.getSingle("from Nhacungcap where manhacungcap = :p0", new Object[]{ id });
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }
    public static boolean update(Nhacungcap obj) {
        Nhacungcap ncc = getSingle(obj.getManhacungcap());
        if(!obj.getTen().equals(""))
            ncc.setTen(obj.getTen());
        if(!obj.getDiachi().equals(""))
            ncc.setDiachi(obj.getDiachi());
        if(!obj.getTinhtrang().equals(""))
            ncc.setTinhtrang(obj.getTinhtrang());
        return HibernateUtil.update(ncc);
    }
}
