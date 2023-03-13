package com.lizbaze.mealplan.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizbaze.mealplan.entities.Grocery;
import com.lizbaze.mealplan.entities.User;
import com.lizbaze.mealplan.services.AuthService;
import com.lizbaze.mealplan.services.GroceryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class GroceryController {

	@Autowired
	private GroceryService grocServ;
	@Autowired
	private AuthService auth;

	@GetMapping("groceries")
	public List<Grocery> get(Principal principal, HttpServletResponse res) {

		User user = auth.getUserByUsername(principal.getName());
		List<Grocery> groceries = null;
		if (user != null) {
			groceries = grocServ.findByUserId(user.getId());
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}

		return groceries;
	}

	@PostMapping("groceries")
	public Grocery create(@RequestBody Grocery grocery, Principal principal, HttpServletResponse res) {
		try {
			grocery = grocServ.create(principal.getName(), grocery);
			if (grocery == null) {
				res.setStatus(400);
			} else {
				res.setStatus(201);
			}
		} catch (Exception e) {
			res.setStatus(400);
			grocery = null;
			e.printStackTrace();
		}
		return grocery;
	}

	@PutMapping("groceries/{id}")
	public Grocery update(@PathVariable int id, @RequestBody Grocery grocery, Principal principal, HttpServletResponse res) {
		
		grocery = grocServ.update(principal.getName(), id, grocery);
		if (grocery == null ) {
			res.setStatus(400);
		} else {
			res.setStatus(200);
		}
		
		
		return grocery;
	}
	
	@PostMapping("grocerylist")
	public List<Grocery> createList(@RequestBody List<Grocery> groceries, Principal principal, HttpServletResponse res) {
		for (Grocery grocery : groceries) {
			grocery = grocServ.create(principal.getName(), grocery);
			if (grocery == null) {
				res.setStatus(400);
				return null;
			}
		}
		res.setStatus(201);
		
		
		return groceries;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
