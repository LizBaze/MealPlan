package com.lizbaze.mealplan.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Recipe recipe;

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
		recipe = em.find(Recipe.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		recipe = null;
	}

	@Test
	void test_Recipe_entity() {
		assertNotNull(recipe);
		assertEquals("Macaroni and cheese", recipe.getName());
	}
	
	@Test
	void test_Recipe_to_Ingredient_mapping() {
		assertTrue(recipe.getIngredients().size() > 0);
		assertEquals(recipe.getIngredients().get(0).getIngredient().getName(), "Macaroni");
	}
	
	@Test
	void test_Recipe_to_Instruction_mapping() {
		assertTrue(recipe.getInstructions().size() > 0);
	}

}
