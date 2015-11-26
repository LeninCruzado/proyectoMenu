package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Postre extends Model {
    
    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String nombre;
    
    @Constraints.Required(message="Debe ingresar el stock")
    public Integer stock;
    
    @Constraints.Required(message="Debe ingresar el stock")
    public String imagen;
    
    // Generic query helper for entity with id long
    public static Model.Finder<Long,Postre> find = new Model.Finder<Long,Postre>(Long.class, Postre.class);
    
    // Para usar en select list
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Postre c: Postre.find.orderBy("nombre").findList()) {
            options.put(c.id.toString(), c.nombre);
        }
        return options;
    }
}