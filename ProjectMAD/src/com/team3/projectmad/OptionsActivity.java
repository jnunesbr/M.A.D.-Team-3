package com.team3.projectmad;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.team3.database.Database;
import com.team3r.projectmad.R;

public class OptionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		Database db = Database.getInstance(this);
//		db.dropDatabase();
		Log.d("Test","Total Customers: "+db.getAllCustomers().size());
		Log.d("Test","Total Reservations: "+db.getAllReservations().size());
	}

	public void onClick(View view) {

		super.finish();
	}

	public void customer(View view) {
		Intent i = new Intent(this, CustomerActivity.class);
		startActivity(i);
	}

	public void menu(View view) {
		Intent i = new Intent(this, ViewMenuItems.class);
		startActivity(i);
	}

	public void pickup(View view) {
		 Intent i = new Intent(this, ViewPickup.class);
		 startActivity(i);
	}

	public void reservation(View view) {
		Intent i = new Intent(this, ReservationActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}