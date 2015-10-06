package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Promocion extends Model {
    
    @Id
    public String id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String name;
    

    @Constraints.Required(message="Debe ingresar el precio")
    public String descripcion;    
    
    @Constraints.Required(message="Debe ingresar el precio")
    public String precioAnt;

    @Constraints.Required(message="Debe ingresar el precio")
    public String precioAct;

    // Generic query helper for entity with id long
    public static Model.Finder<Long,Promocion> find = new Model.Finder<Long,Promocion>(Long.class, Promocion.class);

    // Para usar en select list
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Plato c: Plato.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

    public Promocion (String id,String name,String descripcion,String precioAnt,String precioAct) {
        //this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.precioAnt = precioAnt;
        this.precioAct = precioAct;
    }

    
    public static Promocion create(String id,String name,String descripcion,String precioAnt,String precioAct) {
        Promocion item = new Promocion(id,name,descripcion,precioAnt,precioAct);
        item.save();
        return item;
    }
    
}
