package com.lizbaze.mealplan.services;

import com.lizbaze.mealplan.entities.User;

public interface AuthService {
	
	public User register(User user);
	public User getUserByUsername(String username);

}
