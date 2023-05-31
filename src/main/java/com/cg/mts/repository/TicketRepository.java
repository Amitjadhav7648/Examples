package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mts.pojo.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
