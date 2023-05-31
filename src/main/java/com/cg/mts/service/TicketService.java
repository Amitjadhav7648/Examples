package com.cg.mts.service;

import java.util.List;

import com.cg.mts.exception.TicketNotFoundException;
import com.cg.mts.pojo.Ticket;

public interface TicketService {
	public Ticket addTicket(Ticket ticket,Integer bookingId) throws TicketNotFoundException;

	public Ticket findTicket(int ticketId);

	List<Ticket> viewTicketList() throws TicketNotFoundException;

}
