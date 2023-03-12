package com.lizbaze.mealplan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lizbaze.mealplan.entities.Grocery;

public interface GroceryRepository extends JpaRepository<Grocery, Integer> {
	
	public List<Grocery> findByUser_Id(int id);

}
