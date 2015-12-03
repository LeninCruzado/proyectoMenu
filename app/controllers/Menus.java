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

//@With(AutentificarAdm.class)
public class Menus extends Controller {

//GET
    public static Result getMenus(){
        List<Menu> menus = Menu.find.all();
        return ok(toJson(menus));
    }
    
    public static Result getMenu(Long id){
        Menu menu = Menu.find.byId(id);
        return ok(toJson(menu));
    }
    
    public static Result getMenuNombre(String nombre){
        Menu menu = Menu.find.where().eq("nombre", nombre).findUnique();
        if(menu == null) {
            return badRequest("No existe usuario con ese nombre");
        }
        return ok(toJson(menu));
    }

//POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addMenu()
    {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Menu newMenu = new Menu();
            
            newMenu.nombre = json.findPath("nombre").textValue();
            String cadena = json.findPath("precio").textValue();
            newMenu.precio = Float.parseFloat(cadena);

            String cadena1 = json.findPath("tipomenu").textValue();//tipomenu debe ser id de tipomenu
            newMenu.tipomenu = Tipomenu.find.byId(Long.parseLong(cadena1));
            String cadena2 = json.findPath("plato").textValue();//plato debe ser id de plato
            newMenu.plato = Plato.find.byId(Long.parseLong(cadena2));
            String cadena3 = json.findPath("entrada").textValue();//entrada debe ser id de entrada
            newMenu.entrada = Entrada.find.byId(Long.parseLong(cadena3));
            String cadena4 = json.findPath("bebida").textValue();//bebida debe ser id de bebida
            newMenu.bebida = Bebida.find.byId(Long.parseLong(cadena4));
            String cadena5 = json.findPath("postre").textValue();//postre debe ser id de postre
            newMenu.postre = Postre.find.byId(Long.parseLong(cadena5));
            
            if(newMenu.nombre == null || newMenu.plato == null) {
                return badRequest("Missing parameter");
            } else {
                newMenu.save();
                return redirect("/promociones");
            }
        }
     }
    
    public static  Result editMenu(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Menu menu = Menu.find.byId(id);

        if(menu != null){
            menu.nombre = json.findPath("nombre").textValue();
            String cadena0 = json.findPath("precio").textValue();
            menu.precio = Float.parseFloat(cadena0);

            String cadena1 = json.findPath("tipomenu").textValue();//tipomenu debe ser id de tipomenu
            menu.tipomenu = Tipomenu.find.byId(Long.parseLong(cadena1));
            String cadena2 = json.findPath("plato").textValue();//plato debe ser id de plato
            menu.plato = Plato.find.byId(Long.parseLong(cadena2));
            String cadena3 = json.findPath("entrada").textValue();//entrada debe ser id de entrada
            menu.entrada = Entrada.find.byId(Long.parseLong(cadena3));
            String cadena4 = json.findPath("bebida").textValue();//bebida debe ser id de bebida
            menu.bebida = Bebida.find.byId(Long.parseLong(cadena4));
            String cadena5 = json.findPath("postre").textValue();//postre debe ser id de postre
            menu.postre = Postre.find.byId(Long.parseLong(cadena5));
        
            menu.update(id);
            return ok("se edito");
        }else return ok("no se encontro");
    }
    
    public static Result deleteMenu() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Menu.find.ref(id).delete();
        return redirect("/promociones");
    }
}
