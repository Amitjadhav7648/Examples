package com.cg.mts.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cg.mts.exception.MovieNotFoundException;
import com.cg.mts.pojo.Movie;
import com.cg.mts.pojo.Show;
import com.cg.mts.repoImpl.QueryClass;
import com.cg.mts.repository.MoviesRepository;
import com.cg.mts.repository.ShowRepository;
import com.cg.mts.repository.TheatreRepository;

import org.springframework.util.StringUtils;
import java.io.IOException;
import java.util.Base64;

@Service
public class MoviesServiceImpl implements MoviesService {

	@Autowired
	private MoviesRepository moviesrepository;
	@Autowired
	TheatreRepository theatreRepository;
	@Autowired
	ShowRepository showrepository;
	@Autowired
	QueryClass query;

	@Override
	public Movie addMovie(Movie movie) throws MovieNotFoundException {
		if (movie != null) {
			if (moviesrepository.existsById(movie.getMovieId())) {
				throw new MovieNotFoundException("Movie with this id already exists");
			} else {
				/*
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				if(fileName.contains(".."))
				{
					System.out.println("not a a valid file");
				}
				try {
					movie.setMovieImage(Base64.getEncoder().encodeToString(file.getBytes()));
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				moviesrepository.saveAndFlush(movie);
			}
		}
		return movie;
	}

	@Override
	public Movie removeMovie(int movieid) {
		Movie m = moviesrepository.findById(movieid).get();
		List<Show> shows = showrepository.findAll();
		for (Show show : shows) {
			if (show.getMovie()!=null && show.getMovie().getMovieId() == movieid) {
				show.setMovie(null);
			}
		}
		moviesrepository.delete(m);
		return m;
	}
	
	@Override
	public Movie updateMovie(Movie movie) {
		moviesrepository.saveAndFlush(movie);
		return moviesrepository.getOne(movie.getMovieId());
	}
	
	@Override
	public Movie addMovieToShow(Movie movie,Integer showId) {
		Show show=new Show();
		if (showId != null) {
			show = showrepository.getOne(showId);
			movie.setShow(show);
		}
		moviesrepository.saveAndFlush(movie);
		return moviesrepository.getOne(movie.getMovieId());
	}

	@Override
	public Movie viewMovie(int movieid) {
		return moviesrepository.findById(movieid).get();
	}

	@Override
	public List<Movie> viewMovieList() throws MovieNotFoundException {
		List<Movie> ml = moviesrepository.findAll();
		//if (ml.size() == 0) throw new MovieNotFoundException("Movies dosen't exist");
		return ml;
	}

	@Override
	public List<Movie> viewMovieList(int theatreid) {
		List<Movie> movies = new ArrayList<>();
		List<Show> shows = showrepository.findAll();
		Set<Integer> showIds = new HashSet<>();
		for (Show s : shows) {
			if (s.getTheatre().getTheatreId() == theatreid) {
				showIds.add(s.getShowId());
			}
		}
		for (Integer id : showIds) {
			movies.add(showrepository.getOne(id).getMovie());
		}
		return movies;
	}

	@Override
	public List<Movie> viewMovieList(LocalDate date) {
		List<Movie> mvList = new ArrayList<>();
		for (Movie movie : moviesrepository.findAll()) {
			if (movie.getMovieDate() != null && movie.getMovieDate().isEqual(date)) {
				mvList.add(movie);
			}
		}
		return mvList;
	}


}
