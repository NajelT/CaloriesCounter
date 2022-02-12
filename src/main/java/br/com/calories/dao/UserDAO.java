package br.com.calories.dao;

import java.util.Set;

import br.com.calories.model.User;

public interface UserDAO {

	User find(String email);

	User find(Integer userId);

	User update(User user);

	boolean remove(Integer userId);

	User save(User user);
	
	Set<User> findAll();

}
