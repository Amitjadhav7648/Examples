package com.cg.mts.pojo;

import java.time.LocalDate;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	@OneToOne(mappedBy = "booking")
	private Show show;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;
	private String transactionMode;
	private String transactionStatus;
	private double totalCost;
	@JsonIgnore
	@ManyToOne
	private Customer customer;
	@JsonIgnore
	@OneToOne(mappedBy = "booking")
	private Ticket ticket;

	public Booking() {

	}

	public Booking(Show show, LocalDate bookingDate, String transactionMode, String transactionStatus, double totalCost,
			Customer customer, Ticket ticket) {
		super();
		this.show = show;
		this.bookingDate = bookingDate;
		this.transactionMode = transactionMode;
		this.transactionStatus = transactionStatus;
		this.totalCost = totalCost;
		this.customer = customer;
		this.ticket = ticket;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


}