/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.ApplicationConfig;
import Config.Configs;
import Models.ChangePasswordRequest;
import Ultility.HibernateUtil;
import Ultility.Security;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.*;

/**
 *
 * @author Shin-Desktop
 */
public class NhanVienAdapter {
    //Help function
    private static void getNewID(Nhanvien obj) {
        int count = HibernateUtil.count("select count(manhanvien) from Nhanvien");
        String temp = ""+(count+1);
        while (temp.length()<3) {            
            temp = "0"+temp;
        }
        obj.setManhanvien("NV"+temp);
    }
    
   
    //
    
    public static List<Nhanvien> getAll() {
        List<Nhanvien> list = HibernateUtil.getList("from Nhanvien", null);
        for (Nhanvien nhanvien : list) {
            nhanvien.setTentaikhoan(null);
            nhanvien.setMatkhau(null);
        }
	return list;
    }
    
    public static Nhanvien getSingle(String id){
        List<Nhanvien> list = HibernateUtil.getSingle("from Nhanvien where manhanvien = :p0", new Object[]{ id });
        
        if (list.size()>0) {
            Nhanvien nhanvien = list.get(0);
            nhanvien.setTentaikhoan(null);
            nhanvien.setMatkhau(null);
            return nhanvien;
        }
        return null;
    }
    
    public static Nhanvien getSingle_ChangePassword(String id){
        List<Nhanvien> list = HibernateUtil.getSingle("from Nhanvien where manhanvien = :p0", new Object[]{ id });
        
        if (list.size()>0) {
            Nhanvien nhanvien = list.get(0);
            return nhanvien;
        }
        return null;
    }
    
    public static List<Nhanvien> getListInAgency(String id) {
        List<Nhanvien> list = HibernateUtil.getList("from Nhanvien where machinhanh = :p0", new Object[]{id});
        for (Nhanvien nhanvien : list) {
            nhanvien.setTentaikhoan(null);
            nhanvien.setMatkhau(null);
        }
	return list;
    }
    

    
    public static String addNhanVien(Nhanvien obj) throws UnsupportedEncodingException{
        if (obj.getManhanvien().isEmpty()) {
            getNewID(obj);
        }
        obj.setMatkhau(Security.Encrypt(obj.getMatkhau()));
        if (HibernateUtil.save(obj)) {
            SyncNhanVien(1,obj);
            return "Thêm nhân viên thành công";
        }
        else{
            return "Thêm nhân viên thất bại";
        }
    }

    public static String deleteNhanVien(Nhanvien obj) {
        obj.setDaxoa(true);
            if (HibernateUtil.update(obj)) {
                SyncNhanVien(-1, obj);
                return "Nhân viên đã xoá thành công";
            }
        return "Xoá không thành công";
    }

    public static boolean updateNhanVien(Nhanvien obj) {
        Nhanvien nv = getSingle(obj.getManhanvien());
        obj.setMatkhau(nv.getMatkhau());
        obj.setTentaikhoan(nv.getTentaikhoan());
        if (HibernateUtil.update(obj)) {
            SyncNhanVien(0, obj);
            return true;
        }
        return false;
    }
    
    public static void SyncNhanVien(int code, Nhanvien obj){
        Client client = ClientBuilder.newClient();
        Form form = new Form();
        form.param("Id", obj.getManhanvien());
        form.param("Username", obj.getTentaikhoan());
        form.param("Password", obj.getMatkhau());
        form.param("Email", obj.getEmail());
        form.param("FullName",obj.getHoten());
        form.param("PermissionLevel", PhanQuyenAdapter.getSingle(obj.getMaphanquyen()).getCapphanquyen()+"");
        form.param("SyncType",code+"");
        
        Response response = client
                .target(Configs.sync2LoginService)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form));
    }

    public static boolean syncPassword(ChangePasswordRequest obj) {
        Nhanvien nv = getSingle_ChangePassword(obj.UserId);
        if (nv!=null) {
            nv.setMatkhau(obj.NewPass);
            if (HibernateUtil.update(nv)) {
                return true;
            }
        }
        return false;
    }
}
