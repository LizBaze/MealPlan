package com.lizbaze.mealplan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizbaze.mealplan.controllers.IngredientRepository;
import com.lizbaze.mealplan.controllers.InstructionRepository;
import com.lizbaze.mealplan.entities.Ingredient;
import com.lizbaze.mealplan.entities.Instruction;
import com.lizbaze.mealplan.entities.Recipe;
import com.lizbaze.mealplan.entities.User;
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
	
	@Override
	public List<Recipe> findAll() {
		return recipeRepo.findAll();
	}

	@Override
	public Recipe create(Recipe recipe, String username) {
		User user = userRepo.findByUsername(username);

		if (user != null) {
			recipe.setUser(user);
			for (Ingredient ingredient: recipe.getIngredients()) {
				ingredientRepo.saveAndFlush(ingredient);
			}
			recipe = recipeRepo.saveAndFlush(recipe);
			for (Instruction instruction: recipe.getInstructions()) {
				instruction.setRecipe(recipe);
				instructionRepo.saveAndFlush(instruction);
			}
			
		}

		return recipe;
	}

}
