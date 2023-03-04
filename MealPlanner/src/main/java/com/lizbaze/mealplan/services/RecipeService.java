package com.lizbaze.mealplan.services;

import java.util.List;

import com.lizbaze.mealplan.entities.Recipe;

public interface RecipeService {
	
	public List<Recipe> findAll();
	public Recipe create(Recipe recipe, String username);
	public Recipe findById(int id);
	public Recipe edit(int id, Recipe recipe, String username);
	public List<Recipe> search(String searchTerm);
	public List<Recipe> findByUsername(String username);
	public boolean addRecipeToMealPlan(String username, int recipeId);
	public boolean clearMealPlan(String username);
	public List<Recipe> findFavoritesByUsername(String username);
	public List<Recipe> searchFavorites(String searchTerm, String username);

}
