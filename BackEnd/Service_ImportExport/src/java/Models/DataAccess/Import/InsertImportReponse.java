/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DataAccess.Import;

import Models.BodyResponse;
import java.util.ArrayList;

/**
 *
 * @author BuaNhot
 */
public class InsertImportReponse extends BodyResponse{
    public ArrayList<String> MaNhaCungCapError;
    
    public InsertImportReponse(){
        MaNhaCungCapError = new ArrayList<>();
    }
}
