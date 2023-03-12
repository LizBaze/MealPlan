package com.lizbaze.mealplan.services;

import java.util.List;

import com.lizbaze.mealplan.entities.Grocery;

public interface GroceryService {
	
	public List<Grocery> findAll();
	public List<Grocery> findByUserId(int id);
	public Grocery create(Grocery grocery);
	public Grocery update(Grocery grocery);

}
