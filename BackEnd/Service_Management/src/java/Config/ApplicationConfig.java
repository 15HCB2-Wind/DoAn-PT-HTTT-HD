/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Shin-Desktop
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
//Config URL
    private String sync_IP = "http://192.168.22.158:28288/";
    private String token_IP = "http://192.168.22.158:28288/";
    private String sync_Nhanvien = "account/sync";
    private String authenticate_token = "";
    public static final String SECURITY_SECRET_KEY = "";
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }


    /**
     * @return the sync_Nhanvien
     */
    public String getSync_Nhanvien() {
        return sync_IP + sync_Nhanvien;
    }
    
    public String getAuthentication(){
        return token_IP+authenticate_token;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Services.NhanVienAPIs.class);
    }
}
