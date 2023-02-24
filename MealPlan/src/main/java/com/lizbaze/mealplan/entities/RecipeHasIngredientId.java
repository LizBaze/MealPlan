package com.lizbaze.mealplan.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable

public class RecipeHasIngredientId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="recipe_id")
	private int recipeId;
	
	private int ingredientId;
	
	
	public RecipeHasIngredientId() {
		
	}

	public RecipeHasIngredientId(int recipeId, int IngredientId) {
		this.recipeId = recipeId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredientId, recipeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeHasIngredientId other = (RecipeHasIngredientId) obj;
		return ingredientId == other.ingredientId && recipeId == other.recipeId;
	}
	
	
}
