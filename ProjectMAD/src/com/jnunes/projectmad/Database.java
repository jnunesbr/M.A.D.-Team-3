package com.jnunes.projectmad;

import java.sql.Date;
import java.util.ArrayList;

public class Database {
	private ArrayList<Reservation> reservations;
	private ArrayList<Customer> customers;
	private static Database instance = null;

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	private Database() {
		populate();
	}

	public static Database getInstance() {
		if (instance == null)
			instance = new Database();
		return instance;
	}

	private void populate() {
		ArrayList<String> items = new ArrayList<String>();
		reservations = new ArrayList<Reservation>();
		customers = new ArrayList<Customer>();

		customers.add(new Customer("5645461", "Mario"));
		customers.add(new Customer("5645461", "Jose"));
		customers.add(new Customer("5645461", "John"));
		customers.add(new Customer("5645461", "Anne"));
		customers.add(new Customer("5645461", "Peter"));
		customers.add(new Customer("5645461", "Noah"));
		customers.add(new Customer("5645461", "Julia"));

//		items.add("item 1");
//		reservations.add(new Reservation(customers.get(0), items, new Date(10,
//				10, 10)));
//		items.add("item 2");
//		reservations.add(new Reservation(customers.get(1), items, new Date(9,
//				10, 10)));
//		items.add("item 3");
//		reservations.add(new Reservation(customers.get(2), items, new Date(7,
//				10, 10)));
//		items.add("item 4");
//		reservations.add(new Reservation(customers.get(3), items, new Date(1,
//				10, 10)));
//		items.add("item 2");
//		reservations.add(new Reservation(customers.get(4), items, new Date(9,
//				10, 10)));
//		items.add("item 3");
//		reservations.add(new Reservation(customers.get(5), items, new Date(7,
//				10, 10)));
//		items.add("item 4");
//		reservations.add(new Reservation(customers.get(6), items, new Date(1,
//				10, 10)));
	}
	
	public void addReservation(Reservation reservation){
		reservations.add(reservation);
	}
}
