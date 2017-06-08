/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.DataAccess.Agency.AgencyRequest;
import Ultility.HibernateUtil;
import Ultility.Security;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import pojos.Chinhanh;
import pojos.Khosua;
import pojos.Nhanvien;

/**
 *
 * @author Shin'sLaptop
 */
public class ChiNhanhAdapter {
    public static void getNewID(String areaid, Chinhanh obj) {
        if (CounterAdapter.updateCounter("indexChinhanh")){
            obj.setMachinhanh(String.format("%s%s%03d", areaid, "CN", CounterAdapter.getAreaCounter().getIndexChinhanh()));
        }
    }
    
    public static boolean isValidName(String name) {
        return HibernateUtil.getSingle("from Chinhanh where tenchinhanh = :p0", new Object[]{name}).size() <= 0;
    }
    
    public static List<Chinhanh> getAll(){
        return HibernateUtil.getList("from Chinhanh", null);
    }
    
    public static List<Chinhanh> getFromArea(Object areaid){
        return HibernateUtil.getList("from Chinhanh where machinhanh like :p0", new Object[] { areaid + "%" });
    }
    
    public static Chinhanh getSingle(Object id){
        List<Chinhanh> list = HibernateUtil.getSingle("from Chinhanh where machinhanh = :p0", new Object[]{ id });
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }
    
    public static boolean add(AgencyRequest req) {
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            
            //add agency
            getNewID(req.AreaId, req.CN_Data);
            req.CN_Data.setDaxoa(false);
            req.CN_Data.setTinhtrang("Đang hoạt động.");
            
            //add manager
            Nhanvien manager;
            if (req.Mode){
                manager = NhanVienAdapter.getSingleFullInfo(req.QL_Data1);
                manager.setMachinhanh(req.CN_Data.getMachinhanh());
                manager.setMaphanquyen("PQ002");
            }else{
                manager = new Nhanvien();
                NhanVienAdapter.getNewID(req.AreaId, manager);
                manager.setHoten(req.QL_Data2.getHoten());
                manager.setEmail(req.QL_Data2.getEmail());
                manager.setMachinhanh(req.CN_Data.getMachinhanh());
                //default
                manager.setNgayvaolam(new Date());
                manager.setMaphanquyen("PQ002");
                manager.setDaxoa(false);
                manager.setTentaikhoan(manager.getEmail());
                manager.setMatkhau(Security.Encrypt(manager.getTentaikhoan()));
                //===
            }
            
            //add default-warehouse
            Khosua warehouse;
            warehouse = new Khosua();
            KhoSuaAdapter.getNewID(req.AreaId, warehouse);
            warehouse.setMachinhanh(req.CN_Data.getMachinhanh());
            warehouse.setDiachi(req.CN_Data.getDiachi());
            warehouse.setTenkho("Kho tạm - " + req.CN_Data.getTenchinhanh());
            warehouse.setSucchua(100d);
            //default
            warehouse.setLuongsuaco(0d);
            warehouse.setDaxoa(false);
            warehouse.setTinhtrang("Đang được sử dụng.");
            //===
            
            //update agency-manager
            req.CN_Data.setQuanly(manager.getManhanvien());
            
            //update agency-default-warehouse
            req.CN_Data.setKhotam(warehouse.getMakho());
            
            //save
            session.saveOrUpdate(req.CN_Data);
            session.saveOrUpdate(manager);
            session.saveOrUpdate(warehouse);
            
            session.getTransaction().commit();
            success = true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return success;
    }

    public static int delete(Chinhanh id) {
        return HibernateUtil.execute("update Chinhanh set daxoa = :p1, tinhtrang = :p2 where machinhanh = :p0", new Object[]{ id.getMachinhanh(), true, "Ngừng hoạt động." });
    }
    
    public static int recover(Chinhanh id) {
        return HibernateUtil.execute("update Chinhanh set daxoa = :p1, tinhtrang = :p2 where machinhanh = :p0", new Object[]{ id.getMachinhanh(), false, "Đang hoạt động." });
    }

    public static boolean update(AgencyRequest req) {
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            
            //update agency
            Chinhanh cn = getSingle(req.CN_Data.getMachinhanh());
            cn.setTenchinhanh(req.CN_Data.getTenchinhanh());
            cn.setSodt(req.CN_Data.getSodt());
            cn.setDiachi(req.CN_Data.getDiachi());
            cn.setTinhtrang(req.CN_Data.getTinhtrang());
            
            //update manager
            if (req.Mode){
                if (!req.QL_Data1.equals(cn.getMachinhanh())){
                    Nhanvien old = NhanVienAdapter.getSingleFullInfo(cn.getQuanly());
                    old.setDaxoa(true);
                    //
                    Nhanvien manager = NhanVienAdapter.getSingleFullInfo(req.QL_Data1);
                    manager.setMachinhanh(cn.getMachinhanh());
                    manager.setMaphanquyen("PQ002");
                    cn.setQuanly(manager.getManhanvien());
                    session.saveOrUpdate(manager);
                }
            }else{
                Nhanvien manager = new Nhanvien();
                NhanVienAdapter.getNewID(req.AreaId, manager);
                manager.setHoten(req.QL_Data2.getHoten());
                manager.setEmail(req.QL_Data2.getEmail());
                manager.setMachinhanh(req.CN_Data.getMachinhanh());
                //default
                manager.setNgayvaolam(new Date());
                manager.setMaphanquyen("PQ002");
                manager.setDaxoa(false);
                manager.setTentaikhoan(manager.getEmail());
                manager.setMatkhau(Security.Encrypt(manager.getTentaikhoan()));
                //===
                cn.setQuanly(manager.getManhanvien());
                session.saveOrUpdate(manager);
            }
            
            session.saveOrUpdate(cn);
            
            session.getTransaction().commit();
            success = true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return success;
    }
}
