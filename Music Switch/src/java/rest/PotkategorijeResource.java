/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.DB;
import db.Potkategorija;
import db.Slika;
import java.util.Base64;

import java.util.List;

import javax.ws.rs.PathParam;
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
@Path("potkategorije/{kat_id}/")
public class PotkategorijeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PotkategorijeResource
     */
    public PotkategorijeResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PotkategorijeResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("kat_id") int kat_id) {

        List<Potkategorija> res = DB.query("SELECT p FROM Potkategorija p WHERE p.ktgId.katId = " + kat_id);

        JSONArray arr = new JSONArray();
        for (Potkategorija p : res) {
            JSONObject obj = new JSONObject();
            obj.put("id", p.getPotId());
            obj.put("naziv", p.getPotNaziv());
            obj.put("kat_id", p.getKtgId());
            obj.put("opis", p.getPotOpis());

            Slika slika_potkategorije = p.getSlika();

            String img_64 = Base64.getEncoder().encodeToString(slika_potkategorije.getSlkSlika());
            obj.put("slika", img_64);

            arr.put(obj);
        }
        return arr.toString();

    }

    /**
     * PUT method for updating or creating an instance of PotkategorijeResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
