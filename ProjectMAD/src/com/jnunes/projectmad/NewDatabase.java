package com.jnunes.projectmad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewDatabase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "restaurantManager";
 
    // Contacts table name
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String TABLE_RESERVATIONS = "reservations";
    
    // Contacts Table Columns names
    private static final String KEY_CUSTOMER_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_ADDRESS = "address";
    
    // Reservations Table Columns names
    private static final String KEY_RESERVATION_ID = "id";
    private static final String KEY_CUSTOMER = "customer";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "address";
 
    public NewDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
                + KEY_CUSTOMER_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        
        String CREATE_RESERVATIONS_TABLE = "CREATE TABLE " + TABLE_RESERVATIONS + "("
                + KEY_CUSTOMER_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        
        db.execSQL(CREATE_CUSTOMERS_TABLE);
        db.execSQL(CREATE_RESERVATIONS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATIONS);
 
        // Create tables again
        onCreate(db);
    }
    
    // Customers Methods ----------------------------------------------------------------------------------------------
    
    public void addContact(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, customer.getName()); // Contact Name
        values.put(KEY_PH_NO, customer.getPhone()); // Contact Phone Number
     
        // Inserting Row
        db.insert(TABLE_CUSTOMERS, null, values);
        db.close(); // Closing database connection
    }
    
    public Customer getCustomer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(TABLE_CUSTOMERS, new String[] { KEY_CUSTOMER_ID,
                KEY_NAME, KEY_PH_NO }, KEY_CUSTOMER_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Customer contact = new Customer(cursor.getString(2), cursor.getString(1));
        return contact;
    }
    // -----------------------------------------------------------------------------------------------------------------

}
