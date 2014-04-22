package com.jnunes.basics;

public class Customer {
	private String name, phone, address;
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer(String phone, String name, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Customer(long id, String phone, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
