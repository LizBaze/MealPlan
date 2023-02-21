package com.lizbaze.mealplan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizbaze.mealplan.entities.Recipe;
import com.lizbaze.mealplan.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepo;
	
	@Override
	public List<Recipe> findAll() {
		return recipeRepo.findAll();
	}


	@Override
	public Recipe create(Recipe recipe) {
		
		recipe = recipeRepo.saveAndFlush(recipe);
		
		return recipe;
	}

}
