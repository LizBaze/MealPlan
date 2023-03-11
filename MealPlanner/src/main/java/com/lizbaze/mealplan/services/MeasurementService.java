package com.lizbaze.mealplan.services;

import java.util.List;

import com.lizbaze.mealplan.entities.Measurement;

public interface MeasurementService {
	
	public List<Measurement> findAll();
	public Measurement create(Measurement measurement);

}
