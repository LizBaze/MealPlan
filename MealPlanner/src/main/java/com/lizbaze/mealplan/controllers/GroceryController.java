package com.lizbaze.mealplan.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	
	@GetMapping("users/groceries")
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
	
}
