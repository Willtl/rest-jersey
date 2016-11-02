package org.willian.firstjerseyproject.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectResource {

	@GET
	@Path("annotations")
	public String getParamUsingAnnotations(@MatrixParam("param") String matrixParam,
			@HeaderParam("customHeaderValue") String header, 
			@HeaderParam("authTokenID") String token,
			@CookieParam("cookieName") String name) {
		
		//MatrixParam: webapi/injectdemo/annotations;param=Valor
		//HeaderParam: use key "customHeaderValue" aside the value
		//HeaderParam: use "authTokenID" and value "@Ww33423554"
		if (token.equals("@Ww33423554")){
			return "Authenticated \"@Ww33423554\"! \nMatrix param is: " + matrixParam + "\nCustom Header param is: " + header;
		}
		return null;
	}
}
