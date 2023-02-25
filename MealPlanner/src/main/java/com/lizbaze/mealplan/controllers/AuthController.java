package com.lizbaze.mealplan.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizbaze.mealplan.entities.Recipe;
import com.lizbaze.mealplan.entities.User;
import com.lizbaze.mealplan.services.AuthService;
import com.lizbaze.mealplan.services.RecipeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class AuthController {

	@Autowired
	private AuthService authService;
	@Autowired
	private RecipeService recipeService;

	@GetMapping("users/{userId}")
	public User getUserById(@PathVariable Integer userId, HttpServletResponse res) {
		User user = authService.getUserById(userId);
		if (user == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return user;
	}

	@GetMapping("authenticate")
	public User authenticate(Principal principal, HttpServletResponse res) {
		if (principal == null) { // no Authorization header sent
			res.setStatus(401);
			res.setHeader("WWW-Authenticate", "Basic");
			return null;
		}
		return authService.getUserByUsername(principal.getName());
	}

	@PostMapping("register")
	public User register(@RequestBody User user, HttpServletResponse res) {
		if (user == null) {
			res.setStatus(400);
			return null;
		}
		user = authService.register(user);
		return user;
	}

	@PutMapping("users/{id}/favorites/{recipeId}")
	public void addRecipeToFavorites(@PathVariable int id, @PathVariable int recipeId, HttpServletResponse res) {

		User user = authService.getUserById(id);
		Recipe recipe = recipeService.findById(recipeId);
		if (user != null && recipe != null) {
			boolean success = authService.addRecipeToFavorites(recipe, user.getUsername());
			if (success) {
				res.setStatus(200);
			} else {
				res.setStatus(400);
			}
		} else {
			res.setStatus(400);
		}

	}

}
