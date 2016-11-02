package org.willian.firstjerseyproject.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.willian.firstjerseyproject.model.ErrorMessage;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {

	@Override
	public Response toResponse(UnauthorizedException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 401,
				"http://localhost:8080/firstjerseyproject/documentation/index.jsp");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}
}
