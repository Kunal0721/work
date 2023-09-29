package com.springrest.loginv1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/all")
	public String hello() {
		return "Hello world";
	}
	
	@GetMapping("/")
	public String index() {
		return "welcome to index page";
	}
	
	@GetMapping("/allow")
	public String allow() {
		return "Everyone is allowed in this page";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "Permitted";
	}
}
