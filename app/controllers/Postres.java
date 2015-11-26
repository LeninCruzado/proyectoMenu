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

public class Postres extends Controller {
    
//GET
    public static Result getPostres(){
        List<Postre> postres = Postre.find.all();
        return ok(toJson(postres));
    }
    
    public static Result getPostre(Long id){
        Postre postre = Postre.find.byId(id);
        return ok(toJson(postre));
    }
    
    public static Result getPostreNombre(String nombre){
        Postre postre = Postre.find.where().eq("nombre", nombre).findUnique();
        if(postre == null) {
            return badRequest("No existe postre con ese nombre");
        }
        return ok(toJson(postre));
    }
    
//POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addPostre()
    {
        //Promocion newPostre = Json.fromJson(request().body().asJson(), Postre.class);
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Postre newPostre = new Postre();
            
            newPostre.nombre = json.findPath("nombre").textValue();
            String cadena = json.findPath("stock").textValue();
            newPostre.stock = Integer.parseInt(cadena);
            newPostre.imagen = json.findPath("imagen").textValue();

            if(newPostre.nombre == null) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newPostre.save();
                return redirect("/bebidas");
            }
        }
     }
    
    public static Result sacarStockPostre(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Postre postre = Postre.find.byId(id);//if id existe en db
        
        if(postre.stock>0){
            postre.stock = postre.stock - 1;
            postre.update(id);
            return ok("stock dismunuyo en 1");
        }
        else{
            return ok("no hay stock");
        }
    }
    
    public static  Result editPostre(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Postre postre = Postre.find.byId(id);
        
        postre.nombre = json.findPath("nombre").textValue();
        String cadena1 = json.findPath("stock").textValue();
        postre.stock = Integer.parseInt(cadena1);
        postre.imagen = json.findPath("imagen").textValue();
        
        postre.update(id);
        return ok("se edito");
    }
    
    public static Result deletePostre() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Postre.find.ref(id).delete();
        return redirect("/postres");
    }
}