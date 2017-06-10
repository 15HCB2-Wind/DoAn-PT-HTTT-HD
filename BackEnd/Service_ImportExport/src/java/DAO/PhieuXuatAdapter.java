/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BusinessHandler.PhieuXuatBUS;
import Config.Configs;
import Models.BodyResponse;
import Models.DataAccess.Export.InsertExportRequest;
import Models.DataAccess.Export.SelectExportRequest;
import Ultility.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import pojos.Chitietxuatsua;
import pojos.Phieuxuat;

/**
 *
 * @author Shin-Desktop
 */
public class PhieuXuatAdapter {
    private static void getNewID(Phieuxuat obj) {
        if (CounterAdapter.updateCounter("indexPhieuxuat")){
            obj.setMachungtu(String.format("%s%s%05d", Configs.AREA_ID, "PX", CounterAdapter.getAreaCounter().getIndexPhieuxuat()));
        }
    }

    public static List<Phieuxuat> getAll(Object id) {
        return HibernateUtil.getList("from Phieuxuat where macn = :p0 order by ngaylap desc, ngayxuat asc, machungtu desc", new Object[] { id });
    }
    
    public static Phieuxuat getSingle(Object userid){
        List<Phieuxuat> list = HibernateUtil.getSingle("from Phieuxuat where machungtu = :p0", new Object[]{ userid });
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }
    
    public static boolean add(InsertExportRequest request) {
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            getNewID(request.Data);
            request.Data.setNgaylap(new Date());
            request.Data.setDahuy(false);
            session.save(request.Data);

            for (Chitietxuatsua Detail : request.Details) {
                Detail.getId().setMachungtu(request.Data.getMachungtu());
                session.save(Detail);
            }

            session.getTransaction().commit();
            success = true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return success;
    }
    
    public static int delete(Object machungtu) {
        return HibernateUtil.execute("update Phieuxuat set ngayxuat = :p1 where machungtu = :p0", new Object[]{ machungtu, true });
    }

    public static int exportNow(SelectExportRequest req, BodyResponse res) {
        List<Chitietxuatsua> listCT = HibernateUtil.getList("from Chitietxuatsua where id.machungtu = :p0", new Object[] { req.machungtu });
        
        for (Chitietxuatsua ct : listCT) {
            if (!PhieuXuatBUS.updateMilk_isReady2Sub(ct.getId().getMakho(), ct.getLuongsuaxuat(), req.Token)){
                res.IsError = true;
                res.Errors.add(String.format("Kho %s không có đủ sửa để xuất!", ct.getId().getMakho()));
                return -2;
            }
        }
        
        for (Chitietxuatsua ct : listCT) {
            PhieuXuatBUS.updateMilk(ct.getId().getMakho(), ct.getLuongsuaxuat(), req.Token);
        }
        return HibernateUtil.execute("update Phieuxuat set ngayxuat = :p1 where machungtu = :p0", new Object[]{ req.machungtu, new Date() });
    }
}
