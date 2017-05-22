/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Ultility.HibernateUtil;
import Ultility.Security;
import java.util.List;
import pojos.Chinhanh;

/**
 *
 * @author Shin'sLaptop
 */
public class ChiNhanhAdapter {
    
    
     private static void getNewID(Chinhanh obj) {
        List<Chinhanh> list = HibernateUtil.getSingle("from Chinhanh order by machinhanh desc",null);//lay ds chinhanh tu duoi len
        String machu= "";
         if (list.size()>0) {
            Chinhanh cn = list.get(list.size()-1);
            machu = cn.getMachinhanh().substring(2);//CN00001 -> 00001
         }
         else
         {
              machu = "0";
         }
        int maso = Integer.parseInt(machu);
        obj.setMachinhanh(String.format("CN%05d", maso + 1));
    }
    
    public static List<Chinhanh> getAll(){
        return HibernateUtil.getList("from Chinhanh order by machinhanh asc", null);
    }
    public static Chinhanh getSingle(Object userid){
        List<Chinhanh> list = HibernateUtil.getSingle("from Chinhanh where machinhanh = :p0", new Object[]{ userid });
        if (list.size()>0) {
            Chinhanh cn = list.get(0);
            return cn;
        }
        return null;
    }
    
    public static boolean add(Chinhanh obj) {
        getNewID(obj);
        return HibernateUtil.save(obj);
    }

    public static int delete(Chinhanh id) {
        return HibernateUtil.execute("update Chinhanh set daxoa = :p1 where machinhanh = :p0", new Object[]{ id.getMachinhanh(), true });
    }
    
    public static int recover(Chinhanh id) {
        return HibernateUtil.execute("update Chinhanh set daxoa = :p1 where machinhanh = :p0", new Object[]{ id.getMachinhanh(), false });
    }

    public static boolean update(Chinhanh obj) {
        Chinhanh cn = getSingle(obj.getMachinhanh());
        if(!obj.getTenchinhanh().equals(""))
            cn.setTenchinhanh(obj.getTenchinhanh());
        if(!obj.getSodt().equals(""))
            cn.setSodt(obj.getSodt());
        if(!obj.getDiachi().equals(""))
            cn.setDiachi(obj.getDiachi());
        if(!obj.getTinhtrang().equals(""))
            cn.setTinhtrang(obj.getTinhtrang());
        if(obj.getDaxoa()!= null)
            cn.setDaxoa(obj.getDaxoa());
        if(!obj.getQuanly().equals(""))
            cn.setQuanly(obj.getQuanly());
        return HibernateUtil.update(cn);
    }
}
