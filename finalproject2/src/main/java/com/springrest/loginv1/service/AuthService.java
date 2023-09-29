package com.springrest.loginv1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springrest.loginv1.model.User;
import com.springrest.loginv1.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	public void signup(User user) {

		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole("USER");
		repository.save(user);

	}

	public boolean signin(String username, String password) {
		Optional<User> userOptional = repository.findByUsername(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			String storedPassword = user.getPassword();
			return encoder.matches(password, storedPassword);
		}
		return false;
	}
}
