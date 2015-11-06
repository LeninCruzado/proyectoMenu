package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.*;


public class Autentificar extends Action.Simple {

	public F.Promise <SimpleResult> call(Http.Context ctx) throws Throwable {
		
		Response response = new Response();
		response.setHeader("respuesta","salio todo bien");
		
		if(ctx.session().get("user") == null) {
			//return redirect(routes.Home.acceso_denegado());
			/*Result unauthorized = Results.unauthorized("unauthorized");
			return F.Promise.pure(unauthorized);*/
			return F.Promise.pure((SimpleResult) unauthorized("unauthorized"));
		}
		return delegate.call(ctx);

	}

}