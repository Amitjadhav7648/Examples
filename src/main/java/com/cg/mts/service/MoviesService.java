package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cg.mts.exception.MovieNotFoundException;
import com.cg.mts.pojo.Movie;

public interface MoviesService {

	public Movie addMovie(Movie movie) throws MovieNotFoundException;

	public Movie removeMovie(int movieid) throws MovieNotFoundException;
	
	public Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	public Movie addMovieToShow(Movie movie, Integer showId) throws MovieNotFoundException;

	public Movie viewMovie(int movieid) throws MovieNotFoundException;

	public List<Movie> viewMovieList() throws MovieNotFoundException;

	public List<Movie> viewMovieList(int theatreid);

	public List<Movie> viewMovieList(LocalDate date);
}
