package com.lizbaze.mealplan.services;

import java.util.List;

import com.lizbaze.mealplan.entities.Recipe;

public interface RecipeService {
	
	public List<Recipe> findAll();
	public Recipe create(Recipe recipe, String username);
	public Recipe findById(int id);
	public Recipe edit(int id, Recipe recipe, String username);

}
