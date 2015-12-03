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
    
    @ManyToOne
    public Plato plato;
    public int cantPlato;
	public Plato getPlato(Long id) {
		return Plato.find.where().idEq(id).findUnique();
	}
    
    @ManyToOne
    public Entrada entrada;
    public int cantEntrada;
	public Entrada getEntrada(Long id) {
		return Entrada.find.where().idEq(id).findUnique();
	}
    
    @ManyToOne
    public Bebida bebida;
    public int cantBebida;
	public Bebida getBebida(Long id) {
		return Bebida.find.where().idEq(id).findUnique();
	}
    
    @ManyToOne
    public Postre postre;
    public int cantPostre;
	public Postre getPostre(Long id) {
		return Postre.find.where().idEq(id).findUnique();
	}
    
    @ManyToOne
    public Menu menu;
    public int cantMenu;
	public Menu getMenu(Long id) {
		return Menu.find.where().idEq(id).findUnique();
	}
    
    @ManyToOne
    public Promocion promocion;
    public int cantPromocion;
	public Promocion getPromocion(Long id) {
		return Promocion.find.where().idEq(id).findUnique();
	}
    

    // Generic query helper for entity with id long
    public static Model.Finder<Long,Pedido> find = new Model.Finder<Long,Pedido>(Long.class, Pedido.class);
}
