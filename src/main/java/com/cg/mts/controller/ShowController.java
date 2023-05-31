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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.pojo.Show;
import com.cg.mts.service.ShowService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shows")
public class ShowController {

	Logger logger = LoggerFactory.getLogger(ShowController.class);
	@Autowired
	private ShowService showService;

	/**
	 * Stores a Show object in the Database.
	 * 
	 * @param show
	 * @param theatreId
	 * @param screenId
	 * @return Show
	 * @throws AccessForbiddenException
	 */
	@PostMapping("/add")
	public ResponseEntity<Show> addShow(@RequestBody Show show, @RequestParam(required = false) Integer theatreId,
			@RequestParam(required = false) Integer screenId)  {

		showService.addShow(show, theatreId, screenId);
		logger.info("-------Show Added Succesfully--------");
		return new ResponseEntity<>(show, HttpStatus.CREATED);
	}

	/**
	 * Removes persisted Show instance from the Database.
	 * 
	 * @param showId
	 * @return Show
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping("/delete/{showId}")
	public ResponseEntity<Show> removeShow(@PathVariable int showId)  {

		ResponseEntity<Show> response = null;
		Show show = showService.viewShow(showId);
		if (show == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			showService.removeShow(showId);
			response = new ResponseEntity<>(show, HttpStatus.OK);
			logger.info("-------Show with ShowId " + showId + " Deleted Successfully---------");
		}
		return response;
	}

	/**
	 * Updates a existing Show record in the database.
	 * 
	 * @param show
	 * @param theatreId
	 * @param screenId
	 * @return Show
	 * @throws AccessForbiddenException
	 */
	@PutMapping("/update")
	public ResponseEntity<Show> updateShow(@RequestBody Show show, @RequestParam(required = false) Integer theatreId,
			@RequestParam(required = false) Integer screenId)  {

		ResponseEntity<Show> response = null;
		if (show == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			showService.updateShow(show, theatreId, screenId);
			response = new ResponseEntity<>(show, HttpStatus.OK);
			logger.info("-------Show Updated Successfully---------");
		}
		return response;
	}

	/**
	 * Returns the record from the database using identifier - showId
	 * 
	 * @param showId
	 * @return Show
	 * @throws AccessForbiddenException
	 * @throws ShowNotFoundException
	 */
	@GetMapping("/view/{showId}")
	public ResponseEntity<Show> viewShow(@PathVariable int showId)
			throws  ShowNotFoundException {

		ResponseEntity<Show> response = null;
		try {
			Show show = showService.viewShow(showId);
			response = new ResponseEntity<>(show, HttpStatus.OK);
			logger.info("-------Show with ShowId " + showId + " Found Successfully---------");
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new ShowNotFoundException("Show with " + showId + " id dosen't exist");
		}
		return response;
	}

	/**
	 * Return's the List of Shows existing from the Database
	 * 
	 * @return List<Show>
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/findall")
	public ResponseEntity<List<Show>> viewShowList() {

		logger.info("-------List Of Shows Fetched Successfully---------");
		return ResponseEntity.ok(showService.viewAllShows());
	}

	/**
	 * 
	 * @param theatreId
	 * @return Show
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/show_theatre/{theatreId}")
	public ResponseEntity<List<Show>> viewShowByTheatreId(@PathVariable int theatreId) {

		logger.info("-------List Of Shows With TheatreId " + theatreId + " Fetched Successfully---------");
		return ResponseEntity.ok(showService.viewShowList(theatreId));
	}

	/**
	 * 
	 * @param date
	 * @return List<Show>
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/date/{date}")
	public ResponseEntity<List<Show>> viewShowByLocalDate(@PathVariable int date) {

		logger.info("-------List Of Shows With Date " + date + " Fetched Successfully---------");
		return ResponseEntity.ok(showService.viewShowList(date));
	}
}
