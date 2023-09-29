package com.springrest.loginv1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvestmentNotFoundException extends Exception {
	
	public InvestmentNotFoundException(String message) {
		super(message);
	}
}
