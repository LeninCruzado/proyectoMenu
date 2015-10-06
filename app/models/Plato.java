package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Plato extends Model {
    
    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String name;
    
    @Constraints.Required(message="Debe ingresar el precio")
    public String price;
    
    // Generic query helper for entity with id long
    public static Model.Finder<Long,Plato> find = new Model.Finder<Long,Plato>(Long.class, Plato.class);

    // Para usar en select list
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Plato c: Plato.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }
    
}
