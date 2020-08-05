package com.spring.demo.security.exception;

import java.util.Set;

public class UserUnSupportedFieldPatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserUnSupportedFieldPatchException(Set<String> keys) {
		super("Field " + keys.toString() + " update is not allow.");
	}

}
