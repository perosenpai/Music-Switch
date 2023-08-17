/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.Artikal;
import db.DB;
import db.Kategorija;
import db.Slika;
import db.Stavka;
import java.util.Base64;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
@Path("korpa")
public class KorpaResource {

    @Context
    private UriInfo context;

    @Context
    private HttpServletRequest request;

    /**
     * Creates a new instance of KorpaResource
     */
    public KorpaResource() {
    }

    /**
     * Retrieves representation of an instance of rest.KorpaResource
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/{kupac_id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("kupac_id") Integer kupac_id) {

        List<Stavka> res = DB.query("SELECT s FROM Stavka s WHERE s.kupId.kupId = ?1 AND s.racId IS NULL", kupac_id);

        JSONArray a = new JSONArray();
        for (Stavka s : res) {
            JSONObject obj = new JSONObject();
            obj.put("id", s.getArtId().getArtId());
            obj.put("naziv", s.getArtId().getArtNaziv());
            obj.put("kolicina", s.getStaKolicina());
            obj.put("cena", s.getStaCena());

            Slika slika_artikla = s.getArtId().getSlika();
            String img_64 = Base64.getEncoder().encodeToString(slika_artikla.getSlkSlika());
            obj.put("slika", img_64);

            a.put(obj);
        }
        return a.toString();

    }

    @POST
    @Path("/dodaj")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String dodajUKorpu(String content) {

        JSONObject json = new JSONObject(content);

        HttpSession my_session = request.getSession(true);
        String id_artikal = json.get("art_id").toString();
        String session_article_id = "cart_" + id_artikal;

        if (my_session.getAttribute(session_article_id) != null) {

            Integer existing_article_count = Integer.parseInt(my_session.getAttribute(session_article_id).toString());
            my_session.setAttribute(session_article_id, (existing_article_count + 1));
    
        } else {

            my_session.setAttribute(session_article_id, 1);
        }

        return "OK";
    }

    @POST
    @Path("/oduzmi")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String oduzmiIzKorpe(String content) {

        JSONObject json = new JSONObject(content);

        HttpSession my_session = request.getSession(true);
        String id_artikal = json.get("art_id").toString();
        String session_article_id = "cart_" + id_artikal;

        
        Integer kolicina = Integer.parseInt(my_session.getAttribute(session_article_id).toString());
        if (kolicina > 1) {
            Integer existing_article_count = Integer.parseInt(my_session.getAttribute(session_article_id).toString());
            my_session.setAttribute(session_article_id, (existing_article_count - 1));
           

        } else {
            my_session.removeAttribute(session_article_id);
        }

        return "ok";
    }

    @POST
    @Path("/prikazi")
    @Produces(MediaType.APPLICATION_JSON)
    public String prikaziKorpu(String content) {

        HttpSession my_session = request.getSession(true);
        JSONArray artikli_u_korpi = new JSONArray();
        Enumeration e = (Enumeration) (my_session.getAttributeNames());

        while (e.hasMoreElements()) {
            Object article;

            if ((article = e.nextElement()) != null) {
                if (article.toString().startsWith("cart")) {
                    String id_artikal = article.toString().substring(5);
                    String kolicina =  my_session.getValue(article.toString()).toString();
                    Artikal artikal = (Artikal) DB.query("SELECT a FROM Artikal a WHERE a.artId =?1", Integer.parseInt(id_artikal)).get(0);
                    JSONObject obj = new JSONObject();
                    obj.put("id_artikal", id_artikal);
                    obj.put("kolicina", kolicina);
                    obj.put("cena", artikal.getArtCena()*Double.parseDouble(kolicina) );
                    obj.put("naziv", artikal.getArtNaziv());
                    
                    
                    Slika slika_artikla = artikal.getSlika();
                    String img_64 = Base64.getEncoder().encodeToString(slika_artikla.getSlkSlika());
                    obj.put("slika", img_64);
                    
                    artikli_u_korpi.put(obj);

                } else {
                    return artikli_u_korpi.toString();
                }
            }
            
        }

        return artikli_u_korpi.toString();

    }

    /**
     * PUT method for updating or creating an instance of KorpaResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
