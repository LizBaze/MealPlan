package com.lizbaze.mealplan.services;

import java.util.List;

import com.lizbaze.mealplan.entities.Recipe;

public interface RecipeService {
	
	public List<Recipe> findAll();
	public Recipe create(Recipe recipe);

}
