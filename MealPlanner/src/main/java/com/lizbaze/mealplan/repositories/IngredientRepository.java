package com.lizbaze.mealplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lizbaze.mealplan.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	
	public Ingredient findByName(String name);

}
