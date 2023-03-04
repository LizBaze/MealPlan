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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_User_entity() {
		assertNotNull(user);
		assertEquals(user.getUsername(), "admin");
	}
	
	@Test
	void test_UserHasRecipe_mapping() {
		assertTrue(user.getRecipes().size() > 0);
		assertEquals("Macaroni and cheese", user.getRecipes().get(0).getName());
	}
	
	@Test
	void test_user_mealPlan() {
		assertNotNull(user);
		assertTrue(user.getMealPlan().size() > 0);
	}

}
