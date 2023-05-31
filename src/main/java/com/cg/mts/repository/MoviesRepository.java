package com.cg.mts.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.pojo.Movie;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {
	// @Query("select m from Movie m join m.show s where s.theatre.theatreId = :id")
	// List<Movie> getAllByTheatreId(@Param("id") int id);

	List<Movie> getAllBymovieDate(LocalDate date);

}
