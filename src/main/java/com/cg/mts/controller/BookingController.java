package com.cg.mts.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cg.mts.pojo.Booking;
import com.cg.mts.pojo.Screen;
import com.cg.mts.exception.AccessForbiddenException;
import com.cg.mts.exception.BookingNotFoundException;
import com.cg.mts.exception.ScreenNotFoundException;
import com.cg.mts.service.IBookingService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/booking")
public class BookingController {

	Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private IBookingService bookingService;
	@Autowired
	LoginController loginController;

	/**
	 * 
	 * @param t
	 * @param customerId
	 * @return booking details
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> addTicketBooking(@RequestBody Booking t,
			@RequestParam(required = false) Integer customerId,@RequestParam(required = false) Integer showId)
			throws AccessForbiddenException, BookingNotFoundException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Login");
		 */
		//logger.info("-------Booking For Customer " + customerId + "Added---------");
		return ResponseEntity.ok(bookingService.addBooking(t, customerId,showId));
	}

	/**
	 * 
	 * @return bookingList
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@GetMapping("/findall")
	public ResponseEntity<List<Booking>> viewAllBookings() throws AccessForbiddenException, BookingNotFoundException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Loggin");
		 */
		logger.info("-------List Of Bookings Fetched Successfully---------");
		return ResponseEntity.ok(bookingService.viewBookingList());
	}

	/**
	 * 
	 * @param t
	 * @return updated booking details
	 * @throws BookingNotFoundException
	 * @throws AccessForbiddenException
	 */
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Booking updateTicketBooking(@RequestBody Booking t)
			throws BookingNotFoundException, AccessForbiddenException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Loggin");
		 */
		logger.info("-------Booking Updated Successfully---------");
		return bookingService.updateBooking(t);
	}

	/**
	 * 
	 * @param bookingId
	 * @return deleted booking details
	 * @throws BookingNotFoundException
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping("ticketbooking/{bookingId}")
	public Booking deleteTicketBookingById(@PathVariable int bookingId)
			throws BookingNotFoundException, AccessForbiddenException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Loggin");
		 */
		logger.info("-------Booking With Booking Id " + bookingId + " Deleted Successfully---------");
		return bookingService.cancelBooking(bookingId);
	}
	
	@GetMapping("/viewbooking/{bookingId}")
	public ResponseEntity<Booking> viewBooking(@PathVariable int bookingId)
			throws BookingNotFoundException {
		ResponseEntity<Booking> response = null;
		try {
			Booking booking = bookingService.viewBooking(bookingId);
			response = new ResponseEntity<>(booking, HttpStatus.OK);
			logger.info("-------Screen Found---------");
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new BookingNotFoundException("Booking dosen't exist");
		}
		return response;
	}
	
	/**
	 * 
	 * @param movieId
	 * @return bookingList by movie id
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@GetMapping("/byMovie/{movieId}")
	public ResponseEntity<List<Booking>> viewMovieByMovieId(@PathVariable int movieId)
			throws AccessForbiddenException, BookingNotFoundException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Loggin");
		 */
		logger.info("-------Bookings With MovieId " + movieId + " Fetched Successfully---------");
		return ResponseEntity.ok(bookingService.showAllBookings(movieId));
	}

	/**
	 * 
	 * @param date
	 * @return bookingList by date
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@GetMapping("/byDate/{date}")
	public ResponseEntity<List<Booking>> viewMovieByLocalDate(
			@RequestParam("bookingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
			throws AccessForbiddenException, BookingNotFoundException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Loggin");
		 */
		logger.info("-------Bookings With Date " + date + " Fetched Successfully---------");
		return ResponseEntity.ok(bookingService.showAllBookings(date));
	}

	/**
	 * 
	 * @param bookingId
	 * @return total cost of booking
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@GetMapping("/cost/{bookingId}")
	public double TotalBookingost(@PathVariable int bookingId)
			throws AccessForbiddenException, BookingNotFoundException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Loggin");
		 */
		logger.info("-------Total Cost Of Booking displayed Successfully---------");
		return bookingService.calculateTotalCost(bookingId);
	}
}
