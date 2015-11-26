package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.Promise;

public class Autentificar extends Action.Simple {

	public Promise <SimpleResult> call(Http.Context ctx) throws Throwable {
		
		//Response response = new Response();
		
		if(ctx.session().get("user") == null) {
			//response.setHeaders("respuesta","no");
			//return Promise.pure ( ok(response) );
			//return Promise.pure ( redirect(routes.Home.index()) );
			return Promise.pure ( (SimpleResult) ok("No esta logueado") );
		}
		return delegate.call(ctx);
	}
}