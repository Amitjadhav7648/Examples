package com.cg.mts.pojo;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seatId;
	private String seatNumber;
	private String type;
	private double price;

	@Enumerated(EnumType.STRING)
	private SeatStatus status;
	@JsonIgnore
	@ManyToOne
	private Ticket ticket;
	public Seat() {
	}
	public Seat(String seatNumber, String type, double price, SeatStatus status, Ticket tickett) {
		super();
		this.seatNumber = seatNumber;
		this.type = type;
		this.price = price;
		this.status = status;
		this.ticket = tickett;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public SeatStatus getStatus() {
		return status;
	}
	public void setStatus(SeatStatus status) {
		this.status = status;
	}
	public Ticket getTickett() {
		return ticket;
	}
	public void setTickett(Ticket tickett) {
		this.ticket = tickett;
	}

}
