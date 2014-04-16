package com.jnunes.database;

import java.util.ArrayList;

import android.content.Context;

import com.jnunes.basics.Reservation;

public class ReservationDAO {
	private Database db;

	public ReservationDAO(Context context) {
		db = Database.getInstance(context);
	}

	public void addReservation(Reservation reservation) {
		db.addReservation(reservation);
	}

	public ArrayList<Reservation> getAllReservations() {
		return db.getAllReservations();
	}

	public Reservation getReservation(int id) {
		return db.getReservation(id);
	}
}
