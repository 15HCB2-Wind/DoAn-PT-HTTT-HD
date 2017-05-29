/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.DataAccess.Staff.StaffRequest;
import Models.DataAccess.Staff.StaffResponse;
import Models.Other.ChangePasswordRequest;
import Models.Other.ChangePasswordResponse;
import Models.DataAccess.*;
import BusinessHandler.NhanVienBUS;
import DAO.NhanVienAdapter;
import Models.DataAccess.Staff.SelectStaffRequest;
import Models.TokenData;
import com.google.gson.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import pojos.Nhanvien;

/**
 * REST Web Service
 *
 * @author Shin-Desktop
 */
@Path("NhanVien")
public class NhanVienAPIs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NhanVienAPIs
     */
    public NhanVienAPIs() {
    }

    @POST
    @Path("get")
    @Produces("application/json")
    @Consumes("application/json")
    public String get(String json) {
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        SelectStaffRequest request = gson.fromJson(json, SelectStaffRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            try {
                Nhanvien result = NhanVienAdapter.getSingle(request.UserId);
                if (result == null) {
                    response.Errors.add("Không tìm thấy nhân viên.");
                    response.IsError = true;
                } else {
                    result.setMatkhau(null);
                    response.Data = result;
                }
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("getAll")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAll(String json) {
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try {
                response.Data = NhanVienAdapter.getAll(token.AgencyId);
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("add")
    @Produces("application/json")
    @Consumes("application/json")
    public String add(String json) {
        Gson gson = new Gson();
        StaffRequest request = gson.fromJson(json, StaffRequest.class);
        StaffResponse response = new StaffResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            if (NhanVienBUS.validateInformation(request, response, "add")) {
                try {
                    request.Data.setMachinhanh(token.AgencyId);
                    if (NhanVienAdapter.addNhanVien(request.Data)) {
                        NhanVienBUS.sync(1, request.Data);
                        response.Data = "Thêm thành công.";
                    } else {
                        response.Errors.add("Thêm thất bại.");
                        response.IsError = true;
                    }
                } catch (Exception ex) {
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
            else {
                response.Errors.add("Thêm thất bại.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("delete")
    @Produces("application/json")
    @Consumes("application/json")
    public String delete(String json) {
        Gson gson = new Gson();
        SelectStaffRequest request = gson.fromJson(json, SelectStaffRequest.class);
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            try {
                int result = NhanVienAdapter.delete(request.UserId);
                if (result != 1) {
                    response.Errors.add("Xóa nhân viên thất bại.");
                    response.IsError = true;
                } else {
                    NhanVienBUS.sync(-1, NhanVienAdapter.getSingle(request.UserId));
                    response.Data = "Xóa nhân viên thành công.";
                }
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("recover")
    @Produces("application/json")
    @Consumes("application/json")
    public String recover(String json) {
        Gson gson = new Gson();
        SelectStaffRequest request = gson.fromJson(json, SelectStaffRequest.class);
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            try {
                int result = NhanVienAdapter.recover(request.UserId);
                if (result != 1) {
                    response.Errors.add("Khôi phục nhân viên thất bại.");
                    response.IsError = true;
                } else {
                    NhanVienBUS.sync(1, NhanVienAdapter.getSingleFullInfo(request.UserId));
                    response.Data = "Khôi phục nhân viên thành công.";
                }
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("update")
    @Produces("application/json")
    @Consumes("application/json")
    public String update(String json) {
        Gson gson = new Gson();
        StaffRequest request = gson.fromJson(json, StaffRequest.class);
        StaffResponse response = new StaffResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            if (BusinessHandler.NhanVienBUS.validateInformation(request, response, "update")) {
                try {
                    if (NhanVienAdapter.update(request.Data)) {
                        NhanVienBUS.sync(0, NhanVienAdapter.getSingleFullInfo(request.Data.getManhanvien()));
                        response.Data = "Cập nhật thành công!";
                    } else {
                        response.Errors.add("Cập nhật thất bại!");
                        response.IsError = true;
                    }
                } catch (Exception ex) {
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
            else {
                response.Errors.add("Cập nhật thất bại!");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("syncfuheigenhiegheukjenf")
    @Produces("application/json")
    @Consumes("application/json")
    public String sync(String json) {
        Gson gson = new Gson();
        ChangePasswordRequest request = gson.fromJson(json, ChangePasswordRequest.class);
        ChangePasswordResponse response = new ChangePasswordResponse();
        try {
            response.IsError = NhanVienAdapter.changePassword(request) != 1;
        } catch (Exception ex) {
            response.IsError = true;
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getStaffOfAgency")
    @Produces("application/json")
    @Consumes("application/json")
    public String getStaffOfAgency(String json) {
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
//        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
//        if (token != null){
            try {
                response.Data = NhanVienAdapter.getStaffOfAgency(request.Predicates[0]);
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
//        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("addManager")
    @Produces("application/json")
    @Consumes("application/json")
    public String addManager(String json) {
        Gson gson = new Gson();
        StaffRequest request = gson.fromJson(json, StaffRequest.class);
        StaffResponse response = new StaffResponse();
//        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 3);
//        if (token != null){
            //request.Data.setMachinhanh(NhanVienAdapter.getSingle(token.UserId).getMachinhanh());
            //if (NhanVienBUS.validateInformation(request, response, "add")) {
                try {
                    String id = NhanVienAdapter.addManager(request.Data);
                    if (!id.equals("false")) {
                        //NhanVienBUS.sync(1, request.Data);
                        response.Data = id;
                    } else {
                        response.Errors.add("Thêm thất bại.");
                        response.IsError = true;
                    }
                } catch (Exception ex) {
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            //}
//            //else {
//                response.Errors.add("Thêm thất bại.");
//                response.IsError = true;
//            //}
//        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("updateRole")
    @Produces("application/json")
    @Consumes("application/json")
    public String updateRole(String json) {
        Gson gson = new Gson();
        StaffRequest request = gson.fromJson(json, StaffRequest.class);
        StaffResponse response = new StaffResponse();
//        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)) {
            if (BusinessHandler.NhanVienBUS.validateInformation(request, response, "update")) {
                try {
                    if (NhanVienAdapter.updateRole(request.Data)) {
                        NhanVienBUS.sync(0, NhanVienAdapter.getSingleFullInfo(request.Data.getManhanvien()));
                        response.Data = "Cập nhật thành công!";
                    } else {
                        response.Errors.add("Cập nhật thất bại!");
                        response.IsError = true;
                    }
                } catch (Exception ex) {
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
            else {
                response.Errors.add("Cập nhật thất bại!");
                response.IsError = true;
            }
//        }
        return gson.toJson(response);
    }
}
