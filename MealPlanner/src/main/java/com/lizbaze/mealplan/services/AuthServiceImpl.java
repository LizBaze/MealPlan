package com.lizbaze.mealplan.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lizbaze.mealplan.entities.Recipe;
import com.lizbaze.mealplan.entities.User;
import com.lizbaze.mealplan.repositories.RecipeRepository;
import com.lizbaze.mealplan.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Override
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("user");
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User getUserById(int id) {
		Optional<User> userOpt = userRepo.findById(id);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
//		return userOpt.isPresent() ? userOpt.get() : null;
	}

	@Override
	public boolean addRecipeToFavorites(Recipe recipe, String username) {
		boolean success = false;
		User user = userRepo.findByUsername(username);
		if (user != null) {
			user.addFavorite(recipe);
			userRepo.saveAndFlush(user);
			success = true;
		}
		return success;
	}

	@Override
	public boolean removeRecipeFromFavorites(Recipe recipe, String username) {
		boolean success = false;
		User user = userRepo.findByUsername(username);
		if (user != null && user.getRecipes().contains(recipe)) {
			user.removeFavorite(recipe);
			userRepo.saveAndFlush(user);
			success = true;
		}
		
		return success;
	}
	
	

}
