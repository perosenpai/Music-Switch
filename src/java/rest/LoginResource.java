/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.DB;
import db.Kupac;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import json.JSONObject;

/**
 * REST Web Service
 *
 * @author Petrik
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;
    
    @Context 
    private HttpServletRequest request;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * Retrieves representation of an instance of rest.LoginResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(String content) {
        JSONObject user = new JSONObject(content);
        String email = user.getString("email");
        String pass = user.getString("pass");
        
        
        List<Kupac> kupac = DB.query("SELECT k FROM Kupac k WHERE k.kupEmail = ?1 AND k.kupSifra = ?2",email,pass);
        
        if(!kupac.isEmpty()) { 
           HttpSession s=request.getSession();
           s.setAttribute("email", email);
           
           return "{\"res\":\"ok\"}";
        } else {
           return   "{\"res\":\"err\"}";     
        }  
    }
    
    @POST
    @Path("kupac")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginovan_kupac(String content) {
        
        HttpSession s=request.getSession(true);
        String kupac_email = (String) s.getAttribute("email");
        
        System.out.println("moj kupac" + kupac_email);
      
        List<Kupac> kupci = DB.query("SELECT k FROM Kupac k WHERE k.kupEmail = ?1", kupac_email);
        
        if(!kupci.isEmpty()) { 
             JSONObject obj = new JSONObject();
            for(Kupac k : kupci) {

              
              obj.put("ime", k.getKupIme());
              obj.put("prezime", k.getKupPrezime());
              obj.put("adresa", k.getKupAdresa());
              obj.put("grad", k.getGraId().getGraNaziv().toString());
              obj.put("postanski", k.getKuppostBroj());
              obj.put("id", k.getKupId());
            }
            
            return obj.toString();
        } else {
           return   "";     
        }  
    }

    /**
     * PUT method for updating or creating an instance of LoginResource
     * @param content representation for the resource
     */
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
