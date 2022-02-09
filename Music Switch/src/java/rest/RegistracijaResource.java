
package rest;

import db.DB;
import db.Grad;
import db.Kupac;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import json.JSONArray;
import json.JSONObject;


@Path("registracija")
public class RegistracijaResource {

    @Context
    private UriInfo context;

    @Context
    private HttpServletRequest request;

    
    public RegistracijaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @POST
    @Path("provera")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String provera(String content) {
        JSONObject obj = new JSONObject(content);
        String email = obj.getString("email");
        if (vecpostoji(email)) {
            return "err";
        }
        return "ok";
    }

    private boolean vecpostoji(String email) {
        List<Kupac> provera = DB.query("SELECT k FROM Kupac k WHERE k.kupEmail = ?1", email);
        return !provera.isEmpty();
    }

    @GET
    @Path("gradovi")
    @Produces(MediaType.APPLICATION_JSON)
    public String gradovi() {
        List<Grad> gradovi = DB.query("SELECT grad FROM Grad grad");
            JSONArray obj = new JSONArray();
            for(Grad g: gradovi){
                JSONObject o = new JSONObject();
                o.put("id", g.getGraId());
                o.put("grad", g.getGraNaziv());
                obj.put( o);
            }
            return obj.toString();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(String content) {
        JSONObject obj = new JSONObject(content);
        String ime = obj.getString("name");
        String prezime = obj.getString("surname");
        String kontakt = obj.getString("num");
        String email = obj.getString("email");
        String pass = obj.getString("pass");
        String ulica = obj.getString("adress");
        
        Integer grad = obj.getInt("city"); 
        String postanskiBroj = obj.getString("postBroj");
        
        

        if (vecpostoji(email)) {
            return "err";
            
        }

        Kupac k = new Kupac(ime, prezime, kontakt, email, pass, ulica, grad, postanskiBroj);
        DB.insert(k);
        return "ok";
    }
}
