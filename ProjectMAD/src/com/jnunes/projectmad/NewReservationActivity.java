package com.jnunes.projectmad;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class NewReservationActivity extends Activity {

	private Calendar calendar;
	private Date choosenDate;
	private Customer choosenCustomer;
	private int position;
	
	private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			TextView txtDate = (TextView) findViewById(R.id.txtLblReservationDate);
			txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
			choosenDate.setYear(year-1900);
			choosenDate.setMonth(monthOfYear);
			choosenDate.setDay(dayOfMonth+1);
		}
	};

	private TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			TextView txtTime = (TextView) findViewById(R.id.txtLblReservationTime);
			txtTime.setText(hourOfDay + ":" + minute);
			choosenDate.setHour(hourOfDay);
			choosenDate.setMinute(minute);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_reservation);
		calendar = Calendar.getInstance();
		choosenDate = new Date();
		Spinner spinner = (Spinner) findViewById(R.id.spQntPeople);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i=0;i<40;i++){
			numbers.add(i+1);
		}
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, numbers); 
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_reservation, menu);
		return true;
	}

	public void openPicker(View view) {
		switch (view.getId()) {
		case R.id.btnChooseReservationDate:
			DatePickerDialog dateDialog = new DatePickerDialog(this,
					dateListener, calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			dateDialog.show();
			break;
		case R.id.btnChooseReservationTime:
			TimePickerDialog timeDialog = new TimePickerDialog(this,
					timeListener, calendar.get(Calendar.HOUR_OF_DAY),
					calendar.get(Calendar.MINUTE), true);
			timeDialog.show();
			break;
		}
	}

	public void confirmReservation(View view) {
		Database database = Database.getInstance();
		choosenCustomer = database.getCustomers().get(position);
		ArrayList<String> items = new ArrayList<String>();
		items.add("item 45");
		Spinner spinner = (Spinner) findViewById(R.id.spQntPeople);
		database.addReservation(new Reservation(choosenCustomer,items,choosenDate,spinner.getSelectedItemPosition()+1));
		ListView listview = (ListView) findViewById(R.id.listview);
		setResult(RESULT_OK);
		finish();
	}

	public void cancelReservation(View view) {
		onBackPressed();
	}

	public void findCustomer(View view) {
		final Dialog findCustomer = new Dialog(this);
		findCustomer.setTitle("Select Customer");
		findCustomer.setContentView(R.layout.layout_find_customer);

		AutoCompleteTextView tvName = (AutoCompleteTextView) findCustomer
				.findViewById(R.id.actxtName);
		String[] names = getNamesList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names);
		tvName.setAdapter(adapter);
		tvName.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView txt = (TextView) findViewById(R.id.txtLblNameCustomer);
				TextView txt2 = (TextView) findCustomer
						.findViewById(R.id.actxtName);
				String name = txt2.getText().toString();
				txt.setText(name.split("-")[0].trim());
				position = arg2;
				findCustomer.dismiss();
			}

		});
		findCustomer.show();
	}

	private String[] getNamesList() {
		Database database = Database.getInstance();
		String[] names = new String[database.getCustomers().size()];
		int i = 0;
		for (Customer customer : database.getCustomers()) {
			names[i] = customer.getName() + " - " + customer.getPhone();
			i++;
		}

		return names;
	}

}
