package com.cg.mts.validator;

import org.springframework.stereotype.Component;

@Component
public class InputValidationImpl implements InputValidator {

	@Override
	public boolean nameValidator(String name) {
		return name.length() >= 3;
	}

	public boolean usernameValidator(String username) {
		return username.matches("[A-Za-z]{3,20}$");
	}

	@Override
	public boolean passwordValidator(String password) {
		return password.length() >= 3;
	}

	@Override
	public boolean contactValidator(String contact) {
		return contact.matches("[0-9]{10}");
	}

	@Override
	public boolean emailValidator(String email) {
		return email.matches("^(.+)@(.+)$");
	}

}
