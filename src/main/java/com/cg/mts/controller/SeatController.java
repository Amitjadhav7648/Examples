package com.cg.mts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.exception.AccessForbiddenException;
import com.cg.mts.exception.SeatNotFoundException;
import com.cg.mts.pojo.Seat;
import com.cg.mts.service.ISeatService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/seats")
public class SeatController {

	Logger logger = LoggerFactory.getLogger(SeatController.class);
	@Autowired
	private ISeatService seatService;
	@Autowired
	LoginController loginController;

	/**
	 * 
	 * @param seat
	 * @return seatDetails
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PostMapping("/add")
	public ResponseEntity<Seat> addASeat(@RequestBody Seat seat)
			throws AccessForbiddenException, SeatNotFoundException {
		
		seat = seatService.addSeat(seat);
		logger.info("-------Seat Added Successfully---------");
		return new ResponseEntity<>(seat, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @return listOfSeats
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@GetMapping("/findall")
	public ResponseEntity<List<Seat>> viewSeatList() throws AccessForbiddenException, SeatNotFoundException {
		
		logger.info("-------List of Seats Fetched Successfully---------");
		return ResponseEntity.ok(seatService.viewSeatList());
	}

	/**
	 * 
	 * @param seat
	 * @return updated seat
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PutMapping("/update")
	public ResponseEntity<Seat> updateSeat(@RequestBody Seat seat)
			throws AccessForbiddenException, SeatNotFoundException {
	
		ResponseEntity<Seat> response = null;
		if (seat == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			seat = seatService.updateSeat(seat);
			response = new ResponseEntity<>(seat, HttpStatus.OK);
			logger.info("-------Seat Updated Successfully---------");
		}
		return response;
	}

	/**
	 * 
	 * @param seat
	 * @return booked seat
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PutMapping("/book")
	public ResponseEntity<Seat> BookASeat(@RequestBody Seat seat)
			throws AccessForbiddenException, SeatNotFoundException {
		
		seat = seatService.bookSeat(seat);
		logger.info("-------Seat booking Successfull---------");
		return new ResponseEntity<>(seat, HttpStatus.OK);
	}

	/**
	 * 
	 * @param seat
	 * @return cancelled seat
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PutMapping("/cancel")
	public ResponseEntity<Seat> CancelASeat(@RequestBody Seat seat)
			throws AccessForbiddenException, SeatNotFoundException {
		
		seat = seatService.cancelSeatBooking(seat);
		logger.info("-------Seat Cancellation Successfull---------");
		return new ResponseEntity<>(seat, HttpStatus.OK);
	}

	/**
	 * 
	 * @param seat
	 * @return blocked seat
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PutMapping("/block")
	public ResponseEntity<Seat> BloclASeat(@RequestBody Seat seat)
			throws AccessForbiddenException, SeatNotFoundException {
		
		seat = seatService.blockSeat(seat);
		logger.info("-------Seat blocking Successfull---------");
		return new ResponseEntity<>(seat, HttpStatus.OK);

	}
}
