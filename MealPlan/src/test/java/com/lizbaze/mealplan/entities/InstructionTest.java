package com.lizbaze.mealplan.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InstructionTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Instruction instruction;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("MealPlan");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		instruction = em.find(Instruction.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		instruction = null;
	}

	@Test
	void test_Instruction_entity() {
		assertNotNull(instruction);
		assertEquals("1. Boil Macaroni. 2. Add cheese.", instruction.getDescription());
	}

}
