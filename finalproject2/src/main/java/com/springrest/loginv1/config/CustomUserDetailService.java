package com.springrest.loginv1.config;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springrest.loginv1.model.User;
import com.springrest.loginv1.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
            user.get().getUsername(),
            user.get().getPassword(),
            // Add user roles/authorities here if needed
            Collections.emptyList()
        );
    }
}
