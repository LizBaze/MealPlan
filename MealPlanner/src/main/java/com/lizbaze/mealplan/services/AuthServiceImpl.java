package com.lizbaze.mealplan.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lizbaze.mealplan.entities.User;
import com.lizbaze.mealplan.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("user");
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User getUserById(int id) {
		Optional<User> userOpt = userRepo.findById(id);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
//		return userOpt.isPresent() ? userOpt.get() : null;
	}
	
	

}
