package com.spring.demo.security.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Long id) {
		super("User id not found : " + id);
	}

}