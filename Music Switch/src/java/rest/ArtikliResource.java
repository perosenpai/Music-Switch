/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.Artikal;
import db.DB;
import db.Slika;
import java.util.Base64;
import java.util.List;
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
@Path("artikli/{pot_id}/")
public class ArtikliResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ArtikliResource
     */
    public ArtikliResource() {
    }

    /**
     * Retrieves representation of an instance of rest.ArtikliResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("pot_id") int pot_id) {
        List<Artikal> res = DB.query("SELECT a FROM Artikal a WHERE a.potId.potId = " +  pot_id);
 
            JSONArray arr = new JSONArray();
            for (Artikal a : res) {
                JSONObject obj = new JSONObject();
                obj.put("id", a.getArtId());
                obj.put("naziv", a.getArtNaziv());
                obj.put("cena", a.getArtCena());
                obj.put("opis", a.getArtOpis());
                
                /*
                JSONArray karakteristika = new JSONArray();
                
                Set<Karakteristike> karakteristike = a.getKarakteristikeSet();
                for(Karakteristike k : karakteristike){
                    karakteristika.put('', k.)
                }
                */
               /* 
                JSONObject slike = new JSONObject(); 
                
                Set<Slika> slike_rezultat = a.getSlikaSet(); 
                for(Slika s : slike_rezultat){
                    String img_64 = Base64.getEncoder().encodeToString(s.getSlkSlika());
                    slike.put("slika", img_64);
                }
                
                obj.put("slike", slike);
                */
               
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
