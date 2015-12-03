package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.mvc.BodyParser;

import views.html.*;

import models.*;
import java.util.List;

import play.libs.Json;
import play.libs.Json.*;
import static play.libs.Json.toJson;
import com.fasterxml.jackson.databind.JsonNode;


@With(AutentificarAdm.class)
  public class Tipomenus extends Controller {

//GET
    public static Result getTipomenus(){
        List<Tipomenu> tipomenus = Tipomenu.find.all();
        return ok(toJson(tipomenus));
    }
    
    public static Result getTipomenu(Long id){
        Tipomenu tipomenu = Tipomenu.find.byId(id);
        return ok(toJson(tipomenu));
    }
    
    public static Result getTipomenuNombre(String nombre){
        Tipomenu tipomenu = Tipomenu.find.where().eq("nombre", nombre).findUnique();
        if(tipomenu == null) {
            return badRequest("No existe tipousuario con ese nombre");
        }
        return ok(toJson(tipomenu));
    }

// POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addTipomenu()
    {
        //Tipousuario newTipousuario = Json.fromJson(request().body().asJson(), Tipousuario.class);
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Tipomenu newTipomenu = new Tipomenu();
            
            newTipomenu.nombre = json.findPath("nombre").textValue();
            
            if(newTipomenu.nombre == null) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newTipomenu.save();
                return ok("tipousuario se guardo");
            }
        }
     }
     
     public static  Result editTipomenu(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Tipomenu tipomenu = Tipomenu.find.byId(id);

        if(tipomenu != null){
            tipomenu.nombre = json.findPath("nombre").textValue();
            tipomenu.update(id);
            return ok("se edito");   
        }else return ok("no se encontro");
    }
     
     
    public static Result deleteTipomenu() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Tipomenu.find.ref(id).delete();
        return ok("se elimino");
    }

}