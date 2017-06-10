/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.DataAccess.Report.ReportRequest;
import Ultility.HibernateUtil;
import java.util.List;

/**
 *
 * @author Shin'sLaptop
 */
public class ReportAdapter {
    public static Object getAllCowOfAgency(ReportRequest request){
        return HibernateUtil.getListSQL("call service_management_drinksmile.tkSoLuongBo(:p0, :p1, :p2, :p3)", new Object[]{request.macn, request.ngaybatdau, request.ngayketthuc, request.dachet });
    }

    public static Object getAllCowOfCompany(ReportRequest request) {
        return HibernateUtil.getListSQL("call service_management_drinksmile.tkSoLuongBo_AllAgencies(:p0, :p1)", new Object[]{ request.ngaybatdau, request.ngayketthuc });
    }
}
