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

public class Pedidos extends Controller {

//GET
    public static Result getPedidos(){
        List<Pedido> pedidos = Pedido.find.all();
        return ok(toJson(pedidos));
    }
    
    public static Result getPedido(Long id){
        Pedido pedido = Pedido.find.byId(id);
        return ok(toJson(pedido));
    }
    /*
    public static Result getPedidoEstado(String estado){
        List<Pedido> pedidos = Pedido.find.where().ilike("estado", estado);//"%coco%"
        if(pedidos == null) {
            return badRequest("No existe pedido con ese estado");
        }
        return ok(toJson(pedidos));
     }*/
     /*
     List<Task> tasks = find.where()
    .ilike("name", "%coco%")
    .orderBy("dueDate asc")
    .findPagingList(25)
    .setFetchAhead(false)
    .getPage(1)
    .getList();
     */
    
//POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addPedido()
    {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Pedido newPedido = new Pedido();
            
            newPedido.estado = "Pendiente";
            newPedido.tipo = json.findPath("tipo").textValue();
            
            String cadena = json.findPath("cliente").textValue();
            newPedido.cliente = Cliente.find.byId(Long.parseLong(cadena));
            
            String cadena2 = json.findPath("plato").textValue();
            newPedido.plato = Plato.find.byId(Long.parseLong(cadena2));
            newPedido.cantPlato = Integer.parseInt(json.findPath("cantPlato").textValue());
            
            String cadena3 = json.findPath("entrada").textValue();
            newPedido.entrada = Entrada.find.byId(Long.parseLong(cadena3));
            newPedido.cantEntrada = Integer.parseInt(json.findPath("cantEntrada").textValue());
            
            String cadena4 = json.findPath("bebida").textValue();
            newPedido.bebida = Bebida.find.byId(Long.parseLong(cadena4));
            newPedido.cantBebida = Integer.parseInt(json.findPath("cantBebida").textValue());
            
            String cadena5 = json.findPath("postre").textValue();
            newPedido.postre = Postre.find.byId(Long.parseLong(cadena5));
            newPedido.cantPostre = Integer.parseInt(json.findPath("cantPostre").textValue());
            
            String cadena6 = json.findPath("menu").textValue();
            newPedido.menu = Menu.find.byId(Long.parseLong(cadena6));
            newPedido.cantMenu = Integer.parseInt(json.findPath("cantMenu").textValue());
            
            String cadena7 = json.findPath("promocion").textValue();
            newPedido.promocion = Promocion.find.byId(Long.parseLong(cadena7));
            newPedido.cantPromocion = Integer.parseInt(json.findPath("cantPromocion").textValue());
            
            if(newPedido.cliente == null ) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newPedido.save();
                return redirect("/promociones");
            }
        }
     }
}
