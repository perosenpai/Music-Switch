/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.Artikal;
import db.DB;
import db.Karakteristike;
import db.Slika;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import json.JSONArray;
import json.JSONObject;

/**
 * REST Web Service
 *
 * @author Petrik
 */
@Path("artikal/{id_art}/")
public class ArtikalResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ArtikliResource
     */
    public ArtikalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.ArtikliResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id_art") int id_art) {
        List<Artikal> res = DB.query("SELECT a FROM Artikal a WHERE a.artId = " +  id_art);
 
            JSONArray arr = new JSONArray();
            for (Artikal a : res) {
                JSONObject obj = new JSONObject();
                obj.put("id", a.getArtId());
                obj.put("naziv", a.getArtNaziv());
                obj.put("cena", a.getArtCena());
                obj.put("opis", a.getArtOpis());
                
                
                JSONArray karakteristike_json = new JSONArray();
                
                Set<Karakteristike> karakteristike = a.getKarakteristikeSet();
                for(Karakteristike k : karakteristike){
                    JSONObject obj_kar = new JSONObject();
                    obj_kar.put("naziv", k.getKarNaziv());
                    obj_kar.put("vrednost", k.getKarVrednost());

                    karakteristike_json.put(obj_kar);
                }
               
                obj.put("karakteristike", karakteristike_json);
                
                
                Slika slika_artikla = a.getSlika();
                
               String img_64 = Base64.getEncoder().encodeToString(slika_artikla.getSlkSlika());
               obj.put("slika", img_64);
                
                arr.put(obj);

                
                
                
                
            }
            return arr.toString();
    }

    /**
     * PUT method for updating or creating an instance of ArtikliResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
