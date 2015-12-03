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

public class Bebidas extends Controller {

//GET
    public static Result getBebidas(){
        List<Bebida> bebidas = Bebida.find.all();
        return ok(toJson(bebidas));
    }
    
    public static Result getBebida(Long id){
        Bebida bebida = Bebida.find.byId(id);
        return ok(toJson(bebida));
    }
    
    public static Result getBebidaNombre(String nombre){
        Bebida bebida = Bebida.find.where().eq("nombre", nombre).findUnique();
        if(bebida == null) {
            return badRequest("No existe bebida con ese nombre");
        }
        return ok(toJson(bebida));
    }
    
//POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addBebida()
    {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Bebida newBebida = new Bebida();

            newBebida.nombre = json.findPath("nombre").textValue();
            String cadena = json.findPath("stock").textValue();
            newBebida.stock = Integer.parseInt(cadena);
            newBebida.imagen = json.findPath("imagen").textValue();
            
            if(newBebida.nombre == null || newBebida.nombre.equals("")) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newBebida.save();
                return redirect("/bebidas");
            }
        }
     }
     
     public static Result sacarStockBebida(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Bebida bebida = Bebida.find.byId(id);//if id existe en db
        
        if(bebida != null){
            String cadena2 = json.findPath("cantidad").textValue();
            int cantidad = Integer.parseInt(cadena2);
            if(bebida.disminuirStock(cantidad)){
                bebida.update(id);
                return ok("stock dismunuyo en 1");
            }else{
                return ok("no hay stock");
            }
        }else{
            return ok("no se encontro bebida");
        }
    }
    
    public static  Result editBebida(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Bebida bebida = Bebida.find.byId(id);
        
        bebida.nombre = json.findPath("nombre").textValue();
        String cadena1 = json.findPath("stock").textValue();
        bebida.stock = Integer.parseInt(cadena1);
        bebida.imagen = json.findPath("imagen").textValue();
        
        bebida.update(id);
        return ok("se edito");
    }
     
    public static Result deleteBebida() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Bebida.find.ref(id).delete();
        return redirect("/bebidas");
    }
}