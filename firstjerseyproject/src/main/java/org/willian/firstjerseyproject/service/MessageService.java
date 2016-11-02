package org.willian.firstjerseyproject.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.willian.firstjerseyproject.database.Database;
import org.willian.firstjerseyproject.exception.DataNotFoundException;
import org.willian.firstjerseyproject.model.Comment;
import org.willian.firstjerseyproject.model.Message;

public class MessageService {

	public Map<Long, Message> messages = Database.getMessages();
	public Map<Long, Comment> comments = Database.getComments();

	public MessageService() {
		messages.put(1L, new Message(1L, "Olha ai bixo!", "Willian Tessaro Lunardi"));
		messages.put(2L, new Message(2L, "Esta fera meu!", "Ramão Tiburski"));
		messages.put(3L, new Message(3L, "Oito e sete bixo!", "Everton de Matos"));
		messages.put(4L, new Message(4L, "Beijo no ombro.", "Luciana Berta Sandri"));
		// comments to be added to the Message 5
		comments.put(1L, new Comment(1L, "Primeiro comentario", "Willian Tessaro Lunardi"));
		comments.put(2L, new Comment(2L, "Segundo comentario", "Rafaela Berta Sandri"));
		// message 5 with the comments above
		messages.put(5L, new Message(5L, "Post numero 1414", "Milton Lunardi", comments));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	//throwing custom exceptions, in this case DataNotFoundException is an custom exception
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message with id " + id + " not found.");
		}
		
		return messages.get(id);
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		message.setCreated(new Date());
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			System.out.println("entrou no primeiro if!");
			return null;
		}
		messages.put(message.getId(), message);
		System.out.println("entrou no segundo if!");
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}

	// filtering
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();

		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}

	// pagination
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		return list.subList(start - 1, (start - 1) + size);
	}
}