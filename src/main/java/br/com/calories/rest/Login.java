package br.com.calories.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.calories.dao.MemoryUserDAO;
import br.com.calories.dao.UserDAO;
import br.com.calories.model.User;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Login {
	
	private UserDAO dao = new MemoryUserDAO();
	
	@GET
	@Path("/login")
	public User login(@QueryParam("email") String email, @QueryParam("password") String password) {
		User user = dao.find(email);
		if(user == null || !password.equals(user.getPassword())) {
			return null;
		}
		return user;
	}

}
