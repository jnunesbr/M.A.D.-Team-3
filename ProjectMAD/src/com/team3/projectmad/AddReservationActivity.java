package com.jnunes.projectmad;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jnunes.basics.Customer;
import com.jnunes.basics.Date;
import com.jnunes.basics.Reservation;
import com.jnunes.database.CustomerDAO;
import com.jnunes.database.ReservationDAO;

public class AddReservationActivity extends Activity implements
		OnItemClickListener {

	private Calendar calendar;
	private Date choosenDate;
	private Customer choosenCustomer;
	private long reservationId;
	private Dialog dialogFindCustomer;

	private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			TextView txtDate = (TextView) findViewById(R.id.txtLblReservationDate);
			txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
			choosenDate.setYear(year);
			choosenDate.setMonth(monthOfYear + 1);
			choosenDate.setDay(dayOfMonth);
		}
	};

	private TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			TextView txtTime = (TextView) findViewById(R.id.txtLblReservationTime);
			if (minute < 10) {
				txtTime.setText(hourOfDay + ":0" + minute);
			} else {
				txtTime.setText(hourOfDay + ":" + minute);
			}
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
		Intent intent = getIntent();
		Spinner spinner = (Spinner) findViewById(R.id.spQntPeople);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 40; i++) {
			numbers.add(i + 1);
		}
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item, numbers);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		if (intent.getExtras().getString("code").equals("edit")) {
			CustomerDAO dao = new CustomerDAO(this);
			choosenCustomer = dao.getCustomer(Long.parseLong(intent.getExtras()
					.getString("customerId")));
			
			choosenDate = new Date(intent.getExtras().getString("date"));

			spinner.setSelection(Integer.parseInt(intent.getExtras().getString(
					"people")) - 1);

			TextView tvName = (TextView) findViewById(R.id.txtLblNameCustomer);
			TextView tvDate = (TextView) findViewById(R.id.txtLblReservationDate);
			TextView tvTime = (TextView) findViewById(R.id.txtLblReservationTime);

			tvName.setText(choosenCustomer.getName());
			tvDate.setText(choosenDate.getDateString());
			tvTime.setText(choosenDate.getDateTimeString().split(" ")[1]);

			this.reservationId = Long.parseLong(intent.getExtras().getString(
					"id"));
		}
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
		ReservationDAO dao = new ReservationDAO(this);
		Intent intent = getIntent();
		String code = intent.getExtras().getString("code");
		Spinner spinner = (Spinner) findViewById(R.id.spQntPeople);
		Reservation reservation = new Reservation(choosenCustomer, choosenDate,
				spinner.getSelectedItemPosition() + 1);

		if (code.equals("add")) {
			intent.putExtra("id",
					Long.toString(dao.addReservation(reservation)));
		}
		if (code.equals("edit")) {
			reservation.setId(reservationId);
			dao.editReservation(reservation);
		}
		setResult(RESULT_OK, intent);
		finish();
	}

	public void cancelReservation(View view) {
		onBackPressed();
	}

	public void findCustomer(View view) {
		final Dialog findCustomer = new Dialog(this);
		findCustomer.setTitle("Select Customer");
		findCustomer.setContentView(R.layout.layout_find_customer);
		dialogFindCustomer = findCustomer;

		AutoCompleteTextView acName = (AutoCompleteTextView) findCustomer
				.findViewById(R.id.actxtName);
		String[] names = getNamesList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.autocomplete_item, names);
		acName.setAdapter(adapter);
		acName.setOnItemClickListener(this);
		findCustomer.show();
	}

	private String[] getNamesList() {
		CustomerDAO dao = new CustomerDAO(this);
		String[] names = new String[dao.getAllCustomers().size()];
		int i = 0;
		for (Customer customer : dao.getAllCustomers()) {
			names[i] = customer.getName() + " - " + customer.getPhone();
			i++;
		}
		return names;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView txt = (TextView) findViewById(R.id.txtLblNameCustomer);
		TextView txt2 = (TextView) dialogFindCustomer
				.findViewById(R.id.actxtName);
		String name = txt2.getText().toString();
		txt.setText(name.split("-")[0].trim());

		CustomerDAO dao = new CustomerDAO(this);
		ArrayList<Customer> customers = dao.getAllCustomers();
		for (Customer c : customers) {
			if (c.getName().equals(name.split("-")[0].trim())) {
				if (c.getPhone().equals(name.split("-")[1].trim())) {
					choosenCustomer = dao.getCustomer(c.getId());
					break;
				}
			}
		}
		dialogFindCustomer.dismiss();
	}

}
