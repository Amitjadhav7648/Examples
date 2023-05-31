package com.cg.mts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.exception.AccessForbiddenException;
import com.cg.mts.exception.UserCreationError;
import com.cg.mts.pojo.Customer;
import com.cg.mts.pojo.User;
import com.cg.mts.repository.CustomerRepository;
import com.cg.mts.service.IUserService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	IUserService userService;
	@Autowired
	CustomerRepository customerRepository;

	/**
	 * 
	 * @param user
	 * @return user
	 * @throws AccessForbiddenException
	 * @throws CustomerNotFoundException
	 * @throws UserCreationError
	 */
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user)
			throws AccessForbiddenException, CustomerNotFoundException, UserCreationError {
		// if(!logCon.loginStatus() & logCon.getRole().equalsIgnoreCase("ADMIN"))
		/*
		 * if (user.getRole().equalsIgnoreCase("ADMIN") &
		 * !loginController.loginStatus()) throw new
		 * AccessForbiddenException("You must be Admin to access this..."); else
		 */
		if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
			Customer customer = new Customer(user.getUsername(), null, null, null, user.getPassword());
			logger.info("-----------------Customer Added---------------------");
			customerRepository.saveAndFlush(customer);
			user.setCustomer(customer);
		}
		logger.info("-----------------User Added---------------------");
		return userService.addUser(user);
	}

	/**
	 * 
	 * @param user
	 * @return remove user
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping("/removeuser")
	public User removeUser(@RequestBody User user) throws AccessForbiddenException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Loggin"); if
		 * (!loginController.getRole().equalsIgnoreCase("ADMIN")) throw new
		 * AccessForbiddenException("You must be Admin to access this...");
		 */
		logger.info("--------------------User Deleted------------------");
		return userService.removeUser(user);
	}
}