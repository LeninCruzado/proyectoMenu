package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.*;


public class Autentificar2 extends Action.Simple {

	public F.Promise <SimpleResult> call(Http.Context ctx) throws Throwable {
		
		Response response = new Response();
		response.setHeader("respuesta","salio todo bien");
		
		if(ctx.session().get("tipo").equals("Administrador")) {
			//return redirect(routes.Home.acceso_denegado());
			/*Result unauthorized = Results.unauthorized("unauthorized");
			return F.Promise.pure(unauthorized);*/
			return delegate.call(ctx);
		}
		return F.Promise.pure((SimpleResult) unauthorized("unauthorized"));

	}

}