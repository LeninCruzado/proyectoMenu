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

public class Platos extends Controller {

//GET
    public static Result getPlatos(){
        List<Plato> platos = Plato.find.all();
        return ok(toJson(platos));
    }
    
    public static Result getPlato(Long id){
        Plato plato = Plato.find.byId(id);
        return ok(toJson(plato));
    }
    
    public static Result getPlatoNombre(String nombre){
        Plato plato = Plato.find.where().eq("nombre", nombre).findUnique();
        if(plato == null) {
            return badRequest("No existe plato con ese nombre");
        }
        return ok(toJson(plato));
    }
    
//POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addPlato()
    {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Plato newPlato = new Plato();
            
            newPlato.nombre = json.findPath("nombre").textValue();
            String cadena1 = json.findPath("precio").textValue();
            newPlato.precio = Float.parseFloat(cadena1);
            String cadena2 = json.findPath("stock").textValue();
            newPlato.stock = Integer.parseInt(cadena2);
            newPlato.imagen = json.findPath("imagen").textValue();
            
            if(newPlato.nombre == null || newPlato.nombre.equals("")) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newPlato.save();
                return redirect("/platos");
            }
        }
     }
    
    public static Result sacarStockPlato(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Plato plato = Plato.find.byId(id);//if id existe en db
        
        if(plato != null){
            String cadena2 = json.findPath("cantidad").textValue();
            int cantidad = Integer.parseInt(cadena2);
            if(plato.disminuirStock(cantidad)){
                plato.update(id);
                return ok("stock dismunuyo en 1");
            }else{
                return ok("no hay stock");
            }
        }else{
            return ok("no se encontro plato");
        }
    }
    
    public static  Result editPlato(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Plato plato = Plato.find.byId(id);
        
        plato.nombre = json.findPath("nombre").textValue();
        String cadena1 = json.findPath("precio").textValue();
        plato.precio = Float.parseFloat(cadena1);
        String cadena2 = json.findPath("stock").textValue();
        plato.stock = Integer.parseInt(cadena2);
        plato.imagen = json.findPath("imagen").textValue();
        
        plato.update(id);
        return ok("se edito");
    }
    
    public static Result deletePlato() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Plato.find.ref(id).delete();
        return redirect("/platos");
    }
}