package com.cg.mts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.exception.UserCreationError;
import com.cg.mts.pojo.User;
import com.cg.mts.repository.IAdminRepository;
import com.cg.mts.validator.InputValidator;

@Service
public class IAdminServiceImpl implements IAdminService {

	@Autowired
	IAdminRepository adminRepo;

	@Autowired
	InputValidator validate;

	@Override
	public void registerAdmin(String username, String password) throws Exception {
		if (!validate.usernameValidator(username))
			throw new UserCreationError("Check Username !!!!");
		if (!validate.passwordValidator(password))
			throw new UserCreationError("Cannot register this admin with this password");
		User u = new User(username, password, "ADMIN", null);
		adminRepo.saveAndFlush(u);
	}

}
