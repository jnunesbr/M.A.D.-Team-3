package com.jnunes.database;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jnunes.basics.Customer;
import com.jnunes.basics.Date;
import com.jnunes.basics.MenuItem;
import com.jnunes.basics.Pickup;
import com.jnunes.basics.Reservation;

public class Database extends SQLiteOpenHelper {
	private static Database database;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "restaurantManager";

	// Database table names
	private static final String TABLE_CUSTOMERS = "customers";
	private static final String TABLE_RESERVATIONS = "reservations";
	private static final String TABLE_PICKUPS = "pickups";
	private static final String TABLE_MENU_ITEMS = "menu_items";
	private static final String TABLE_PICKUPS_ITEMS = "pickups_items";

	// Customer Table Columns names
	private static final String KEY_CUSTOMER_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PH_NO = "phone_number";
	private static final String KEY_ADDRESS = "address";

	// Reservations Table Columns names
	private static final String KEY_RESERVATION_ID = "id";
	private static final String KEY_CUSTOMER_RESERVATION = "customer";
	private static final String KEY_DATE_TIME = "datetime";
	private static final String KEY_NUMBER_OF_PEOPLE = "numberPeople";

	// PickUps Table Columns names
	private static final String KEY_PICKUP_ID = "id";
	private static final String KEY_CUSTOMER_PICKUP = "customer";
	private static final String KEY_DATE_TIME_PICKUP = "datetime";

	// Menu_Items Table Columns names
	private static final String KEY_ITEM_ID = "id";
	private static final String KEY_ITEM_NAME = "name";
	private static final String KEY_ITEM_PRICE = "price";

	// PickUps_Items Table Columns names
	private static final String KEY_PICKUP_ITEM_ID = "id";
	private static final String KEY_PICKUP = "pickupId";
	private static final String KEY_ITEM = "itemId";
	private static final String KEY_QUANTITY = "quantity";

	private Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static Database getInstance(Context context) {
		if (database == null) {
			database = new Database(context);
		}
		return database;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
				+ KEY_CUSTOMER_ID + " INTEGER PRIMARY KEY," + KEY_NAME
				+ " TEXT," + KEY_PH_NO + " TEXT," + KEY_ADDRESS + " TEXT" + ")";

		String CREATE_RESERVATIONS_TABLE = "CREATE TABLE " + TABLE_RESERVATIONS
				+ "(" + KEY_RESERVATION_ID + " INTEGER PRIMARY KEY,"
				+ KEY_CUSTOMER_RESERVATION + " INTEGER," + KEY_DATE_TIME
				+ " TEXT," + KEY_NUMBER_OF_PEOPLE + " INTEGER, "
				+ "FOREIGN KEY(" + KEY_CUSTOMER_RESERVATION + ") REFERENCES "
				+ TABLE_CUSTOMERS + "(" + KEY_CUSTOMER_ID + "))";

		String CREATE_PICKUPS_TABLE = "CREATE TABLE " + TABLE_PICKUPS + "("
				+ KEY_PICKUP_ID + " INTEGER PRIMARY KEY," + KEY_CUSTOMER_PICKUP
				+ " INTEGER," + KEY_DATE_TIME_PICKUP + " TEXT,"
				+ "FOREIGN KEY(" + KEY_CUSTOMER_PICKUP + ") REFERENCES "
				+ TABLE_CUSTOMERS + "(" + KEY_CUSTOMER_ID + "))";

		String CREATE_MENU_ITEMS_TABLE = "CREATE TABLE " + TABLE_MENU_ITEMS
				+ "(" + KEY_ITEM_ID + " INTEGER PRIMARY KEY," + KEY_ITEM_NAME
				+ " TEXT," + KEY_ITEM_PRICE + " REAL" + ")";

		String CREATE_PICKUPS_ITEMS_TABLE = "CREATE TABLE "
				+ TABLE_PICKUPS_ITEMS + "(" + KEY_PICKUP_ITEM_ID
				+ " INTEGER PRIMARY KEY," + KEY_PICKUP + " INTEGER," + KEY_ITEM
				+ " INTEGER," + "FOREIGN KEY(" + KEY_PICKUP + ") REFERENCES "
				+ TABLE_PICKUPS + "(" + KEY_PICKUP_ID + "))" + "FOREIGN KEY("
				+ KEY_ITEM + ") REFERENCES " + TABLE_MENU_ITEMS + "("
				+ KEY_ITEM_ID + "))";

		db.execSQL(CREATE_CUSTOMERS_TABLE);
		db.execSQL(CREATE_RESERVATIONS_TABLE);
		db.execSQL(CREATE_PICKUPS_TABLE);
		db.execSQL(CREATE_MENU_ITEMS_TABLE);
		db.execSQL(CREATE_PICKUPS_ITEMS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICKUPS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_ITEMS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICKUPS_ITEMS);

		// Create tables again
		onCreate(db);
	}

	// Customers Methods
	// ----------------------------------------------------------------------------------------------

	public void addCustomer(Customer customer) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, customer.getName());
		values.put(KEY_PH_NO, customer.getPhone());
		values.put(KEY_ADDRESS, customer.getAddress());

		// Inserting Row
		db.insert(TABLE_CUSTOMERS, null, values);
		db.close(); // Closing database connection
	}

	public Customer getCustomer(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CUSTOMERS, new String[] {
				KEY_CUSTOMER_ID, KEY_NAME, KEY_PH_NO, KEY_ADDRESS },
				KEY_CUSTOMER_ID + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Customer contact = new Customer(cursor.getString(2),
				cursor.getString(1), cursor.getString(3));
		db.close();
		return contact;
	}

	public void deleteCustomer(int id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CUSTOMERS, KEY_CUSTOMER_ID+"="+id, null);
		db.close();
	}
	
	public ArrayList<Customer> getAllCustomers() {
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "SELECT * FROM " + TABLE_CUSTOMERS;
		Cursor cursor = db.rawQuery(query, null);

		ArrayList<Customer> customers = new ArrayList<Customer>();
		while (cursor.moveToNext()) {
			Customer customer = new Customer(cursor.getString(2),
					cursor.getString(1), cursor.getString(3));
			customers.add(customer);
		}
		db.close();
		return customers;
	}

	// Reservation Methods
	// ------------------------------------------------------------------------
	public void addReservation(Reservation reservation) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CUSTOMER_RESERVATION, reservation.getCustomer().getId());
		values.put(KEY_NUMBER_OF_PEOPLE, reservation.getNumberPeople());
		values.put(KEY_DATE_TIME, reservation.getReservationDate()
				.getDateTimeString());

		// Inserting Row
		db.insert(TABLE_RESERVATIONS, null, values);
		db.close(); // Closing database connection
	}

	public Reservation getReservation(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_RESERVATIONS,
				new String[] { KEY_CUSTOMER_RESERVATION, KEY_DATE_TIME,
						KEY_NUMBER_OF_PEOPLE }, KEY_RESERVATION_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Customer customer = getCustomer(Integer.parseInt(cursor.getString(0)));
		Reservation reservation = new Reservation(customer, new Date(
				cursor.getString(1)), Integer.parseInt(cursor.getString(2)));
		db.close();
		return reservation;
	}
	
	public void deleteReservation(int id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_RESERVATIONS, KEY_RESERVATION_ID+"="+id, null);
		db.close();
	}

	public ArrayList<Reservation> getAllReservations() {
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "SELECT " + KEY_RESERVATION_ID + " FROM "
				+ TABLE_RESERVATIONS;
		Cursor cursor = db.rawQuery(query, null);

		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		while (cursor.moveToNext()) {
			Reservation reservation = getReservation(Integer.parseInt(cursor
					.getString(0)));
			reservations.add(reservation);
		}
		db.close();
		return reservations;
	}

	// Menu Items Methods
	// ------------------------------------------------------------------------
	public void addMenuItem(MenuItem menuItem) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ITEM_NAME, menuItem.getName());
		values.put(KEY_ITEM_PRICE, menuItem.getPrice());

		// Inserting Row
		db.insert(TABLE_MENU_ITEMS, null, values);
		db.close();
	}

	public MenuItem getMenuItem(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_MENU_ITEMS, new String[] {
				KEY_ITEM_NAME, KEY_ITEM_PRICE }, KEY_ITEM_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		MenuItem menuItem = new MenuItem(cursor.getString(0),
				Double.parseDouble(cursor.getString(1)));
		db.close();
		return menuItem;
	}
	
	public void deleteMenuItem(int id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MENU_ITEMS, KEY_ITEM_ID+"="+id, null);
		db.close();
	}
	
	public ArrayList<MenuItem> getAllMenuItems() {
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "SELECT * FROM " + TABLE_MENU_ITEMS;
		Cursor cursor = db.rawQuery(query, null);

		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		while (cursor.moveToNext()) {
			MenuItem menuItem = new MenuItem(cursor.getString(1),
					Double.parseDouble(cursor.getString(2)));
			menuItems.add(menuItem);
		}
		db.close();
		return menuItems;
	}

	// PickUps Methods
	// ------------------------------------------------------------------------
	public void addPickup(Pickup pickup) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CUSTOMER_PICKUP, pickup.getCustomer().getId());
		values.put(KEY_DATE_TIME_PICKUP, pickup.getPickupDate()
				.getDateTimeString());

		// Inserting Row
		db.insert(TABLE_PICKUPS, null, values);
		db.close(); // Closing database connection
	}

	public Pickup getPickup(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PICKUPS, new String[] {
				KEY_CUSTOMER_PICKUP, KEY_DATE_TIME_PICKUP }, KEY_PICKUP_ID
				+ "=?", new String[] { String.valueOf(id) }, null, null, null,
				null);
		if (cursor != null)
			cursor.moveToFirst();

		int customerId = Integer.parseInt(cursor.getString(0));
		Date dateTime = new Date(cursor.getString(1));

		Customer customer = getCustomer(customerId);

		Pickup pickup = new Pickup(customer, dateTime);
		pickup.setItems(getAllPickupItems(id));
		db.close();
		return pickup;
	}
	
	public void deletePickup(int id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PICKUPS, KEY_PICKUP_ID+"="+id, null);
		db.close();
	}

	public HashMap<MenuItem, Integer> getAllPickupItems(int pickupId) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PICKUPS_ITEMS, new String[] { KEY_ITEM,
				KEY_QUANTITY }, KEY_PICKUP + "=?",
				new String[] { String.valueOf(pickupId) }, null, null, null,
				null);

		HashMap<MenuItem, Integer> items = new HashMap<MenuItem, Integer>();

		while (cursor.moveToNext()) {
			MenuItem item = getMenuItem(Integer.parseInt(cursor.getString(0)));
			items.put(item, Integer.parseInt(cursor.getString(1)));
		}
		db.close();
		return items;
	}

	public ArrayList<Pickup> getAllPickups() {
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "SELECT " + KEY_PICKUP_ID + " FROM " + TABLE_PICKUPS;
		Cursor cursor = db.rawQuery(query, null);

		ArrayList<Pickup> pickups = new ArrayList<Pickup>();
		while (cursor.moveToNext()) {
			Pickup pickup = getPickup(Integer.parseInt(cursor.getString(0)));
			pickups.add(pickup);
		}
		db.close();
		return pickups;
	}
}
