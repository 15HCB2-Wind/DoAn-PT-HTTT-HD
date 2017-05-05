/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Ultility.HibernateUtil;
import java.util.List;
import pojos.Phieuxuat;

/**
 *
 * @author Shin-Desktop
 */
public class PhieuXuatAdapter {

    public static List<Phieuxuat> getAll() {
        return HibernateUtil.getList("from Phieuxuat", null);
    }
    
}
