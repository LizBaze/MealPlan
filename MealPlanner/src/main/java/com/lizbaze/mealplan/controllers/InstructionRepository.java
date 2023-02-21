package com.lizbaze.mealplan.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lizbaze.mealplan.entities.Instruction;

public interface InstructionRepository extends JpaRepository<Instruction, Integer> {

}
