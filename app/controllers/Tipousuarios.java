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
  public class Tipousuarios extends Controller {

//GET
    public static Result getTipousuarios(){
        List<Tipousuario> tipousuarios = Tipousuario.find.all();
        return ok(toJson(tipousuarios));
    }
    
    public static Result getTipousuario(Long id){
        Tipousuario tipousuario = Tipousuario.find.byId(id);
        return ok(toJson(tipousuario));
    }
    
    public static Result getTipousuarioNombre(String nombre){
        Tipousuario tipousuario = Tipousuario.find.where().eq("nombre", nombre).findUnique();
        if(tipousuario == null) {
            return badRequest("No existe tipousuario con ese nombre");
        }
        return ok(toJson(tipousuario));
    }

// POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addTipousuario()
    {
        //Tipousuario newTipousuario = Json.fromJson(request().body().asJson(), Tipousuario.class);
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Tipousuario newTipousuario = new Tipousuario();
            
            newTipousuario.nombre = json.findPath("nombre").textValue();
            
            if(newTipousuario.nombre == null) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newTipousuario.save();
                return ok("tipousuario se guardo");
            }
        }
     }
     
     public static  Result editTipousuario(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Tipousuario tipousuario = Tipousuario.find.byId(id);
        
        tipousuario.nombre = json.findPath("nombre").textValue();
        
        tipousuario.update(id);
        return ok("se edito");
    }
     
     
    public static Result deleteTipousuario() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Tipousuario.find.ref(id).delete();
        return ok("se elimino");
    }

}