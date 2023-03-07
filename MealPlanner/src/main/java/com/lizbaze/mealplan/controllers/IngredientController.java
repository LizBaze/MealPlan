package com.lizbaze.mealplan.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizbaze.mealplan.entities.Ingredient;
import com.lizbaze.mealplan.services.IngredientService;

@RestController
@CrossOrigin({"*", "http://localhost"})
@RequestMapping("api")
public class IngredientController {
	
	@Autowired
	private IngredientService ingServ;
	
	@GetMapping("ingredients")
	public List<Ingredient> findAll(HttpServletResponse res) {
		List<Ingredient> ingredients = ingServ.findAll();
		if (ingredients != null) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}
		return ingredients;
	}
}
