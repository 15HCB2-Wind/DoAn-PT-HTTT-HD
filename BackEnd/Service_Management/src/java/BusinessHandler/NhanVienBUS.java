/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.Personnel.UpdatePersonnelRequest;
import Models.DataAccess.Personnel.InsertPersonnelRequest;
import Models.DataAccess.Personnel.InsertPersonnelResponse;
import Models.DataAccess.Personnel.UpdatePersonnelResponse;
import Models.Other.SyncResponse;
import Models.Other.SyncRequest;
import Models.DataAccess.*;
import Models.*;
import Config.Configs;
import DAO.PhanQuyenAdapter;
import com.google.gson.Gson;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.Nhanvien;

/**
 *
 * @author Shin'sLaptop
 */
public class NhanVienBUS {
    public static boolean insertValidate(InsertPersonnelRequest request, InsertPersonnelResponse response){
        if (request.Data.getHoten().isEmpty()){
            response.NameErrors.add("Họ tên không được để trống!");
            response.IsError = true;
        }
        if (request.Data.getEmail().isEmpty()){
            response.EmailErrors.add("Email không được để trống!");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
    public static boolean updateValidate(UpdatePersonnelRequest request, UpdatePersonnelResponse response){
        if (request.Data.getHoten().isEmpty()){
            response.NameErrors.add("Họ tên không được để trống!");
            response.IsError = true;
        }
        if (request.Data.getEmail().isEmpty()){
            response.EmailErrors.add("Email không được để trống!");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
    public static boolean deleteValidate(DeleteRequest request, DeleteResponse response){
        if (request.Predicates.length <= 0){
            response.Errors.add("Không đủ tham số tìm kiếm!");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
    public static boolean getSingleValidate(SelectRequest request, SelectResponse response){
        if (request.Predicates.length <= 0){
            response.Errors.add("Không đủ tham số tìm kiếm!");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
    public static void sync(int code, Nhanvien obj){
        int times = 3;
        boolean fail = true;
        
        SyncRequest sRequest = new SyncRequest();
        sRequest.Id = obj.getManhanvien();
        sRequest.FullName = obj.getHoten();
        sRequest.Username = obj.getTentaikhoan();
        sRequest.Password = obj.getMatkhau();
        sRequest.Email = obj.getEmail();
        sRequest.PermissionLevel = PhanQuyenAdapter.getSingle(obj.getMaphanquyen()).getCapphanquyen();
        sRequest.SyncType = code;
        
        do{
            try{
                Client client = ClientBuilder.newClient();
                Response res = client
                        .target(Configs.SYNC_TO_LOGIN_SERVICE)
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(new Gson().toJson(sRequest)));

                SyncResponse result = res.readEntity(SyncResponse.class);
                fail = result == null;
                if (!fail && result.IsError)
                    break;
            } catch (Exception ex){ }
        }while(fail && --times > 0);
    }
}
