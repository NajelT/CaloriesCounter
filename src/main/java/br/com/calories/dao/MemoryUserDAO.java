package br.com.calories.dao;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import br.com.calories.model.Meal;
import br.com.calories.model.Profile;
import br.com.calories.model.User;

public class MemoryUserDAO implements UserDAO {
	
	private static Set<User> users = new HashSet<User>();
	
	static {
		User regular_user = new User("Cindy Soares", "cindy@email.com", "senha", 1800, Profile.USER);
		regular_user.addMeal(new Meal(new GregorianCalendar(2015, 10, 2, 8, 30).getTime(),
				"breakfast", 250));
		regular_user.addMeal(new Meal(new GregorianCalendar(2015, 10, 2, 12, 30).getTime(),
				"lunch", 415));
		regular_user.addMeal(new Meal(new GregorianCalendar(2015, 10, 2, 19, 30).getTime(),
				"dinner", 550));
		regular_user.addMeal(new Meal(new GregorianCalendar(2015, 10, 3, 8, 30).getTime(),
				"breakfast", 300));
		regular_user.addMeal(new Meal(new GregorianCalendar(2015, 10, 3, 12, 30).getTime(),
				"lunch", 500));
		regular_user.addMeal(new Meal(new GregorianCalendar(2015, 10, 3, 19, 30).getTime(),
				"dinner", 600));
		regular_user.addMeal(new Meal(new GregorianCalendar(2015, 10, 3, 17, 30).getTime(),
				"snack", 150));
		
		users.add(regular_user);
		users.add(new User("User manager", "manager@email.com", "1234", null, Profile.USER_MANAGER));
		users.add(new User("User admin", "admin@email.com", "4321", null, Profile.ADMIN_MANAGER));
	}
	
	public User find(Integer userId) {
		return (User) users.stream().filter(u -> u.getId().equals(userId)).findFirst().orElse(null);
	}
	
	public User find(String email) {
		return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
	}
	
	public User save(User user) {
		boolean saved = users.add(user);
		if(saved) return user;
		return null;
	}
	
	public User update(User user) {
		boolean removed = users.remove(user);
		if(removed) {
			boolean saved = users.add(user);
			if(saved) return user;
		}
		return null;
	}
	
	public boolean remove(Integer userId) {
		return users.remove(find(userId));
	}
	
	@Override
	public Set<User> findAll() {
		return users;
	}

}
