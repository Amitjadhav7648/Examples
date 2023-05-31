package com.cg.mts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.mts.exception.TheatreNotFoundException;
import com.cg.mts.pojo.Movie;
import com.cg.mts.pojo.Show;
import com.cg.mts.pojo.Theatre;
import com.cg.mts.repository.MoviesRepository;
import com.cg.mts.repository.ScreenRepository;
import com.cg.mts.repository.TheatreRepository;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatrerepository;
	@Autowired
	ScreenRepository screenRepository;
	@Autowired
	private MoviesRepository moviesrepository;

	@Override
	public List<Theatre> getAllTheatres() throws TheatreNotFoundException {
		List<Theatre> the = theatrerepository.findAll();
		//if (the.size() == 0) throw new TheatreNotFoundException("No theatres found");
		return the;
	}

	@Override
	public Theatre findTheatres(int theatreId) {
		// TODO Auto-generated method stub
		if (theatrerepository.findById(theatreId).isPresent()) {
			return theatrerepository.findById(theatreId).get();
		} else
			return null;
	}

	@Override
	public Theatre addTheatre(Theatre m) throws TheatreNotFoundException {
		if (m != null) {
			if (theatrerepository.existsById(m.getTheatreId())) {
				throw new TheatreNotFoundException("Theatre already exists");
			} else {
				theatrerepository.saveAndFlush(m);
			}
		}
		return m;
	}

	@Override
	public List<Theatre> updateTheatre(Theatre m) {
		// TODO Auto-generated method stub
		theatrerepository.saveAndFlush(m);
		return theatrerepository.findAll();
	}

	@Override
	public List<Theatre> deleteTheatreById(int theatreId) {
		// TODO Auto-generated method stub
		theatrerepository.deleteById(theatreId);
		return theatrerepository.findAll();
	}

	@Override
	public List<Theatre> findTheatresByMovie(Integer movieId) throws TheatreNotFoundException {
		List<Theatre> theatreList=new ArrayList<>();
		Movie movie=moviesrepository.findById(movieId).get();
		Integer showwID=movie.getShow().getShowId();
		List<Theatre> theatres = theatrerepository.findAll();
		for(Theatre theatre:theatres) {
			List<Show> shows =theatre.getShow();
			for(Show show:shows){
				if(show.getShowId()==showwID) {
					theatreList.add(theatre);
				}
			}
		}
		return theatreList;
	}

	/*
	 * @Override public Theatre addTheatre(Theatre t, List<Integer> screens) { //
	 * TODO Auto-generated method stub
	 * //if(theatrerepository.existsById(m.getTheatreId())) throws new Theatre
	 * List<Screen> preScs=new ArrayList<>(); if(screens!=null) { for(int id:
	 * screens) { Screen sc=screenRepository.getOne(id); preScs.add(sc);
	 * screenRepository.saveAndFlush(sc); } } t.setScreens(preScs);
	 * theatrerepository.saveAndFlush(t); return t; }
	 * 
	 * @Override public List<Theatre> updateTheatre(Theatre t, List<Integer>
	 * screenIds) { // TODO Auto-generated method stub return null; }
	 */

}
