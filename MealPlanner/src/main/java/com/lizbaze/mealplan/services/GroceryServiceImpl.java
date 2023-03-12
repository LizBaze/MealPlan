package com.lizbaze.mealplan.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizbaze.mealplan.entities.Grocery;
import com.lizbaze.mealplan.repositories.GroceryRepository;

@Service
public class GroceryServiceImpl implements GroceryService {

	@Autowired
	private GroceryRepository groceryRepo;
	
	@Override
	public List<Grocery> findAll() {
		return groceryRepo.findAll();
	}
	
	

	@Override
	public Grocery create(Grocery grocery) {
		grocery = groceryRepo.saveAndFlush(grocery);
		return grocery;
	}

	@Override
	public Grocery update(Grocery grocery) {
		Optional<Grocery> groceryOpt = groceryRepo.findById(grocery.getId());
		Grocery updated = null;
		if (groceryOpt.isPresent()) {
			updated = groceryOpt.get();
			updated.setName(grocery.getName());
			updated.setCompleted(grocery.isCompleted());
		}
		return updated;
	}



	@Override
	public List<Grocery> findByUserId(int id) {
		
		return groceryRepo.findByUser_Id(id);
	}

}
