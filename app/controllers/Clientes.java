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
public class Clientes extends Controller {

//GET
    public static Result getClientes(){
        List<Cliente> clientes = Cliente.find.all();
        return ok(toJson(clientes));
    }
    
    public static Result getCliente(Long id){
        Cliente cliente = Cliente.find.byId(id);
        return ok(toJson(cliente));
    }
    
    public static Result getClienteNombre(String nombre){
        Cliente cliente = Cliente.find.where().eq("nombre", nombre).findUnique();
        if(cliente == null) {
            return badRequest("No existe usuario con ese nombre");
        }
        return ok(toJson(cliente));
    }

//POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addCliente()
    {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Cliente newCliente = new Cliente();
            
            newCliente.nombre = json.findPath("nombre").textValue();
            newCliente.login = json.findPath("login").textValue();
            newCliente.password = json.findPath("password").textValue();
            newCliente.email = json.findPath("email").textValue();
            newCliente.direccion = json.findPath("direccion").textValue();
            newCliente.telfono = json.findPath("telfono").textValue();
            
            newCliente.tipousuario = Tipousuario.find.where().eq("nombre", "Cliente").findUnique();
            
            if(newCliente.nombre == null || newCliente.nombre.equals("")) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newCliente.save();
                return redirect("/promociones");
            }
        }
     }
    
    public static  Result editCliente(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Cliente cliente = Cliente.find.byId(id);
        
        if(cliente != null){
            cliente.nombre = json.findPath("nombre").textValue();
            cliente.login = json.findPath("login").textValue();
            cliente.password = json.findPath("password").textValue();
            cliente.email = json.findPath("email").textValue();
            cliente.direccion = json.findPath("direccion").textValue();
            cliente.telfono = json.findPath("telfono").textValue();
            
            cliente.update(id);
            return ok("se edito");
        }else return ok("no se encontro");
    }
    
    public static Result deleteCliente() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Cliente.find.ref(id).delete();
        return redirect("/promociones");
    }
}
