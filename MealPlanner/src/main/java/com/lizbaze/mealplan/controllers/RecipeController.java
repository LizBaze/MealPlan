package com.lizbaze.mealplan.controllers;

import java.security.Principal;
import java.util.List;

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
public class RecipeController {

	@Autowired
	private RecipeService recipeServ;
	@Autowired
	private AuthService auth;

	@GetMapping("recipes")
	public List<Recipe> findAll(HttpServletResponse res) {

		return recipeServ.findAll();
	}

	@PostMapping("recipes")
	public Recipe create(@RequestBody Recipe recipe, Principal principal, HttpServletResponse res) {
		recipe = recipeServ.create(recipe, principal.getName());

		if (recipe != null) {
			res.setStatus(201);
		} else {
			res.setStatus(400);
		}
		return recipe;
	}

	@PutMapping("recipes/{id}")
	public Recipe edit(@RequestBody Recipe recipe, @PathVariable int id, Principal principal, HttpServletResponse res) {

		recipe = recipeServ.edit(id, recipe, principal.getName());

		if (recipe != null) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}

		return recipe;
	}

	@GetMapping("recipes/{searchTerm}")
	public List<Recipe> search(@PathVariable String searchTerm, HttpServletResponse res) {
		List<Recipe> recipes = recipeServ.search(searchTerm);
		if (recipes != null) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}

		return recipes;
	}

	@GetMapping("users/{id}/recipes")
	public List<Recipe> findByUser(@PathVariable int id, HttpServletResponse res) {
		User user = auth.getUserById(id);
		List<Recipe> recipes = recipeServ.findByUsername(user.getUsername());

		if (recipes != null) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}
		return recipes;
	}

	@PutMapping("users/mealPlan")
	public void createMealPlan(@RequestBody List<Recipe> recipes, Principal principal, HttpServletResponse res) {
		boolean success = true;
		if (! recipeServ.clearMealPlan(principal.getName()) ) {
			success = false;
		}
		for (Recipe recipe : recipes) {
			if ( ! recipeServ.addRecipeToMealPlan(principal.getName(), recipe.getId())) {
				success = false;
			}
		}

		if (success) {
			res.setStatus(204);
		} else {
			res.setStatus(400);
		}

	}

}
