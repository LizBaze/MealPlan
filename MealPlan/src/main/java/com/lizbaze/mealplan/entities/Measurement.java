package com.lizbaze.mealplan.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Measurement {
	
	@Id
	private int id;
	
	private String description;
	
	public Measurement() {}
	
	public Measurement(int id, String description) {
		this.id = id;
		this.description = description;
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
		Measurement other = (Measurement) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Measurement [id=" + id + ", description=" + description + "]";
	}

	
	
}
