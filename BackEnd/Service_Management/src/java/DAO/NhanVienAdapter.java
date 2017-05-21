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

    public static List<Nhanvien> getAll(String MaCN, String MaPQ) {
        List<Nhanvien> list = HibernateUtil.getList("from Nhanvien where machinhanh = :p0 and maphanquyen = :p1", new Object[]{MaCN, MaPQ});
        return list;
    }

    public static boolean add(Nhanvien obj) {
        getNewID(obj);
        obj.setDaxoa(false);
        obj.setMatkhau(Security.Encrypt(obj.getMatkhau()));
        return HibernateUtil.save(obj);
    }

    public static int delete(Object userid) {
        return HibernateUtil.execute("update Nhanvien set daxoa = :p1 where manhanvien = :p0", new Object[]{userid, true});
    }

    public static int recover(Object userid) {
        return HibernateUtil.execute("update Nhanvien set daxoa = :p1 where manhanvien = :p0", new Object[]{userid, false});
    }

    public static boolean update(Nhanvien obj) {
        Nhanvien nv = getSingle(obj.getManhanvien());
        nv.setHoten(obj.getHoten());
        nv.setGioitinh(obj.getGioitinh());
        nv.setNgaysinh(obj.getNgaysinh());
        nv.setSodt(obj.getSodt());
        nv.setDiachi(obj.getDiachi());
        nv.setEmail(obj.getEmail());
        //nv.setTentaikhoan(obj.getTentaikhoan());
        //nv.setMatkhau(obj.getMatkhau());
        nv.setTinhtrang(obj.getTinhtrang());
        nv.setDaxoa(obj.getDaxoa());
        nv.setMachinhanh(obj.getMachinhanh());
        nv.setMaphanquyen(obj.getMaphanquyen());
        return HibernateUtil.update(nv);
    }

    public static int changePassword(ChangePasswordRequest obj) {
        return HibernateUtil.execute("update Nhanvien set matkhau = :p1 where manhanvien = :p0", new Object[]{obj.UserId, obj.NewPass});
    }
}
