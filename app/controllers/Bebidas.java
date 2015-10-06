package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.bebidas.*;

import models.*;
import java.util.List;
import static play.libs.Json.toJson;

public class Bebidas extends Controller {

    public static Result index() {
        return ok(index.render(Bebida.find.all()));
    }
    
    //GET, formulario para un nuevo registro
    public static Result create() {
        Form<Bebida> formulario = Form.form(Bebida.class);
        return ok(create.render(formulario));
    }
    
    //POST, se guarda el formulario
    public static Result save() {
        Form<Bebida> formulario = Form.form(Bebida.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(create.render(formulario));
        }
        formulario.get().save();
        flash("success","Bebida " + formulario.get().name + " creado con exito!");
        return redirect("/bebidas/index");
    }
    
    //GET,editar el registro
    public static Result edit(Long id) {
        Form<Bebida> formulario = Form.form(Bebida.class).fill(Bebida.find.byId(id));
        return ok(edit.render(id, formulario));
    }
    
    //POST, guardar el registro editado
    public static Result update(Long id) {
        Form<Bebida> formulario = Form.form(Bebida.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(edit.render(id, formulario));
        }
        formulario.get().update(id);
        flash("success", "Bebida " + formulario.get().name + " actualizado con exito!");
        return redirect("/bebidas/index");
    }
    
    //POST, elimina registro
    public static Result delete(Long id) {
        Bebida.find.ref(id).delete();
        flash("success", "Bebidas ha sido eliminado");
        return redirect("/bebidas/index");
    }
    
    public static Result getBebidas(){
        List<Bebida> bebidas = Bebida.find.all();
        return ok(toJson(bebidas));
    }
    
    public static Result getBebida(Long id){
        Bebida bebida = Bebida.find.byId(id);
        return ok(toJson(bebida));
    }
}


