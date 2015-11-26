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
        //Promocion newBebida = Json.fromJson(request().body().asJson(), Bebida.class);
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Bebida newBebida = new Bebida();

            newBebida.nombre = json.findPath("nombre").textValue();
            String cadena = json.findPath("stock").textValue();
            newBebida.stock = Integer.parseInt(cadena);
            newBebida.imagen = json.findPath("imagen").textValue();
            
            if(newBebida.nombre == null) {
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
        
        if(bebida.stock>0){
            bebida.stock = bebida.stock - 1;
            bebida.update(id);
            return ok("stock dismunuyo en 1");
        }
        else{
            return ok("no hay stock");
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