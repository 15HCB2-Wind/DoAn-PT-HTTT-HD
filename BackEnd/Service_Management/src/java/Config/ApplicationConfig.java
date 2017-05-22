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
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Config.CrossOriginResourceSharingFilter.class);
        resources.add(Services.BoAPIs.class);
        resources.add(Services.ChiNhanhAPIs.class);
        resources.add(Services.ChuongTraiAPIs.class);
        resources.add(Services.KhoSuaAPIs.class);
        resources.add(Services.NhaCungCapAPIs.class);
        resources.add(Services.NhanVienAPIs.class);
    }
}
