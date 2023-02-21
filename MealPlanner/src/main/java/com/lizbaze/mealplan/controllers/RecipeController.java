package com.lizbaze.mealplan.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizbaze.mealplan.entities.Recipe;
import com.lizbaze.mealplan.services.RecipeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class RecipeController {
	
	@Autowired
	private RecipeService recipeServ;
	
	
	@GetMapping("recipes")
	public List<Recipe> findAll(HttpServletResponse res) {
		
		
		return recipeServ.findAll();
	}

}
