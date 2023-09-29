package com.springrest.loginv1.dto;

import lombok.Data;

@Data
public class SignInRequest {
	
	private String username;
	private String password;
}
