package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.Promise;

public class AutentificarAdm extends Action.Simple {

	public Promise <SimpleResult> call(Http.Context ctx) throws Throwable {
		
		//Response response = new Response();
		
		if(ctx.session().get("user") == null) {
			//response.setHeader("respuesta","no");
			//return Promise.pure ( ok(response) );
            //return Promise.pure((SimpleResult) unauthorized("unauthorized"));
			return Promise.pure ( (SimpleResult) ok("No logueado") );
		}
        
        if(!ctx.session().get("tipo").equals("Administrador")) {
			//response.setHeader("respuesta","si");
			//return Promise.pure ( ok(response) );
			//return Promise.pure ( redirect(routes.Home.index()) );
			return Promise.pure ( (SimpleResult) ok("No es Administrador") );
		}
		return delegate.call(ctx);
	}
}