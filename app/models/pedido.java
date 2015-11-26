package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Pedido extends Model {
    
    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String nombre;
    
    @Constraints.Required(message="Debe ingresar la descripcion")
    public String descripcion;
    
    @Constraints.Required(message="Debe ingresar la descripcion")
    public String estado;
    

    // Generic query helper for entity with id long
    public static Model.Finder<Long,Pedido> find = new Model.Finder<Long,Pedido>(Long.class, Pedido.class);

    // Para usar en select list
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Pedido c: Pedido.find.orderBy("nombre").findList()) {
            options.put(c.id.toString(), c.nombre);
        }
        return options;
    }
}
