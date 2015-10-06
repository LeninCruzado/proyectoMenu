package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.postres.*;

import models.*;
import java.util.List;
import static play.libs.Json.toJson;

public class Postres extends Controller {

    public static Result index() {
        return ok(index.render(Postre.find.all()));
    }
    
    //GET, formulario para un nuevo registro
    public static Result create() {
        Form<Postre> formulario = Form.form(Postre.class);
        return ok(create.render(formulario));
    }
    
    //POST, se guarda el formulario
    public static Result save() {
        Form<Postre> formulario = Form.form(Postre.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(create.render(formulario));
        }
        formulario.get().save();
        flash("success","Postre " + formulario.get().name + " creado con exito!");
        return redirect("/postres/index");
    }
    
    //GET,editar el registro
    public static Result edit(Long id) {
        Form<Postre> formulario = Form.form(Postre.class).fill(Postre.find.byId(id));
        return ok(edit.render(id, formulario));
    }
    
    //POST, guardar el registro editado
    public static Result update(Long id) {
        Form<Postre> formulario = Form.form(Postre.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(edit.render(id, formulario));
        }
        formulario.get().update(id);
        flash("success", "Postre " + formulario.get().name + " actualizado con exito!");
        return redirect("/postres/index");
    }
    
    //POST, elimina registro
    public static Result delete(Long id) {
        Postre.find.ref(id).delete();
        flash("success", "Postre ha sido eliminado");
        return redirect("/postres/index");
    }
    
    public static Result getPostres(){
        List<Postre> postres = Postre.find.all();
        return ok(toJson(postres));
    }
    
    public static Result getPostre(Long id){
        Postre postre = Postre.find.byId(id);
        return ok(toJson(postre));
    }
}


