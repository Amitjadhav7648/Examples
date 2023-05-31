package com.cg.mts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.exception.AccessForbiddenException;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.exception.MovieNotFoundException;
import com.cg.mts.pojo.Customer;
import com.cg.mts.pojo.Movie;
import com.cg.mts.service.CustomerService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	@Autowired
	LoginController loginController;

	/**
	 * Checks the login status if login status is true then adds customer details.
	 * 
	 * @param customer
	 * @return response
	 * @throws CustomerNotFoundException
	 * @throws AccessForbiddenException
	 */
	@PostMapping("/add")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer)
			throws CustomerNotFoundException, AccessForbiddenException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Login"); if
		 * (!loginController.getRole().equalsIgnoreCase("ADMIN")) throw new
		 * AccessForbiddenException("You must be Admin to access this...");
		 */
		ResponseEntity<String> response = null;
		if (customer == null) {
			logger.error("-------------Please Enter Customer Values--------");
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			customer = customerService.addCustomer(customer);
			response = new ResponseEntity<>("Customer is Added", HttpStatus.CREATED);
			logger.info("----------------Customer Created------------------");
		}
		return response;
	}

	/**
	 * checks whether login status is true removes the customer using id
	 * 
	 * @param customerId
	 * @return removeCustomer
	 * @throws CustomerNotFoundException
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping("/delete/{customerId}")
	public Customer removeCustomer(@PathVariable int customerId)
			throws CustomerNotFoundException, AccessForbiddenException {
		/*
		 * if (!loginController.loginStatus()) { throw new
		 * AccessForbiddenException("Not Logged In/Invalid Login"); } if
		 * (!loginController.getRole().equalsIgnoreCase("ADMIN")) { throw new
		 * AccessForbiddenException("You must be Admin to access this..."); }
		 */
		logger.info("----------------Customer Deleted Successfully--------------");
		return customerService.removeCustomer(customerId);
	}

	/**
	 * checks weather login status is true updates the customer details.
	 * 
	 * @param customer
	 * @return response
	 * @throws CustomerNotFoundException
	 * @throws AccessForbiddenException
	 */
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer)
			throws CustomerNotFoundException, AccessForbiddenException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Login");
		 */
		ResponseEntity<Customer> response = null;
		if (customer == null) {
			logger.error("Enter Customer Details");
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			customer = customerService.updateCustomer(customer);
			response = new ResponseEntity<>(customer, HttpStatus.OK);
			logger.info("--------------Customer Updated Successfully-----------------");
		}
		return response;
	}

	/**
	 * checks whether the login status is true. also verifies whether the role is
	 * admin or not displays the list of customers
	 * 
	 * @return CustomerList
	 * @throws AccessForbiddenException
	 */

	@GetMapping("/findall")
	public List<Customer> viewCustomerList() throws AccessForbiddenException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Login"); if
		 * (!loginController.getRole().equalsIgnoreCase("ADMIN")) throw new
		 * AccessForbiddenException("You must be Admin to access this...");
		 */
		logger.info("---------------Customer List-----------------");
		return customerService.viewCustomerList();
	}
	
	
	@GetMapping("/view/{customerId}")
	public ResponseEntity<Customer> viewACustomer(@PathVariable int customerId) throws CustomerNotFoundException {

		ResponseEntity<Customer> response = null;
		try {
			Customer movie = customerService.viewCustomer(customerId);
			response = new ResponseEntity<>(movie, HttpStatus.OK);
			logger.info("-------Movie With Movie id " + customerId + " Found---------");
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new CustomerNotFoundException("Customer with " + customerId + " id dosen't exist");
		}
		return response;
		// return ResponseEntity.ok(moviesService.viewMovie(movieId));
	}
}
