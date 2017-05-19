    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Configs;
import Models.Other.SyncRequest;
import Models.Other.SyncResponse;
import Ultility.HibernateUtil;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.Nhacungcap;

/**
 *
 * @author Shin'sLaptop
 */
public class NhaCungCapAdapter {
    public static List<Nhacungcap> getAll(){
        return HibernateUtil.getList("from Nhacungcap", null);
    }
    private static void getNewID(Nhacungcap obj) {
        
          List<Nhacungcap> list = HibernateUtil.getSingle("from Nhacungcap order by manhacungcap desc",null);//lay ds chinhanh tu duoi len
        String machu= "";
         if (list.size()>0) {
            Nhacungcap cn = list.get(list.size()-1);
            machu = cn.getManhacungcap().substring(3);//NCC00001 -> 00001
         }
         else
         {
              machu = "0";
         }
        int maso = Integer.parseInt(machu);
        obj.setManhacungcap(String.format("NCC%05d", maso + 1));
    }
    public static boolean add(Nhacungcap obj) {
        getNewID(obj);
        return HibernateUtil.save(obj);
    }   
     public static Nhacungcap getSingle(Object id){
        List<Nhacungcap> list = HibernateUtil.getSingle("from Nhacungcap where manhacungcap = :p0", new Object[]{ id });
        if (list.size()>0) {
            Nhacungcap ncc = list.get(0);
            return ncc;
        }
        return null;
    }
    public static boolean update(Nhacungcap obj) {
        Nhacungcap ncc = getSingle(obj.getManhacungcap());
        if(!obj.getTen().equals(""))
            ncc.setTen(obj.getTen());
        if(!obj.getDiachi().equals(""))
            ncc.setDiachi(obj.getDiachi());
        if(!obj.getTinhtrang().equals(""))
            ncc.setTinhtrang(obj.getTinhtrang());
        return HibernateUtil.update(ncc);
    }
    
//    public static void sync(int code, Nhacungcap obj){
//        int times = 3;
//        boolean fail = true;
//        
//        SyncRequest sRequest = new SyncRequest();
//        sRequest.Id = obj.getManhanvien();
//        sRequest.FullName = obj.getHoten();
//        sRequest.Username = obj.getTentaikhoan();
//        sRequest.Password = obj.getMatkhau();
//        sRequest.Email = obj.getEmail();
//        sRequest.PermissionLevel = PhanQuyenAdapter.getSingle(obj.getMaphanquyen()).getCapphanquyen();
//        sRequest.SyncType = code;
//        
//        do{
//            try{
//                Client client = ClientBuilder.newClient();
//                Response res = client
//                        .target(Configs.SYNC_TO_LOGIN_SERVICE)
//                        .request(MediaType.APPLICATION_JSON)
//                        .post(Entity.json(new Gson().toJson(sRequest)));
//
//                SyncResponse result = res.readEntity(SyncResponse.class);
//                fail = result == null;
//                if (!fail)
//                    break;
//            } catch (Exception ex){ }
//        }while(fail && --times > 0);
//    }

}
