package com.jnunes.database;

import java.util.ArrayList;

import android.content.Context;

import com.jnunes.basics.Customer;

public class CustomerDAO {
	private Database db;
	
	public CustomerDAO(Context context){
		db = Database.getInstance(context);
	}
	
	public void addCustomer(Customer customer){
		db.addCustomer(customer);
	}
	
	public ArrayList<Customer> getAllCustomers(){
		return db.getAllCustomers();
	}
	
	public Customer getCustomer(int id){
		return db.getCustomer(id);
	}
}
