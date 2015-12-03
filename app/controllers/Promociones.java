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

public class Promociones extends Controller {

//GET
    public static Result getPromociones(){
        List<Promocion> promociones = Promocion.find.all();
        return ok(toJson(promociones));
    }
    
    public static Result getPromocion(Long id){
        Promocion promocion = Promocion.find.byId(id);
        return ok(toJson(promocion));
    }
    
        public static Result getPromocionNombre(String nombre){
        Promocion promocion = Promocion.find.where().eq("nombre", nombre).findUnique();
        if(promocion == null) {
            return badRequest("No existe promocion con ese nombre");
        }
        return ok(toJson(promocion));
    }
    
//POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addPromocion()
    {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Promocion newPromocion = new Promocion();
            
            newPromocion.nombre = json.findPath("nombre").textValue();
            newPromocion.descripcion = json.findPath("descripcion").textValue();
            
            String cadena1 = json.findPath("precioAnt").textValue();
            newPromocion.precioAnt = Float.parseFloat(cadena1);
            String cadena2 = json.findPath("precioAct").textValue();
            newPromocion.precioAct = Float.parseFloat(cadena2);
            String cadena3 = json.findPath("stock").textValue();
            newPromocion.stock = Integer.parseInt(cadena3);
            
            if(newPromocion.nombre == null || newPromocion.nombre.equals("")) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newPromocion.save();
                return redirect("/promociones");
            }
        }
     }
     
     public static Result sacarStockPromocion(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Promocion promocion = Promocion.find.byId(id);//if id existe en db
        if(promocion.stock>0){
            promocion.stock = promocion.stock - 1;
            promocion.update(id);
            return ok("stock dismunuyo en 1");
        }
        else{
            return ok("no hay stock");
        }
    }
    
    public static  Result editPromocion(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Promocion promocion = Promocion.find.byId(id);

        if(promocion != null){
            promocion.nombre = json.findPath("nombre").textValue();
            String cadena1 = json.findPath("precioAnt").textValue();
            promocion.precioAnt = Float.parseFloat(cadena1);
            String cadena2 = json.findPath("precioAct").textValue();
            promocion.precioAct = Float.parseFloat(cadena2);
            String cadena3 = json.findPath("stock").textValue();
            promocion.stock = Integer.parseInt(cadena3);
            
            promocion.update(id);
            return ok("se edito");    
        }else return ok("no se encontro");
    }
    
    public static Result deletePromocion() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Promocion.find.ref(id).delete();
        return redirect("/promociones");
    }
}