package com.lizbaze.mealplan.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lizbaze.mealplan.entities.User;
import com.lizbaze.mealplan.services.AuthService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
public class AuthController {

	@Autowired
	private AuthService authService;

	@GetMapping("users/{userId}")
	public User getUserById(@PathVariable Integer userId, HttpServletResponse res) {
		User user = authService.getUserById(userId);
		if (user == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return user;
	}
	
}
