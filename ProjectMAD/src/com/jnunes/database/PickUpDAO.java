package com.jnunes.database;

import java.util.ArrayList;

import android.content.Context;

import com.jnunes.basics.Customer;
import com.jnunes.basics.Pickup;

public class PickUpDAO {
	private Database db;

	public PickUpDAO(Context context) {
		db = Database.getInstance(context);
	}

	public void addPickup(Pickup pickup) {
		db.addPickup(pickup);
	}

	public ArrayList<Pickup> getAllPickUps() {
		return db.getAllPickups();
	}

	public Pickup getPickUp(int id) {
		return db.getPickup(id);
	}
	
	public void deletePickup(int id){
		db.deletePickup(id);
	}
}
