package com.jnunes.projectmad;

import java.util.ArrayList;

public class Reservation {
	private Customer customer;
	private ArrayList<String> items;
	private Date reservationDate;
	private int numberPeople;
	
	public int getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(int numberPeople) {
		this.numberPeople = numberPeople;
	}

	public Reservation(){
		
	}

	public Reservation(Customer customer, ArrayList<String> items, Date reservationDate, int numberPeople) {
		super();
		this.customer = customer;
		this.items = items;
		this.reservationDate = reservationDate;
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

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	public void addItem(String item){
		items.add(item);
	}
	
	public void removeItem(String item){
		items.remove(item);
	}
	
}
