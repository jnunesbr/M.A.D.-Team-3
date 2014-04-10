package com.jnunes.projectmad;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ReservationActivity extends Activity {

	private ListView listview;
	private Context context;
	
	private ReservationAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation);
		context = this;
		Database database = Database.getInstance();;
		EditText etSearch = (EditText) findViewById(R.id.etSearch);
		etSearch.clearFocus();
		final ArrayList<Reservation> reservations = database.getReservations();
		listview = (ListView) findViewById(R.id.listview);
		adapter = new ReservationAdapter(this, reservations);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final Dialog popup = new Dialog(context);

				popup.setContentView(R.layout.layout_reservation_information);
				popup.setTitle("Pickup Information");

				TextView view = (TextView) popup.findViewById(R.id.txtName);
				view.setText(reservations.get(arg2).getCustomer().getName());
				popup.show();
			}

		});
	}

	public void makeReservation(View view) {
		Intent intent = new Intent(this, NewReservationActivity.class);
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	        	adapter.notifyDataSetChanged();
	        }
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pick_up, menu);
		return true;
	}

}
