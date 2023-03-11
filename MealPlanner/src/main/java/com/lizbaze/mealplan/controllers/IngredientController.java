package com.lizbaze.mealplan.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizbaze.mealplan.entities.Ingredient;
import com.lizbaze.mealplan.entities.Measurement;
import com.lizbaze.mealplan.services.IngredientService;
import com.lizbaze.mealplan.services.MeasurementService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class IngredientController {
	
	@Autowired
	private IngredientService ingServ;
	@Autowired
	private MeasurementService measurementService;

	
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
	
	@SuppressWarnings("rawtypes")
	@GetMapping("measurements")
	public List<List> getIngredientsAndMeasurements(HttpServletResponse res) {
		
		List<Ingredient> ingredients = ingServ.findAll();
		Collections.sort(ingredients, new Comparator<Ingredient>() {

			@Override
			public int compare(Ingredient o1, Ingredient o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		
		List<Measurement> measurements = measurementService.findAll();
		Collections.sort(measurements, new Comparator<Measurement>() {

			@Override
			public int compare(Measurement o1, Measurement o2) {
				return o1.getDescription().compareTo(o2.getDescription());
			}
			
		});
		
		List<List> results = new ArrayList<>();
		results.add(ingredients);
		results.add(measurements);
		return results;
	}
	
	
}
