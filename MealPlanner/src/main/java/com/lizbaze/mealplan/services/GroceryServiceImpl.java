package com.lizbaze.mealplan.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizbaze.mealplan.entities.Grocery;
import com.lizbaze.mealplan.entities.User;
import com.lizbaze.mealplan.repositories.GroceryRepository;
import com.lizbaze.mealplan.repositories.UserRepository;

@Service
public class GroceryServiceImpl implements GroceryService {

	@Autowired
	private GroceryRepository groceryRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Grocery> findAll() {
		return groceryRepo.findAll();
	}
	
	

	@Override
	public Grocery create(String username, Grocery grocery) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			grocery.setUser(user);
			grocery = groceryRepo.saveAndFlush(grocery);
		}
		return grocery;
	}

	
	@Override
	public Grocery update(String username, int id, Grocery grocery) {
		User user = userRepo.findByUsername(username);
		Optional<Grocery> groceryOpt = groceryRepo.findById(id);
		Grocery updated = null;
		
		if (groceryOpt.isPresent()) {
			updated = groceryOpt.get();
			if (user != null && updated.getUser().getId() == user.getId()) {
				updated.setName(grocery.getName());
				updated.setCompleted(grocery.isCompleted());
				groceryRepo.saveAndFlush(updated);
			}
		}
		
		return updated;
	}


	@Override
	public List<Grocery> findByUserId(int id) {
		
		return groceryRepo.findByUser_Id(id);
	}

}
