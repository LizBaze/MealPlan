package com.lizbaze.mealplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lizbaze.mealplan.entities.RecipeHasIngredient;

public interface RecipeHasIngredientRepository extends JpaRepository<RecipeHasIngredient, Integer> {

}
