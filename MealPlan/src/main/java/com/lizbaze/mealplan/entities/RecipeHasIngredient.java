package com.lizbaze.mealplan.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RecipeHasIngredient {
	

	@EmbeddedId
	private RecipeHasIngredientId id;
	
	@ManyToOne
	@JoinColumn(name="recipe_id")
	@MapsId(value="recipeId")
	@JsonIgnore
	private Recipe recipe;
	
	@ManyToOne
	@JoinColumn(name="ingredient_id")
	@MapsId(value="ingredientId")
	private Ingredient ingredient;
	
	private String amount;

	public RecipeHasIngredient(RecipeHasIngredientId id, Recipe recipe, Ingredient ingredient, String amount) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.amount = amount;
	}

	public RecipeHasIngredient() {
		super();
	}

	public RecipeHasIngredientId getId() {
		return id;
	}

	public void setId(RecipeHasIngredientId id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeHasIngredient other = (RecipeHasIngredient) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RecipeHasIngredient [id=" + id + ", recipe=" + recipe + ", ingredient=" + ingredient + ", amount="
				+ amount + "]";
	}
	
	
}
