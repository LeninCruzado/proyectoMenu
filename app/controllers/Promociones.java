package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.platos.*;

import models.*;
import java.util.List;
import static play.libs.Json.toJson;

public class Promociones extends Controller {

    // public static Result index() {
    //     return ok(index.render(Plato.find.all()));
    // }
    
    //GET, formulario para un nuevo registro
    // public static Result create() {
    //     Form<Promocion> formulario = Form.form(Promocion.class);
    //     return ok(create.render(formulario));
    // }
    
public static Result createPromocion() {
        DynamicForm requestData = Form.form().bindFromRequest();

        String id = requestData.get("id");
        String name = requestData.get("name");
        String descripcion = requestData.get("descripcion");
        String precioAnt = requestData.get("precioAnt");
        String precioAct = requestData.get("precioAct");

        Promocion.create(id,name,descripcion,precioAnt,precioAct);

        return ok("se guardo");
    }

    // //POST, se guarda el formulario
    // public static Result save() {
    //     Form<Promocion> formulario = Form.form(Promocion.class).bindFromRequest();
    //     if(formulario.hasErrors()) {
    //         return badRequest(create.render(formulario));
    //     }
    //     formulario.get().save();
    //     //flash("success","Promocion " + formulario.get().name + " creado con exito!");
    //     return ok("se guardó");
    // }
    
    // //GET,editar el registro
    // public static Result edit(Long id) {
    //     Form<Plato> formulario = Form.form(Plato.class).fill(Plato.find.byId(id));
    //     return ok(edit.render(id, formulario));
    // }
    
    // //POST, guardar el registro editado
    // public static Result update(Long id) {
    //     Form<Plato> formulario = Form.form(Plato.class).bindFromRequest();
    //     if(formulario.hasErrors()) {
    //         return badRequest(edit.render(id, formulario));
    //     }
    //     formulario.get().update(id);
    //     flash("success", "Plato " + formulario.get().name + " actualizado con exito!");
    //     return redirect("/platos/index");
    // }
    
    // //POST, elimina registro
    // public static Result delete(Long id) {
    //     Plato.find.ref(id).delete();
    //     flash("success", "Plato ha sido eliminado");
    //     return redirect("/platos/index");

    // }
    
    public static Result getPromociones(){
        List<Promocion> promociones = Promocion.find.all();
        return ok(toJson(promociones));
    }
    
    public static Result getPromocion(Long id){
        Promocion promocion = Promocion.find.byId(id);
        return ok(toJson(promocion));
    }
}


