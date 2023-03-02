package com.lizbaze.mealplan.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	private String email;

	private Boolean enabled;

	private String role;

	@ManyToMany
	@JoinTable(name = "user_has_recipe", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
	@JsonIgnoreProperties({"user", "ingredients", "instructions"})
	private List<Recipe> recipes;

	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user", "ingredients", "instructions"})
	private List<Recipe> createdRecipes;

	public User() {
	}

	public User(int id, String username, String password, String email, List<Recipe> recipes, String role,
			List<Recipe> createdRecipes) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.recipes = recipes;
		this.role = role;
		this.createdRecipes = createdRecipes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Recipe> getCreatedRecipes() {
		return createdRecipes;
	}

	public void setCreatedRecipes(List<Recipe> createdRecipes) {
		this.createdRecipes = createdRecipes;
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
	public void addFavorite(Recipe recipe) {
		if (recipes == null) {
			recipes = new ArrayList<>();
		}
		if (! recipes.contains(recipe)) {
			recipes.add(recipe);
		}
	}
	
	public void removeFavorite(Recipe recipe) {
		if (recipes != null && recipes.contains(recipe)) {
			recipes.remove(recipe);
		}
	}
	
	public void addCreatedRecipe(Recipe recipe) {
		if (createdRecipes == null) {
			createdRecipes = new ArrayList<>();
		}
		if (! createdRecipes.contains(recipe)) {
			createdRecipes.add(recipe);
			recipe.setUser(this);
		}
	}
	
	public void removeCreatedRecipe(Recipe recipe) {
		if (createdRecipes != null && recipes.contains(recipe)) {
			createdRecipes.remove(recipe);
			recipe.setUser(null);
		}
	}

}
