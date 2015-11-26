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

public class Entradas extends Controller {
    
//GET
    public static Result getEntradas(){
        List<Entrada> entradas = Entrada.find.all();
        return ok(toJson(entradas));
    }
    
    public static Result getEntrada(Long id){
        Entrada entrada = Entrada.find.byId(id);
        return ok(toJson(entrada));
    }
    
    public static Result getEntradaNombre(String nombre){
        Entrada entrada = Entrada.find.where().eq("nombre", nombre).findUnique();
        if(entrada == null) {
            return badRequest("No existe entrada con ese nombre");
        }
        return ok(toJson(entrada));
    }
    
//POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addEntrada()
    {
        //Promocion newEntrada = Json.fromJson(request().body().asJson(), Entrada.class);
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Entrada newEntrada = new Entrada();
            
            newEntrada.nombre = json.findPath("nombre").textValue();
            String cadena1 = json.findPath("precio").textValue();
            newEntrada.precio = Float.parseFloat(cadena1);
            String cadena2 = json.findPath("stock").textValue();
            newEntrada.stock = Integer.parseInt(cadena2);
            newEntrada.imagen = json.findPath("imagen").textValue();
            
            if(newEntrada.nombre == null) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newEntrada.save();
                return redirect("/entradas");
            }
        }
    }
    
    public static Result sacarStockEntrada(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Entrada entrada = Entrada.find.byId(id);//if id existe en db
        
        if(entrada.stock>0){
            entrada.stock = entrada.stock - 1;
            entrada.update(id);
            return ok("stock dismunuyo en 1");
        }
        else{
            return ok("no hay stock");
        }
    }
    
    public static  Result editEntrada(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Entrada entrada = Entrada.find.byId(id);
        
        entrada.nombre = json.findPath("nombre").textValue();
        String cadena1 = json.findPath("precio").textValue();
        entrada.precio = Float.parseFloat(cadena1);
        String cadena2 = json.findPath("stock").textValue();
        entrada.stock = Integer.parseInt(cadena2);
        entrada.imagen = json.findPath("imagen").textValue();
        
        entrada.update(id);
        return ok("se edito");
    }
    
    public static Result deleteEntrada() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Entrada.find.ref(id).delete();
        return redirect("/entradas");
    }
}