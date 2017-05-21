/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DataAccess.Staff;

import Models.BodyResponses;
import java.util.ArrayList;

/**
 *
 * @author 19101994
 */
public class StaffResponse extends BodyResponses {

    public ArrayList<String> NameErrors;
    public ArrayList<String> EmailErrors;

    public StaffResponse() {
        NameErrors = new ArrayList<>();
        EmailErrors = new ArrayList<>();
    }
}
