/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DataAccess.Barn;

import Models.BodyResponse;
import java.util.ArrayList;

/**
 *
 * @author Tu
 */
public class BarnResponse extends BodyResponse {

    public ArrayList<String> NameErrors;
    public ArrayList<String> SlotErrors;
    public ArrayList<String> Slot1Errors;

    public BarnResponse() {
        NameErrors = new ArrayList<>();
        SlotErrors = new ArrayList<>();
        Slot1Errors = new ArrayList<>();
    }
}
