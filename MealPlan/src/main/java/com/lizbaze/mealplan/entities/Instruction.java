package com.lizbaze.mealplan.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Instruction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="recipe_id")
	private Recipe recipe;
	
	public Instruction() {}

	public Instruction(int id, String description, Recipe recipe) {
		super();
		this.id = id;
		this.description = description;
		this.recipe = recipe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
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
		Instruction other = (Instruction) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Instruction [id=" + id + ", description=" + description ;
	}

}
