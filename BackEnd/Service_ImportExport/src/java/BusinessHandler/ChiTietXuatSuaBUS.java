/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.DetailSaleMilk.InsertDetailSaleMilkRequest;
import Models.DataAccess.DetailSaleMilk.InsertDetailSaleMilkResponse;

/**
 *
 * @author BuaNhot
 */
public class ChiTietXuatSuaBUS {
    public static boolean insertValidate(InsertDetailSaleMilkRequest request, InsertDetailSaleMilkResponse response){
//        if (request.Data.getLuongsuaxuat().){
//            //            response.MaNhaChungTuError.add("lý do không được để trống!");
//            response.IsError = true;
//        } else {
//        }
        
        return !response.IsError;
    }
}
