package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.exception.SeatNotFoundException;
import com.cg.mts.pojo.Seat;
import com.cg.mts.pojo.SeatStatus;
import com.cg.mts.repository.SeatRepository;

@Service
public class SeatServiceImpl implements ISeatService {
	@Autowired
	private SeatRepository seatRepository;

	@Override
	public Seat addSeat(Seat seat) throws SeatNotFoundException {
		if (seat != null) {
			if (seatRepository.existsById(seat.getSeatId())) {
				throw new SeatNotFoundException("Seat with this id already exists");
			} else {
				seatRepository.saveAndFlush(seat);
			}
		}
		return seatRepository.getOne(seat.getSeatId());
	}

	@Override
	public List<Seat> viewSeatList() throws SeatNotFoundException {
		List<Seat> li = seatRepository.findAll();
		/*
		 * if (li.size() == 0) throw new SeatNotFoundException("No seats found");
		 */
		return li;
	}

	@Override
	public Seat updateSeat(Seat seat) {
		// TODO Auto-generated method stub
		return seatRepository.saveAndFlush(seat);
	}

	@Override
	public Seat bookSeat(Seat seat) {
		seat.setStatus(SeatStatus.BOOKED);
		return seatRepository.saveAndFlush(seat);
	}

	@Override
	public Seat cancelSeatBooking(Seat seat) {
		seat.setStatus(SeatStatus.CANCELLED);
		return seatRepository.saveAndFlush(seat);
	}

	@Override
	public Seat blockSeat(Seat seat) {
		seat.setStatus(SeatStatus.BLOCKED);
		return seatRepository.saveAndFlush(seat);
	}

}
