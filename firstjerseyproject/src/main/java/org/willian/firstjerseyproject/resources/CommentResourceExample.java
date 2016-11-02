package org.willian.firstjerseyproject.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
 
public class CommentResourceExample { 
	
	@GET // root get
	public String root() { 
		return "This is a response from the @GET Root";
	}

	@GET
	@Path("/{commentId}")
	public String getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		//ex: webapi/messages/2/comments/1
		return "The MessageID is: " + messageId + " and the CommentID is: " + commentId;
	}
}
