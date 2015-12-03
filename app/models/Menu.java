package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity 
public class Menu extends Model {

    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String nombre;

	
    @ManyToOne
	@Constraints.Required(message="Debe ingresar el tipo de usuario")
    public Tipomenu tipomenu;
	public Tipomenu getTipomenu(Long id) {
		return Tipomenu.find.where().idEq(id).findUnique();
	}

    // Generic query helper for entity with id Long
    public static Model.Finder<Long,Menu> find = new Model.Finder<Long,Menu>(Long.class, Menu.class);
}