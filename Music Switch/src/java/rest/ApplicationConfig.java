/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Petrik
 */
@javax.ws.rs.ApplicationPath("app")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(rest.ArtikalResource.class);
        resources.add(rest.ArtikliResource.class);
        resources.add(rest.DrzavaResource.class);
        resources.add(rest.KarakteristikeResource.class);
        resources.add(rest.KategorijeResource.class);
        resources.add(rest.KorpaResource.class);
        resources.add(rest.LoginResource.class);
        resources.add(rest.NaciniResource.class);
        resources.add(rest.PotkategorijeResource.class);
        resources.add(rest.RacunResource.class);
        resources.add(rest.RegistracijaResource.class);
    }
    
}
