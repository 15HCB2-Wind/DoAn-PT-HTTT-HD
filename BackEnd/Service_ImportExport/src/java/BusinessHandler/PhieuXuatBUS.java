/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.SaleMilk.InsertSaleMilkRequest;
import Models.DataAccess.SaleMilk.InsertSaleMilkResponse;

/**
 *
 * @author BuaNhot
 */
public class PhieuXuatBUS {
    public static boolean insertValidate(InsertSaleMilkRequest request, InsertSaleMilkResponse response){
        if (request.Data.getLydo().isEmpty()){
//            response.MaNhaChungTuError.add("lý do không được để trống!");
            response.IsError = true;
        }
        return !response.IsError;
    }
}
