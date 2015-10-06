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
    public String name;
    
    @Formats.DateTime(pattern="dd-MM-yyyy - hh:mm")
    public Date created;
    
    @Formats.DateTime(pattern="dd-MM-yyyy - hh:mm")
    public Date updated;
    
    @ManyToOne
    @Constraints.Required(message="Debe ingresar el tipo de plato")
    public TipoMenu tipoMenu;
    public TipoMenu getTipoMenu(Long id) {
        return TipoMenu.find.where().idEq(id).findUnique();
    }
    
    @ManyToOne
    @Constraints.Required(message="Debe ingresar la entrada")
    public Entrada entrada;
    public Entrada getEntrada(Long id) {
        return Entrada.find.where().idEq(id).findUnique();
    }
    
    @ManyToOne
    @Constraints.Required(message="Debe ingresar el tipo de usuario")
    public Plato plato;
    public Plato getPlato(Long id) {
        return Plato.find.where().idEq(id).findUnique();
    }
    
    @ManyToOne
    @Constraints.Required(message="Debe ingresar el tipo de usuario")
    public Bebida bebida;
    public Bebida getBebida(Long id) {
        return Bebida.find.where().idEq(id).findUnique();
    }
    
    @ManyToOne
    @Constraints.Required(message="Debe ingresar el tipo de usuario")
    public Postre postre;
    public Postre getPostre(Long id) {
        return Postre.find.where().idEq(id).findUnique();
    }
    
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