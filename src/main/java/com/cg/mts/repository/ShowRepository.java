package com.cg.mts.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cg.mts.pojo.Show;

public interface ShowRepository extends JpaRepository<Show, Integer> {
	@Query("select s from Show s where s.theatre.theatreId = :id")
	List<Show> getAllByTheatreId(@Param("id") int id);
}
