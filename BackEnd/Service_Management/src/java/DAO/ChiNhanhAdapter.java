/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Configs;
import Ultility.HibernateUtil;
import java.util.List;
import pojos.Chinhanh;

/**
 *
 * @author Shin'sLaptop
 */
public class ChiNhanhAdapter {
    private static void getNewID(Chinhanh obj) {
        if (CounterAdapter.updateCounter("indexChinhanh")){
            obj.setMachinhanh(String.format("%s%s%03d", Configs.AREA_ID, "CN", CounterAdapter.getAreaCounter().getIndexChinhanh()));
        }
    }
    
    public static boolean isValidName(String name) {
        return HibernateUtil.getSingle("from Chinhanh where tenchinhanh = :p0", new Object[]{name}).size() <= 0;
    }
    
    public static List<Chinhanh> getAll(){
        return HibernateUtil.getList("from Chinhanh order by machinhanh asc", null);
    }
    
    public static Chinhanh getSingle(Object userid){
        List<Chinhanh> list = HibernateUtil.getSingle("from Chinhanh where machinhanh = :p0", new Object[]{ userid });
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }
    
    public static String add(Chinhanh obj) {
        getNewID(obj);
         if(HibernateUtil.save(obj))
         {
             return obj.getMachinhanh();
         }
         return null;
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
