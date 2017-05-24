/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.Staff.StaffRequest;
import Models.DataAccess.Staff.StaffResponse;
import Models.Other.SyncResponse;
import Models.Other.SyncRequest;
import Config.Configs;
import DAO.PhanQuyenAdapter;
import DAO.NhanVienAdapter;
import com.google.gson.Gson;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import pojos.Nhanvien;

/**
 *
 * @author Shin'sLaptop
 */
public class NhanVienBUS {

    public static boolean validateInformation(StaffRequest request, StaffResponse response) {
        if (request.Data.getHoten().isEmpty()) {
            response.NameErrors.add("Họ tên không được để trống");
            response.IsError = true;
        }
        if (request.Data.getEmail().isEmpty()) {
            response.EmailErrors.add("Email không được để trống");
            response.IsError = true;
        }
        if (NhanVienAdapter.checkEmail(request.Data.getEmail()) == false) {
            response.EmailErrors.add("Email dã tồn tại");
            response.IsError = true;
        }
        return !response.IsError;
    }

    public static void sync(int code, Nhanvien obj) {
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

        do {
            try {
                Client client = ClientBuilder.newClient();
                Response res = client
                        .target(Configs.SYNC_TO_LOGIN_SERVICE)
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(new Gson().toJson(sRequest)));

                SyncResponse result = res.readEntity(SyncResponse.class);
                fail = result == null;
                if (!fail) {
                    break;
                }
            } catch (Exception ex) {
            }
        } while (fail && --times > 0);
    }
}
