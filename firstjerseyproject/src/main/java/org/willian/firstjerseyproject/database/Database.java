package org.willian.firstjerseyproject.database;

import java.util.HashMap;
import java.util.Map;

import org.willian.firstjerseyproject.model.Comment;
import org.willian.firstjerseyproject.model.Message;
import org.willian.firstjerseyproject.model.Profile;

public class Database {

	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<Long, Comment> comments = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}

	public static Map<Long, Comment> getComments() {
		return comments;
	}

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}

}
