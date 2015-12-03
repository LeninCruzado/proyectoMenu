package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Entrada extends Model {
    
    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String nombre;
    
    @Constraints.Required(message="Debe ingresar el precio")
    public Float precio;
    
    @Constraints.Required(message="Debe ingresar el stock")
    public Integer stock;
    
    @Constraints.Required(message="Debe ingresar el stock")
    public String imagen;
    
    // query generico helper for entity with id long
    public static Model.Finder<Long,Entrada> find = new Model.Finder<Long,Entrada>(Long.class, Entrada.class);
    
    public boolean disminuirStock(int cant){
        if(cant > stock){
            return false;
        }else{
            stock = stock - cant;
            return true;
        }
    }
}
