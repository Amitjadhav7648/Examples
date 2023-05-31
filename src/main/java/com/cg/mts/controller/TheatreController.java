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
import com.cg.mts.exception.TheatreNotFoundException;
import com.cg.mts.pojo.Movie;
import com.cg.mts.pojo.Theatre;
import com.cg.mts.service.TheatreService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/theatre")
public class TheatreController {
	Logger logger = LoggerFactory.getLogger(TheatreController.class);
	@Autowired
	private TheatreService theatreservice;


	/**
	 * 
	 * @return listOfTheatres
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Theatre>> getAlltheatres() throws  TheatreNotFoundException {

		logger.info("-------Theatre List Fetched---------");
		return ResponseEntity.ok(theatreservice.getAllTheatres());
	}

	/**
	 * 
	 * @param t
	 * @return inserted theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@PostMapping("/insert")
	public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre t)
			throws TheatreNotFoundException {

		logger.info("-------Theatre Added Successfully---------");
		return new ResponseEntity<>(theatreservice.addTheatre(t), HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param t
	 * @return updated theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@PutMapping("/update")
	public List<Theatre> updateTheatre(@RequestBody Theatre t)
			throws  TheatreNotFoundException {

		logger.info("-------Theatre Updated Successfully---------");
		return theatreservice.updateTheatre(t);
	}

	/**
	 * 
	 * @param theatreId
	 * @return theatre by theatreId
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/find/{theatreId}")
	public ResponseEntity<Theatre> findTheatre(@PathVariable int theatreId)
			throws  TheatreNotFoundException {

		logger.info("-------Theatre Found with Theatre id" + theatreId + "---------");
		return ResponseEntity.ok(theatreservice.findTheatres(theatreId));
	}

	/**
	 * 
	 * @param theatreId
	 * @return deleted theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@DeleteMapping("/delete/{theatreId}")
	public List<Theatre> deleteMoviesById(@PathVariable int theatreId)
			throws TheatreNotFoundException {

		logger.info("-------Theatre Deleted with Theatre id" + theatreId + "---------");
		return theatreservice.deleteTheatreById(theatreId);
	}
	
	@GetMapping("/findbyMovie/{movieId}")
	public ResponseEntity<List<Theatre>> findTheatreByMovieId(@PathVariable int movieId)
			throws  TheatreNotFoundException {
		return ResponseEntity.ok(theatreservice.findTheatresByMovie(movieId));
	}
	
}
