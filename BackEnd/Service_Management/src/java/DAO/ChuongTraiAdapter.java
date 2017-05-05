/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Ultility.HibernateUtil;
import java.util.List;
import pojos.*;

/**
 *
 * @author Shin'sLaptop
 */
public class ChuongTraiAdapter {
    public static List<Chuongtrai> getAll() {
	return HibernateUtil.getList("from Chuongtrai", null);
    }
    
    public static List<Chuongtrai> getSingle(String id){
        return HibernateUtil.getSingle("from Chuongtrai where machuong = :p0", new Object[]{ id });
    }
    
    public static List<Chuongtrai> getListInAgency(String id){
        return HibernateUtil.getSingle("from Chuongtrai where machinhanh = :p0", new Object[]{ id });
    }
}
