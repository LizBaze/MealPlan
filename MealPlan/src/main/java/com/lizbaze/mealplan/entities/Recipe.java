package com.lizbaze.mealplan.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	private Boolean hidden;
	
	@Column(name="image_url")
	private String imageUrl;

	@OneToMany(mappedBy="recipe")
	private List<RecipeHasIngredient> ingredients;

	@OneToMany(mappedBy = "recipe")
	private List<Instruction> instructions;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties({"mealPlan, recipes"})
	private User user;

	public Recipe() {
	}

	public Recipe(int id, String name, String description, List<RecipeHasIngredient> ingredients, List<Instruction> instructions,
			String imageUrl, User user, Boolean hidden) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.imageUrl = imageUrl;
		this.user = user;
		this.hidden = hidden;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RecipeHasIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeHasIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String image) {
		this.imageUrl = image;
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
		Recipe other = (Recipe) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", ingredients=" + ingredients
				+ "]";
	}
	
	public void addIngredient(RecipeHasIngredient ingredient) {
		if (ingredients == null) {
			ingredients = new ArrayList<>();
		}
		if(!ingredients.contains(ingredient)) {
			ingredients.add(ingredient);
			ingredient.setRecipe(this);
	
		}
	}
	
	public void removeIngredient(RecipeHasIngredient ingredient) {
		if (ingredients != null && ingredients.contains(ingredient)) {
			ingredients.remove(ingredient);
			ingredient.setRecipe(null);
		}
	}

}
