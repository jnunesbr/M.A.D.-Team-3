package com.jnunes.basics;



public class Reservation {
	private Customer customer;
	private Date reservationDate;
	private int numberPeople;

	public int getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(int numberPeople) {
		this.numberPeople = numberPeople;
	}

	public Reservation() {

	}

	public Reservation(Customer customer, Date reservationDate,
			int numberPeople) {
		super();
		this.customer = customer;
		this.reservationDate = reservationDate;
		this.numberPeople = numberPeople;
	}

	public Reservation(Date reservationDate, int numberPeople) {
		super();
		this.reservationDate = reservationDate;
		this.numberPeople = numberPeople;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Date getReservationDate(){
		return reservationDate;
	}
}
