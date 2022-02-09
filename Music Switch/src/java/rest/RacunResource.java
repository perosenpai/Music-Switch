/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.DB;

import java.util.Set;
import db.Racun;
import db.Artikal;
import db.Kupac;
import db.Nacinplacanja;
import db.Stavka;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
@Path("racun")
public class RacunResource {

    @Context
    private UriInfo context;
    
    
    @Context
    private HttpServletRequest request;


    /**
     * Creates a new instance of RacunResource
     */
    public RacunResource() {
    }

    /**
     * Retrieves representation of an instance of rest.RacunResource
     *
     * @return an instance of java.lang.String
     */
    
    @POST
    @Path("/zavrsi")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String zavrsiKupovinu(String content) {

        
       
        JSONObject json = new JSONObject(content);

        Integer kor_id = Integer.parseInt(json.get("kor_id").toString());
        Integer nacin = Integer.parseInt(json.get("nacin").toString());
        
            Racun racun = new Racun(kor_id);
            Nacinplacanja naciniplacanja = racun.getNacId();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("d.M.yyyy");
            Date datum = new Date();
            Kupac kupac = racun.getKupId();
            racun.setRacDatum(datum);
            racun.getNacId();
            DB.insert(racun);
            
            
        HttpSession my_session = request.getSession(true);
        JSONArray artikli_u_korpi = new JSONArray();
        Enumeration e = (Enumeration) (my_session.getAttributeNames());
        
        Set<Stavka> lista_artikala = new HashSet<Stavka>();

        while (e.hasMoreElements()) {
            Object article;

            if ((article = e.nextElement()) != null) {
                if (article.toString().startsWith("cart")) {
                    String id_artikal = article.toString().substring(5);
                    String kolicina =  my_session.getValue(article.toString()).toString();
                    Artikal izabrani_artikal = new Artikal(Integer.parseInt(id_artikal));
                            
                    Stavka stavka = new Stavka(izabrani_artikal, Integer.parseInt(kolicina), izabrani_artikal.getArtCena());
                    stavka.setRacId(racun);
                    lista_artikala.add(stavka);
                    
                    
                }
            }
        }
            racun.setStavkaSet(lista_artikala);
            DB.update(racun);
            
            
        
            return "ok";
    }

    /**
     * PUT method for updating or creating an instance of RacunResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
