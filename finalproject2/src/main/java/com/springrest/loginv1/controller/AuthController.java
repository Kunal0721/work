package com.springrest.loginv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.loginv1.dto.SignInRequest;
import com.springrest.loginv1.model.User;
import com.springrest.loginv1.service.AuthService;

@RestController
public class AuthController {

	
	 @Autowired
	    private AuthService authService;

	    @PostMapping("/signup")
	    public ResponseEntity<String> signup(@RequestBody User user) {
	        try {
	            authService.signup(user);
	            return ResponseEntity.ok("User registered successfully.");
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }

	    @PostMapping("/signin")
	    public ResponseEntity<String> signin(@RequestBody SignInRequest signInRequest) {
	        String username = signInRequest.getUsername();
	        String password = signInRequest.getPassword();

	        if (authService.signin(username, password)) {
	            return ResponseEntity.ok("User signed in successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
	        }
	        }
}
