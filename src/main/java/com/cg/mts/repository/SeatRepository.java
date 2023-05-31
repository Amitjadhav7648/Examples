package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.pojo.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
