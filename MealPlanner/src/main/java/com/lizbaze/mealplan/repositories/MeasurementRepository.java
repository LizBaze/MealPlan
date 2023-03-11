package com.lizbaze.mealplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lizbaze.mealplan.entities.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
