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
}
