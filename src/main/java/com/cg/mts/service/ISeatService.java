package com.cg.mts.service;

import java.util.List;

import com.cg.mts.exception.SeatNotFoundException;
import com.cg.mts.pojo.Seat;

public interface ISeatService {

	public Seat addSeat(Seat seat) throws SeatNotFoundException;

	public List<Seat> viewSeatList() throws SeatNotFoundException;

	public Seat updateSeat(Seat seat);

	public Seat bookSeat(Seat seat);

	public Seat cancelSeatBooking(Seat seat);

	public Seat blockSeat(Seat seat); // not available for any booking
}
