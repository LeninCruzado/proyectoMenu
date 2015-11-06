package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity 
public class Tipousuario extends Model {

    @Id
    public Long id;

    @Constraints.Required(message="Debe ingresar el nombre")
    public String nombre;

	// Generic query helper for entity with id Long
	public static Model.Finder<Long,Tipousuario> find = new Model.Finder<Long,Tipousuario>(Long.class, Tipousuario.class);

	// Para usar en select list
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Tipousuario c: Tipousuario.find.orderBy("nnombre").findList()) {
            options.put(c.id.toString(), c.nombre);
        }
        return options;
    }

}
