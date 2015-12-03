package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity 
public class Cliente extends Model {

    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String nombre;

    @Constraints.Required(message="Debe ingresar el login")
    public String login;

    @Constraints.Required(message="Debe ingresar el password")
    public String password;

	@Constraints.Required(message="Debe ingresar el mail")
    @Constraints.Email
    public String email;
    
    public String direccion;
    public String telfono;
    
	
    @ManyToOne
	@Constraints.Required(message="Debe ingresar el tipo de usuario")
    public Tipousuario tipousuario;
	public Tipousuario getTipousuario(Long id) {
		return Tipousuario.find.where().idEq(id).findUnique();
	}

    // Generic query helper for entity with id Long
    public static Model.Finder<Long,Cliente> find = new Model.Finder<Long,Cliente>(Long.class, Cliente.class);

}