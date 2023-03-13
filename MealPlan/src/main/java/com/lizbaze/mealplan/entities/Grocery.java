package com.lizbaze.mealplan.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Grocery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private Boolean completed;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;

	public Grocery(int id, String name, Boolean completed, User user) {
		super();
		this.id = id;
		this.name = name;
		this.completed = completed;
		this.user = user;
	}

	public Grocery() {
		super();
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

	public Boolean isCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		Grocery other = (Grocery) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Grocery [id=" + id + ", name=" + name + ", completed=" + completed + ", user=" + user + "]";
	}
	
	

}
