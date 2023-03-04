package com.lizbaze.mealplan.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizbaze.mealplan.entities.Ingredient;
import com.lizbaze.mealplan.entities.Instruction;
import com.lizbaze.mealplan.entities.Recipe;
import com.lizbaze.mealplan.entities.RecipeHasIngredient;
import com.lizbaze.mealplan.entities.RecipeHasIngredientId;
import com.lizbaze.mealplan.entities.User;
import com.lizbaze.mealplan.repositories.IngredientRepository;
import com.lizbaze.mealplan.repositories.InstructionRepository;
import com.lizbaze.mealplan.repositories.RecipeHasIngredientRepository;
import com.lizbaze.mealplan.repositories.RecipeRepository;
import com.lizbaze.mealplan.repositories.UserRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private IngredientRepository ingredientRepo;

	@Autowired
	private InstructionRepository instructionRepo;
	
	@Autowired
	private RecipeHasIngredientRepository rhiRepo;
	
	@Override
	public List<Recipe> findAll() {
		return recipeRepo.findAll();
	}

	@Override
	public Recipe create(Recipe recipe, String username) {
	
		User user = userRepo.findByUsername(username);
		
		if (user != null) {
			recipe.setUser(user);
			recipeRepo.saveAndFlush(recipe);
			user.addFavorite(recipe);
			
			for (RecipeHasIngredient rHI : recipe.getIngredients()) {
				Ingredient ingredient = ingredientRepo.findByName(rHI.getIngredient().getName());
				if ( ingredient == null ) {
					ingredientRepo.saveAndFlush(rHI.getIngredient());
				} 
				RecipeHasIngredientId id = new RecipeHasIngredientId(recipe.getId(), rHI.getIngredient().getId());
				rHI.setId(id);
				rHI.setRecipe(recipe);
				rhiRepo.saveAndFlush(rHI);
			}
			for (Instruction instruction : recipe.getInstructions()) {
				instruction.setRecipe(recipe);
				instructionRepo.saveAndFlush(instruction);
			}
			
		}

		return recipe;
	}

	@Override
	public Recipe findById(int id) {
		Optional<Recipe> recipeOpt = recipeRepo.findById(id);
		Recipe recipe = null;
		if (recipeOpt.isPresent()) {
			recipe = recipeOpt.get();
		}
		return recipe;
	}

	@Override
	public Recipe edit(int id, Recipe recipe, String username) {
		User user = userRepo.findByUsername(username);
		
		Recipe editRecipe = findById(id);
		
		if (editRecipe != null && user.equals(editRecipe.getUser())) {
			editRecipe.setDescription(recipe.getDescription());
			editRecipe.setHidden(recipe.getHidden());
			editRecipe.setImageUrl(recipe.getImageUrl());
			editRecipe.setIngredients(recipe.getIngredients());
			editRecipe.setInstructions(recipe.getInstructions());
			editRecipe.setName(recipe.getName());
			recipeRepo.saveAndFlush(editRecipe);
		}
		return editRecipe;
	}

	@Override
	public List<Recipe> search(String searchTerm) {
		
		return recipeRepo.findByNameContaining(searchTerm);
	}

	@Override
	public List<Recipe> findByUsername(String username) {
		List<Recipe> recipes = recipeRepo.findByUser_Username(username);
		return recipes;
	}

	@Override
	public boolean addRecipeToMealPlan(String username, int recipeId) {
		boolean added = false;
		User user = userRepo.findByUsername(username);
		Optional<Recipe> recipeOpt = recipeRepo.findById(recipeId);
		if (user != null && recipeOpt.isPresent()) {
			Recipe recipe = recipeOpt.get();
			user.addRecipeToMealPlan(recipe);
			userRepo.saveAndFlush(user);
			added = true;
		}
		
		return added;
	}

	@Override
	public boolean clearMealPlan(String username) {
		boolean result = false;
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Iterator<Recipe> iter = user.getMealPlan().iterator();
			while (iter.hasNext()) {
				iter.next();
				iter.remove();
			}
			result = true;
		}
		return result;
	}

	@Override
	public List<Recipe> findFavoritesByUsername(String username) {
		User user = userRepo.findByUsername(username);
		List<Recipe> recipes = null;
		if (user != null) {
			recipes = user.getRecipes();
		}
		return recipes;
	}

}
