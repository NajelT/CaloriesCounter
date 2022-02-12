package br.com.calories.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.calories.dao.MemoryUserDAO;
import br.com.calories.dao.UserDAO;
import br.com.calories.model.User;

@Path("/settings")
public class Settings {

	private UserDAO dao = new MemoryUserDAO();
	
	@POST
	@Path("/{userId}/{calories}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean setCaloriesLimit(@PathParam("userId") Integer userId,
			@PathParam("calories")  Integer caloriesLimit) {
		System.out.println("Chamou o serviço: " + caloriesLimit);
		User user = dao.find(userId);
		if (user == null) {
			return false;
		}
		user.setCaloriesLimit(caloriesLimit);
		
		User userUpdated = dao.update(user);
		if(userUpdated == null) return false;
		return true;
	}
	
}
