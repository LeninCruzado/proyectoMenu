package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Tipomenu extends Model {
    
    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String nombre;
    
    // Generic query helper for entity with id long
    public static Model.Finder<Long,Tipomenu> find = new Model.Finder<Long,Tipomenu>(Long.class, Tipomenu.class);
}
