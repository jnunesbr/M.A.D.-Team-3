package com.jnunes.database;

import java.util.ArrayList;

import android.content.Context;

import com.jnunes.basics.MenuItem;

public class MenuItemDAO {
	private Database db;

	public MenuItemDAO(Context context) {
		db = Database.getInstance(context);
	}

	public void addMenuItem(MenuItem item) {
		db.addMenuItem(item);
	}

	public ArrayList<MenuItem> getAllMenuItems() {
		return db.getAllMenuItems();
	}

	public MenuItem getMenuItem(int id) {
		return db.getMenuItem(id);
	}
}
