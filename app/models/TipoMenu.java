package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Tipomenu extends Model {
    
    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String nombre;
    
    // Generic query helper for entity with id long
    public static Model.Finder<Long,Tipomenu> find = new Model.Finder<Long,Tipomenu>(Long.class, Tipomenu.class);
    
    // Para usar en select list
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Tipomenu c: Tipomenu.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.nombre);
        }
        return options;
    }
    
}
