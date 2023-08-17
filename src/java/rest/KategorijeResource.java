/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.DB;
import db.Kategorija;
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
@Path("kategorije")
public class KategorijeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of KategorijeResource
     */
    public KategorijeResource() {
    }

    /**
     * Retrieves representation of an instance of rest.KategorijeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<Kategorija> res = DB.query("SELECT k FROM Kategorija k");

        
            JSONArray a = new JSONArray();
            for (Kategorija k : res) { 
                JSONObject obj = new JSONObject();
                obj.put("id", k.getKatId());
                obj.put("naziv", k.getKatNaziv()); 

                a.put(obj);
            }
            return a.toString();
        
    }

    /**
     * PUT method for updating or creating an instance of KategorijeResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
