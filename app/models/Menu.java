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
    
    @Constraints.Required(message="Debe ingresar el precio")
    public Float precio;
    
    @ManyToOne
	@Constraints.Required(message="Debe ingresar el tipo de menu")
    public Tipomenu tipomenu;
	public Tipomenu getTipomenu(Long id) {
		return Tipomenu.find.where().idEq(id).findUnique();
	}
	
    @ManyToOne
	@Constraints.Required(message="Debe ingresar el plato")
    public Plato plato;
	public Plato getPlato(Long id) {
		return Plato.find.where().idEq(id).findUnique();
	}

	@ManyToOne
    public Entrada entrada;
	public Entrada getEntrada(Long id) {
		return Entrada.find.where().idEq(id).findUnique();
	}
    
    @ManyToOne
    public Bebida bebida;
	public Bebida getBebida(Long id) {
		return Bebida.find.where().idEq(id).findUnique();
	}
    
    @ManyToOne
    public Postre postre;
	public Postre getPostre(Long id) {
		return Postre.find.where().idEq(id).findUnique();
	}
    
    // Generic query helper for entity with id Long
    public static Model.Finder<Long,Menu> find = new Model.Finder<Long,Menu>(Long.class, Menu.class);
}