package com.lizbaze.mealplan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizbaze.mealplan.entities.Measurement;
import com.lizbaze.mealplan.repositories.MeasurementRepository;

@Service
public class MeasurementServiceImpl implements MeasurementService {

	@Autowired
	private MeasurementRepository measurementRepo;
	
	@Override
	public List<Measurement> findAll() {
		return measurementRepo.findAll();
	}

	@Override
	public Measurement create(Measurement measurement) {
		measurementRepo.saveAndFlush(measurement);
		return measurement;
	}

}
