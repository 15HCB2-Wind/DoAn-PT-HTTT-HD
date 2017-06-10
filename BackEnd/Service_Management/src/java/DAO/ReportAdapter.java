/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.DataAccess.Report.ReportRequest;
import Ultility.HibernateUtil;

/**
 *
 * @author Shin'sLaptop
 */
public class ReportAdapter {
    public static Object getAllCowOfAgency(ReportRequest request){
        return HibernateUtil.getListSQL("call service_management_drinksmile.tkSoLuongBo(:p0)", new Object[] { request.macn });
    }

    public static Object getAllCowOfCompany(ReportRequest request) {
        return HibernateUtil.getListSQL("call service_management_drinksmile.tkSoLuongBo_AllAgencies()", null);
    }
}
