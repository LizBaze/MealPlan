package com.lizbaze.mealplan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lizbaze.mealplan.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

	public List<Recipe> findByNameContaining(String searchTerm);
	
	
}
