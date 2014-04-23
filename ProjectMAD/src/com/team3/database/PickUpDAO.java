package com.team3.database;

import java.util.ArrayList;

import android.content.Context;

import com.team3.basics.Customer;
import com.team3.basics.Pickup;

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
