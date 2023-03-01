package com.lizbaze.mealplan.controllers;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("recipes")
	public Recipe create(@RequestBody Recipe recipe, Principal principal, HttpServletResponse res) {
		recipe = recipeServ.create(recipe, principal.getName());
		
		if (recipe != null ) {
			res.setStatus(201);
		} else {
			res.setStatus(400);
		}
		return recipe;
	}

}
