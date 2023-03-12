package com.lizbaze.mealplan.services;

import java.util.List;

import com.lizbaze.mealplan.entities.Grocery;

public interface GroceryService {
	
	public List<Grocery> findAll();
	public List<Grocery> findByUserId(int id);
	public Grocery create(String username, Grocery grocery);
	public Grocery update(String username, int id, Grocery grocery);

}
