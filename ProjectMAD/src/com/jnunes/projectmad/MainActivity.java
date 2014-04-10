package com.jnunes.projectmad;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

	private MainActivity context;
	private Dialog popup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
	}

	public void showpopup(View view) {
		popup = new Dialog(this);

		popup.setContentView(R.layout.activity_popup);
		popup.setTitle("Select Customer");
		Button bt = (Button) popup.findViewById(R.id.btnSubmit);
		bt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Search s = new Search();
				EditText et = (EditText) popup.findViewById(R.id.etPhone);
				s.execute(context,et.getText().toString());
			}
		});
		popup.show();
	}
	
	public void showReservation(View view){
		Intent intent = new Intent(this,ReservationActivity.class);
		startActivity(intent);
	}

	public void doStuff(Customer customer) {
		if (customer != null) {
			Toast.makeText(this, customer.getName(), Toast.LENGTH_LONG).show();
		}else{
			AlertDialog.Builder dlgQuestion = new AlertDialog.Builder(context);
			dlgQuestion.setMessage("Customer not found. Would you like to register a new one?");
			dlgQuestion.setNegativeButton("No", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
										
				}
			});
			
			dlgQuestion.setPositiveButton("Yes", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					AddActivity addCustomer =  new AddActivity(context);
					EditText txtPhone = (EditText) popup.findViewById(R.id.etPhone);
					addCustomer.setTitle("Add New Customer");
					addCustomer.showScreen(txtPhone.getText().toString());
				}
			});
			dlgQuestion.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class Search extends AsyncTask<Void, Void, Void> {

		private ProgressDialog dialog = new ProgressDialog(MainActivity.this);
		private Customer customer = null;
		private MainActivity main;
		private String key;
		HashMap<String, Customer> list = new HashMap<String, Customer>();

		public Search execute(MainActivity main, String phone) {
			super.execute();
			this.main = main;
			key = phone;
			populate();
			return this;
		}

		private void populate() {
			list.put("1234567890", new Customer("1234567890", "Joao"));
			list.put("1234567891", new Customer("1234567891", "Maria"));
			list.put("1234567892", new Customer("1234567892", "Hanna"));
			list.put("1234567893", new Customer("1234567893", "Anne"));
			list.put("1234567894", new Customer("1234567894", "Paul"));
			list.put("1234567895", new Customer("1234567895", "Leo"));
			list.put("1234567896", new Customer("1234567896", "Peter"));
		}

		@Override
		protected void onPreExecute() {
			this.dialog.setMessage("Searching...");
			this.dialog.show();
		}

		@Override
		protected void onPostExecute(Void v) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			main.doStuff(customer);
		}

		@Override
		protected Void doInBackground(Void... v) {
			customer = list.get(key);
			return null;
		}
	}

}
