/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Other.ChangePasswordRequest;
import Ultility.HibernateUtil;
import Ultility.Security;
import java.util.List;
import pojos.*;

/**
 *
 * @author Shin-Desktop
 */
public class NhanVienAdapter {

    private static void getNewID(Nhanvien obj) {
        int count = HibernateUtil.count("select count(manhanvien) from Nhanvien");
        obj.setManhanvien(String.format("NV%05d", count + 1));
    }

    public static boolean checkEmail(String email) {
        if (HibernateUtil.count("select count(*) from Nhanvien where email = '" + email + "'") == 0) {
            return true;
        }
        return false;
    }

    public static Nhanvien getSingle(Object MaNV) {
        List<Nhanvien> list = HibernateUtil.getSingle("from Nhanvien where manhanvien = :p0", new Object[]{MaNV});
        if (list.size() > 0) {
            Nhanvien nhanvien = list.get(0);
            nhanvien.setTentaikhoan(null);
            nhanvien.setMatkhau(null);
            return nhanvien;
        }
        return null;
    }

    public static Nhanvien getSingleFullInfo(Object MaNV) {
        List<Nhanvien> list = HibernateUtil.getSingle("from Nhanvien where manhanvien = :p0", new Object[]{MaNV});
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static List<Nhanvien> getAll(Object MaCN) {
        List<Nhanvien> list = HibernateUtil.getList("from Nhanvien where machinhanh = :p0 and maphanquyen = :p1", new Object[]{MaCN, "PQ001"});
        for (Nhanvien nhanvien : list) {
            nhanvien.setTentaikhoan(null);
            nhanvien.setMatkhau(null);
        }
        return list;
    }

    public static boolean addNhanVien(Nhanvien obj) {
        getNewID(obj);
        obj.setMaphanquyen("PQ001");
        obj.setDaxoa(false);
        obj.setTentaikhoan(obj.getEmail());
        obj.setMatkhau(Security.Encrypt(obj.getTentaikhoan()));
        return HibernateUtil.save(obj);
    }

    public static int delete(Object userid) {
        return HibernateUtil.execute("update Nhanvien set daxoa = :p1 where manhanvien = :p0", new Object[]{userid, true});
    }

    public static int recover(Object userid) {
        return HibernateUtil.execute("update Nhanvien set daxoa = :p1 where manhanvien = :p0", new Object[]{userid, false});
    }

    public static boolean update(Nhanvien obj) {
        Nhanvien nv = getSingleFullInfo(obj.getManhanvien());
        nv.setHoten(obj.getHoten());
        nv.setGioitinh(obj.getGioitinh());
        nv.setNgaysinh(obj.getNgaysinh());
        nv.setSodt(obj.getSodt());
        nv.setDiachi(obj.getDiachi());
        nv.setTinhtrang(obj.getTinhtrang());
        return HibernateUtil.update(nv);
    }

    public static int changePassword(ChangePasswordRequest obj) {
        return HibernateUtil.execute("update Nhanvien set matkhau = :p1 where manhanvien = :p0", new Object[]{obj.UserId, obj.NewPass});
    }
    
    public static List<Nhanvien> getStaffOfAgency(Object MaCN) {
        List<Nhanvien> list = HibernateUtil.getList("from Nhanvien where machinhanh = :p0", new Object[]{MaCN});
        return list;
    }
    
     public static String addManager(Nhanvien obj) {
        getNewID(obj);
        obj.setMaphanquyen("PQ002");
        obj.setDaxoa(false);
        obj.setTentaikhoan(obj.getEmail());
        obj.setMatkhau(Security.Encrypt(obj.getTentaikhoan()));
        if(HibernateUtil.save(obj))
        {
            return obj.getManhanvien().toString();
        }
        return "false";  
    }
    
    public static boolean  updateRole(Nhanvien obj) {
        Nhanvien nv = getSingleFullInfo(obj.getManhanvien());
        nv.setMaphanquyen(obj.getMaphanquyen());
        nv.setMachinhanh(obj.getMachinhanh());
        return HibernateUtil.update(nv);
    }

}
