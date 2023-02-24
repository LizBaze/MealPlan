package com.lizbaze.mealplan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizbaze.mealplan.entities.Ingredient;
import com.lizbaze.mealplan.repositories.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingRepo;
	
	@Override
	public List<Ingredient> findAll() {
		
		return ingRepo.findAll();
	}

}
