package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.entradas.*;

import models.*;
import java.util.List;
import static play.libs.Json.toJson;

public class Entradas extends Controller {

    public static Result index() {
        return ok(index.render(Entrada.find.all()));
    }
    
    //GET, formulario para un nuevo registro
    public static Result create() {
        Form<Entrada> formulario = Form.form(Entrada.class);
        return ok(create.render(formulario));
    }
    
    //POST, se guarda el formulario
    public static Result save() {
        Form<Entrada> formulario = Form.form(Entrada.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(create.render(formulario));
        }
        formulario.get().save();
        flash("success","Entrada " + formulario.get().name + " creado con exito!");
        return redirect("/entradas/index");
    }
    
    //GET,editar el registro
    public static Result edit(Long id) {
        Form<Entrada> formulario = Form.form(Entrada.class).fill(Entrada.find.byId(id));
        return ok(edit.render(id, formulario));
    }
    
    //POST, guardar el registro editado
    public static Result update(Long id) {
        Form<Entrada> formulario = Form.form(Entrada.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(edit.render(id, formulario));
        }
        formulario.get().update(id);
        flash("success", "Entrada " + formulario.get().name + " actualizado con exito!");
        return redirect("/entradas/index");
    }
    
    //POST, elimina registro
    public static Result delete(Long id) {
        Entrada.find.ref(id).delete();
        flash("success", "Entrada ha sido eliminado");
        return redirect("/entradas/index");
    }
    
    public static Result getEntradas(){
        List<Entrada> entradas = Entrada.find.all();
        return ok(toJson(entradas));
    }
    
    public static Result getEntrada(Long id){
        Entrada entrada = Entrada.find.byId(id);
        return ok(toJson(entrada));
    }
}


