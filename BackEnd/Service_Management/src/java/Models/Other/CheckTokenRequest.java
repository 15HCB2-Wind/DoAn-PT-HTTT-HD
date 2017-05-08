/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Other;

import Models.BodyRequests;

/**
 *
 * @author 19101994
 */
public class CheckTokenRequest extends BodyRequests {
    public String TokenPassword;
    public int Role;
}
