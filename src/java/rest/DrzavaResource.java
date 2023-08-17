/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.DB;
import db.Drzava;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import json.JSONArray;
import json.JSONObject;

/**
 * REST Web Service
 *
 * @author Petrik
 */
@Path("drzava")
public class DrzavaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DrzavaResource
     */
    public DrzavaResource() {
    }

    /**
     * Retrieves representation of an instance of rest.DrzavaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<Drzava> res = DB.query("SELECT d FROM Drzava d");

        
            JSONArray a = new JSONArray();
            for (Drzava d : res) { 
                JSONObject obj = new JSONObject();
                obj.put("drzava", d.getDrzNaziv()); 

                a.put(obj);
            }
            return a.toString();
        
    }

    /**
     * PUT method for updating or creating an instance of DrzavaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
