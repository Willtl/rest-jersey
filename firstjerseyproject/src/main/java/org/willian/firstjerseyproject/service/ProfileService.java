package org.willian.firstjerseyproject.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import org.willian.firstjerseyproject.database.Database;
import org.willian.firstjerseyproject.model.Profile; 

public class ProfileService {

	public Map<String, Profile> profiles = Database.getProfiles();
 
	public ProfileService(){
		profiles.put("Willian", new Profile(1L, "Willian", "Willian", "Tessaro Lunardi"));
		profiles.put("Ramao", new Profile(2L, "Ramao", "Ramao", "Tiago Tiburski"));
		profiles.put("Everton", new Profile(3L, "Everton", "Everton", "de Matos"));
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String name) {
		return profiles.get(name);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profile.setCreated(new Date());
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) { 
			System.out.println("entrou no primeiro if!");
			return null;
		}
		System.out.println("entrou no segundo if!");
		profiles.put(profile.getProfileName(), profile); 
		return profile;
	} 

	public Profile removeProfile(String name) {
		return profiles.remove(name);
	}
}