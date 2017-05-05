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
public class KhoSuaAdapter {
    public static List<Khosua> getAll() {
	return HibernateUtil.getList("from Khosua", null);
    }
    
    public static List<Khosua> getSingle(String id){
        return HibernateUtil.getSingle("from Khosua where makho = :p0", new Object[]{ id });
    }
    
    public static List<Khosua> getListInAgency(String id){
        return HibernateUtil.getSingle("from Khosua where machinhanh = :p0", new Object[]{ id });
    } 
}
