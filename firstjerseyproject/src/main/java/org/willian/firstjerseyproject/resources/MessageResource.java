package org.willian.firstjerseyproject.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response; 
import javax.ws.rs.core.UriInfo;

import org.willian.firstjerseyproject.service.MessageService;
import org.willian.firstjerseyproject.exception.UnauthorizedException;
import org.willian.firstjerseyproject.model.Message;
import org.willian.firstjerseyproject.resources.beans.MessageFilterBean;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON) // all methods will Consume JSON
@Produces(MediaType.APPLICATION_JSON) // all methods will Consume JSON
public class MessageResource {

	MessageService messageService = new MessageService();

	// filtering using @QueryParam
	@GET
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size) {
		if (year > 0) { // ex1: webapi/messages?year=2016
			return messageService.getAllMessagesForYear(year);
		}
		if (start > 0 && size > 0) { // ex2: webapi/messages?start=1&size=2
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}

	// filtering using @BeanParam annotations
	@GET
	@Path("/bean")
	public Response getMessagesBeanAnnotations(@BeanParam MessageFilterBean bean) {

		List<Message> messages;

		if (bean.getToken() == null) {
			//could be just
			//return Response.status(Status.UNAUTHORIZED).build();
			throw new UnauthorizedException("The authTokenID is empty.");
		} else if (bean.getToken().equals("@Ww33423554")) {
			if (bean.getYear() > 0) {
				messages = messageService.getAllMessagesForYear(bean.getYear());
				return Response.ok(new GenericEntity<List<Message>>(messages) {
				}).build();
			}
			if (bean.getStart() > 0 && bean.getSize() > 0) {
				messages = messageService.getAllMessagesPaginated(bean.getStart(), bean.getSize());
				return Response.ok(new GenericEntity<List<Message>>(messages) {
				}).build();
			}
			messages = messageService.getAllMessages();
			return Response.ok(new GenericEntity<List<Message>>(messages) {
			}).build();
		} else {
			//could be just
			//return Response.status(Status.UNAUTHORIZED).build();
			throw new UnauthorizedException("The authTokenID: '" + bean.getToken() + "' is not correct.");
		}
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message);

		// it avoids hardcode when describing the URI at created() method
		String newId = String.valueOf(message.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();

		// created() sends .status(Status.CREATED) plus the URI back
		return Response.created(uri).entity(newMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}

	// subResources
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {

		return new CommentResource();
	}
}
