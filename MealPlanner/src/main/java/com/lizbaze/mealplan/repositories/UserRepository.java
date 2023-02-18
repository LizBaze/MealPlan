package com.lizbaze.mealplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lizbaze.mealplan.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);

}
