package br.com.calories.rest;

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.calories.dao.MemoryUserDAO;
import br.com.calories.dao.UserDAO;
import br.com.calories.model.Profile;
import br.com.calories.model.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersManager {

	private UserDAO dao = new MemoryUserDAO();
	
	@POST
	@Path("/add")
	public User addUser(@QueryParam("name") String name, @QueryParam("email") String email,
			@QueryParam("password") String password, @QueryParam("profile") String profileName) {
		Profile profile = getProfile(profileName);
		User user = new User(name, email, password, 2000, profile);
		return dao.save(user);
	}

	@POST
	@Path("/update/{userId}")
	public User updateUser(@PathParam("userId") Integer userId, 
			@QueryParam("name") String name, @QueryParam("email") String email, 
			@QueryParam("password")	String password, @QueryParam("profile") String profileName) {
		User user = dao.find(userId);
		if (user == null) return null;
		user.setName(name);
		user.setEmail(email);
		if(password != null && !password.trim().isEmpty()) {
			user.setPassword(password);
		}
		user.setProfile(getProfile(profileName));
		return dao.update(user);
	}

	@DELETE
	@Path("/remove/{userId}")
	public boolean removeMeal(@PathParam("userId") Integer userId) {
		return dao.remove(userId);
	}

	private Profile getProfile(String profileName) {
		Profile profile = null;
		if(profileName != null) {
			try {
				profile = Profile.valueOf(profileName.toUpperCase());
			} catch(IllegalArgumentException ignored) {}
		}
		return profile;
	}
	
	@GET
	@Path("/all")
	public Set<User> getAll() {
		return dao.findAll();
	}

}
