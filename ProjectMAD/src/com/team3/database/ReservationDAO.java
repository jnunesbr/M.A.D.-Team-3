package com.team3.database;

import java.util.ArrayList;

import android.content.Context;

import com.team3.basics.Reservation;

public class ReservationDAO {
	private Database db;

	public ReservationDAO(Context context) {
		db = Database.getInstance(context);
	}

	public long addReservation(Reservation reservation) {
		return db.addReservation(reservation);
	}

	public ArrayList<Reservation> getAllReservations() {
		return db.getAllReservations();
	}

	public Reservation getReservation(long id) {
		return db.getReservation(id);
	}
	
	public void deleteReservation(long id){
		db.deleteReservation(id);
	}
	
	public void editReservation(Reservation reservation){
		db.editReservation(reservation);
	}
}
