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

    @BodyParser.Of(BodyParser.Json.class)
    
    public static Result addPromocion()
    {
        //Promocion newPromocion = Json.fromJson(request().body().asJson(), Promocion.class);
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Promocion newPromocion = new Promocion();
            //newPromocion.id = json.findPath("id").textValue();
            //String cadena = json.findPath("nombre").textValue()
            newPromocion.nombre = json.findPath("nombre").textValue();
            newPromocion.descripcion = json.findPath("descripcion").textValue();
            // String cadena1 = json.findPath("nombre").textValue()
            // newPromocion.nombre = Float.parseFloat(cadena1);
            String cadena1 = json.findPath("precioAnt").textValue();
            newPromocion.precioAnt = Float.parseFloat(cadena1);
            String cadena2 = json.findPath("precioAct").textValue();
            newPromocion.precioAct = Float.parseFloat(cadena2);
            //newPromocion.descripcion = json.findPath("descripcion").textValue();
            //newPromocion.precioAnt = json.findPath("precioAnt").textValue();
            //newPromocion.precioAct = json.findPath("precioAct").textValue();
            
            if(newPromocion.nombre == null) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newPromocion.save();
                return redirect("/promociones");
            }
        }
     }

    public static Result getPromociones(){
        List<Promocion> promociones = Promocion.find.all();
        return ok(toJson(promociones));
    }
    
    public static Result getPromocion(Long id){
        Promocion promocion = Promocion.find.byId(id);
        return ok(toJson(promocion));
    }
    
    public static Result deletePromocion() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        Promocion.find.ref(id).delete();
        //flash("success", "Entrada ha sido eliminado");
        return redirect("/promociones");
    }
}