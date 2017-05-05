/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Ultility.HibernateUtil;
import java.util.List;
import pojos.Phanquyen;

/**
 *
 * @author Shin'sLaptop
 */
public class PhanQuyenAdapter {
    public static List<Phanquyen> getAll() {
	return HibernateUtil.getList("from Phanquyen", null);
    }
    
    public static Phanquyen getSingle(String id){
        List<Phanquyen> list = HibernateUtil.getSingle("from Phanquyen where maphanquyen = :p0", new Object[]{ id });
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}
