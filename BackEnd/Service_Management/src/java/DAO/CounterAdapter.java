/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Configs;
import Ultility.HibernateUtil;
import java.util.List;
import pojos.Counter;

/**
 *
 * @author 19101994
 */
public class CounterAdapter {
    public static Counter getAreaCounter(){
        List<Counter> list = HibernateUtil.getSingle("from Counter where areaid = :p0", new Object[]{ Configs.AREA_ID });
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }
    
    public static Counter getAreaCounter(String areaid){
        List<Counter> list = HibernateUtil.getSingle("from Counter where areaid = :p0", new Object[]{ areaid });
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }
    
    public static boolean updateCounter(String col_name){
        return HibernateUtil.execute(String.format("update Counter set %1$s = (%1$s + 1) where areaid = :p0", col_name), new Object[]{ Configs.AREA_ID }) > 0;
    }
    
    public static boolean updateCounter(String areaid, String col_name){
        return HibernateUtil.execute(String.format("update Counter set %1$s = (%1$s + 1) where areaid = :p0", col_name), new Object[]{ areaid }) > 0;
    }

    public static List<Counter> getAllAreas() {
        return HibernateUtil.getList("from Counter", null);
    }
}
