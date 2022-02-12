package br.com.calories.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	
	private static int counter = 0;
	
	private Integer id;
	private String name;
	private String email;	
	private List<Meal> meals = new LinkedList<Meal>();
	private Integer caloriesLimit;
	
	private Profile profile;
	
	private transient String password;
	
	public User() {
	}
	
	public User(String name, String email, String password, Integer caloriesLimit, Profile profile) {
		this.id = ++counter;
		this.name = name;
		this.email = email;
		this.password = password;
		this.caloriesLimit = caloriesLimit;
		this.profile = profile;
	}
		
	public Integer getId() {
		return id;
	}
	
	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Meal> getMeals() {
		return meals;
	}
	public boolean addMeal(Meal meal) {
		return this.meals.add(meal);
	}
	public Integer getCaloriesLimit() {
		return caloriesLimit;
	}
	public void setCaloriesLimit(Integer caloriesLimit) {
		this.caloriesLimit = caloriesLimit;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
