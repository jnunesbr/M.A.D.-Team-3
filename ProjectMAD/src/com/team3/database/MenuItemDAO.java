package com.team3.database;

import java.util.ArrayList;

import android.content.Context;

import com.team3.basics.MenuItem;

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
	
	public void deletMenuItem(int id){
		db.deleteMenuItem(id);
	}
public void deletMenuItem(String Mname){
        db.deleteMenuItem(Mname);
    }
    
    public void UMenuItem(MenuItem newitem){
        db.UpdateMenuItem(newitem);
    }
}
