package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;
import models.*;

import play.libs.Json;
import play.libs.Json.*;
import static play.libs.Json.toJson;
import com.fasterxml.jackson.databind.JsonNode;

public class Home extends Controller {
//GET
    public static Result index() {
        return ok(index.render());
    }

//POST
    public static Result ingresar(){
        JsonNode json = request().body().asJson();
        
        Login login = new Login();
        login.login = json.findPath("login").textValue();
        login.password = json.findPath("password").textValue();
        //Debe ingresa el usuario y la clave
        if(login.login == "" || login.password == "") {
            return badRequest("ingresar login y password");
        }
        
        // busca el usuario por login y clave
        Usuario usuario = Usuario.find.where().eq("login", login.login).eq("password", login.password).findUnique();
        if(usuario == null) {
            return badRequest("Usuario y/o clave incorrecto/s");
        }
        
        //login correcto, se setean las variables de session
        session("user", usuario.nombre);
        session("login", usuario.login);
        session("tipo", usuario.getTipousuario(usuario.tipousuario.id).nombre);
    
        return ok("ingreso");//return redirect("/");
    }
 
    
    // cambia el password
    @With(Autentificar.class)
    public static Result cambiar_password() {
        JsonNode json = request().body().asJson();
        // Debe ingresar los 3 datos
        Login login = new Login();
        login.password_act = json.findPath("password_act").textValue();
        login.password_new = json.findPath("password_new").textValue();
        login.password_rep = json.findPath("password_rep").textValue();
        
        if(login.password_act == "" || login.password_new == "" || login.password_rep == "") {
            return badRequest("Debe ingresar las claves");
        }

        // La clave nueva y repeteida deben ser iguales
        if(!login.password_new.equals(login.password_rep)) {
            return badRequest("La clave nueva debe ser igual a la repetida");
        }

        // Busca el usuario logueado y compara la clave con la actual
        Usuario usuario = Usuario.find.where().eq("login", session("login")).eq("password", login.password_act).findUnique();
        if(usuario == null) {
            return badRequest("La clave actual no es la del usuario logueado");
        }

        // Todo correcto, se cambia la clave
        usuario.password = login.password_act;
        usuario.save();//usuario.update(usuario.id);
        return ok("clave cambiada");
    }
    
    @With(Autentificar.class)
    public static Result salir(){
        session().clear();
        return ok("session cerrada");//return redirect("/");
    }

}