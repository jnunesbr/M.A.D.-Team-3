package com.team3.basics;

public class Reservation {
	private long id;
	private Customer customer;
	private Date reservationDate;
	private int numberPeople;

	public Reservation() {

	}

	public Reservation(Customer customer, Date reservationDate, int numberPeople) {
		super();
		this.customer = customer;
		this.reservationDate = reservationDate;
		this.numberPeople = numberPeople;
	}
	
	public Reservation(long id, Customer customer, Date reservationDate, int numberPeople) {
		super();
		this.id = id;
		this.customer = customer;
		this.reservationDate = reservationDate;
		this.numberPeople = numberPeople;
	}

	public Reservation(Date reservationDate, int numberPeople) {
		super();
		this.reservationDate = reservationDate;
		this.numberPeople = numberPeople;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(int numberPeople) {
		this.numberPeople = numberPeople;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getReservationDate() {
		return reservationDate;
	}
	
	public void setReservationDate(Date date){
		this.reservationDate = date;
	}
}
