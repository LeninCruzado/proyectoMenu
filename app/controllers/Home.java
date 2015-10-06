package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import views.html.home.*;

public class Home extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    
    public static Result nosotros() {
        return ok(nosotros.render());
    }
    
    public static Result ingresar(){
        session("user","Neil Esteves");
        session("tipo","Administrador");
        return redirect(routes.Home.index());   //return redirect("/");
    }
    
    public static Result salir(){
        session().clear();
        return redirect(routes.Home.index());   //return redirect("/");
    }

}