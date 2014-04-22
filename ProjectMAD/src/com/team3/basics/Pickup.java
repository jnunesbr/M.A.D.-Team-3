package com.jnunes.basics;

import java.util.ArrayList;
import java.util.HashMap;

public class Pickup {
	private HashMap<MenuItem, Integer> items;
	private Customer customer;
	private Date pickupDate;

	public Pickup() {

	}

	public Pickup(Customer customer, Date date) {
		this.customer = customer;
		this.pickupDate = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public HashMap<MenuItem, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<MenuItem, Integer> items) {
		this.items = items;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public void addItem(MenuItem item, int quantity) {
		items.put(item, quantity);
	}

	public void remove(MenuItem item, int quantity) {
		int total = items.get(item);
		items.put(item, total - quantity);
	}
}
