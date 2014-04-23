package com.team3.database;

import java.util.ArrayList;

import android.content.Context;

import com.team3.basics.Customer;

public class CustomerDAO{
	private Database db;
	
	public CustomerDAO(Context context){
		db = Database.getInstance(context);
	}
	
	public long addCustomer(Customer customer){
		return db.addCustomer(customer);
	}
	
	public ArrayList<Customer> getAllCustomers(){
		return db.getAllCustomers();
	}
	
	public Customer getCustomer(long id){
		return db.getCustomer(id);
	}
	
	public void deleteCustomer(long id){
		db.deleteCustomer(id);
	}
	
	public void editCustomer(Customer customer){
		db.editCustomer(customer);
	}
}
