package br.com.calories.rest;

import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.calories.dao.MemoryUserDAO;
import br.com.calories.dao.UserDAO;
import br.com.calories.model.Meal;
import br.com.calories.model.User;

@Path("/meals")
@Produces(MediaType.APPLICATION_JSON)
public class MealsManager {
	
	private UserDAO dao = new MemoryUserDAO();
	
	@POST
	@Path("/add/{userId}")
	public Meal addMeal(@PathParam("userId") Integer userId, 
			@QueryParam("date") long date, @QueryParam("description") String description, @QueryParam("calories") Integer calories) {
		User user = dao.find(userId);
		Meal meal = null;
		if(user != null) {
			meal = new Meal(new Date(date), description, calories);
			user.addMeal(meal);
		}
		return meal;
	}
	
	@POST
	@Path("/update/{userId}/{mealId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Meal updateMeal(@PathParam("userId") Integer userId, @PathParam("mealId") Integer mealId, 
			@QueryParam("date") long date, @QueryParam("description") String description, @QueryParam("calories") Integer calories) {
		User user = dao.find(userId);
		Meal meal = user.getMeals().stream().filter(m -> m.getId().equals(mealId)).findFirst().orElse(null);
		if(meal != null) {
			meal.setDate(new Date(date));
			meal.setDescription(description);
			meal.setCalories(calories);
		}
		return meal;
	}

	@DELETE
	@Path("/remove/{userId}/{mealId}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean removeMeal(@PathParam("userId") Integer userId, @PathParam("mealId") Integer mealId) {
		User user = dao.find(userId);
		return user.getMeals().removeIf(m -> m.getId().equals(mealId));
	}

}
