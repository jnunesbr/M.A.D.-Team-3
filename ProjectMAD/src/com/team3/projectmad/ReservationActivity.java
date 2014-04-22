package com.jnunes.projectmad;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jnunes.basics.Reservation;
import com.jnunes.database.ReservationDAO;

public class ReservationActivity extends Activity implements
		OnItemClickListener, android.view.View.OnClickListener {

	private ListView listview;
	private Context context;
	private ArrayList<Reservation> reservations;
	private ReservationAdapter adapter;
	private Reservation clickedReservation;
	private Dialog informationDialog;
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation);
		context = this;

		ReservationDAO dao = new ReservationDAO(this);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		reservations = dao.getAllReservations();
		for (Reservation r : reservations) {
			Log.d("Reservation", r.getId() + " - " + r.getCustomer().getName()
					+ " - " + r.getReservationDate().getDateTimeString()
					+ " - " + r.getNumberPeople());
		}
		listview = (ListView) findViewById(R.id.listviewreservation);
		adapter = new ReservationAdapter(this, reservations);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	public void makeReservation(View view) {
		Intent intent = new Intent(this, AddReservationActivity.class);
		intent.putExtra("code", "add");
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			ReservationDAO dao = new ReservationDAO(this);
			if (requestCode == 0) {
				Reservation reservation = dao.getReservation(Long
						.parseLong(data.getExtras().getString("id")));
				reservations.add(reservation);
			}
			if (requestCode == 1) {
				Reservation reservation = dao.getReservation(clickedReservation
						.getId());
				reservations.get(position).setCustomer(
						reservation.getCustomer());
				reservations.get(position).setNumberPeople(
						reservation.getNumberPeople());
				reservations.get(position).setReservationDate(
						reservation.getReservationDate());
				informationDialog.dismiss();
			}
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		final Dialog popup = new Dialog(context);
		this.position = position;

		popup.setContentView(R.layout.layout_reservation_information);
		popup.setTitle("Reservation Details");

		ReservationDAO dao = new ReservationDAO(this);

		TextView tvId = (TextView) view.findViewById(R.id.txtReservationIdRow);

		clickedReservation = dao.getReservation(Long.parseLong(tvId.getText()
				.toString()));

		TextView tvCustomer = (TextView) popup
				.findViewById(R.id.txtReservationInformationCustomer);
		TextView tvDate = (TextView) popup
				.findViewById(R.id.txtReservationInformationDate);
		TextView tvPeople = (TextView) popup
				.findViewById(R.id.txtReservationInformationPeople);

		if (tvCustomer == null) {
			Log.d("Teste", " tvCustomer is null");
		}
		if (clickedReservation == null) {
			Log.d("Teste", "clickedReservation is null");
		}
		if (clickedReservation.getCustomer() == null) {
			Log.d("Teste", "Customer is null");
		}
		tvCustomer.setText(clickedReservation.getCustomer().getName());
		tvDate.setText(clickedReservation.getReservationDate()
				.getDateTimeString());
		tvPeople.setText("" + clickedReservation.getNumberPeople());

		Button btnDelete = (Button) popup
				.findViewById(R.id.btnReservationInformationDelete);
		Button btnEdit = (Button) popup
				.findViewById(R.id.btnReservationInformationEdit);

		btnDelete.setOnClickListener(this);
		btnEdit.setOnClickListener(this);

		informationDialog = popup;
		popup.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnReservationInformationDelete:
			final AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(
					this);
			confirmationDialog
					.setTitle("Are you sure you want to delete this reservation?");
			confirmationDialog.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							ReservationDAO dao = new ReservationDAO(
									getApplicationContext());
							dao.deleteReservation(clickedReservation.getId());
							reservations.remove(position);
							adapter.notifyDataSetChanged();
							informationDialog.dismiss();
						}
					});
			confirmationDialog.setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			confirmationDialog.create().show();
			break;
		case R.id.btnReservationInformationEdit:
			Intent intent = new Intent(this, AddReservationActivity.class);
			intent.putExtra("code", "edit");

			intent.putExtra("id", Long.toString(clickedReservation.getId()));
			intent.putExtra("name", clickedReservation.getCustomer().getName());
			intent.putExtra("customerId",
					Long.toString(clickedReservation.getCustomer().getId()));
			intent.putExtra("date", clickedReservation.getReservationDate()
					.getDateTimeString());
			intent.putExtra("people",
					Integer.toString(clickedReservation.getNumberPeople()));
			startActivityForResult(intent, 1);
			break;
		}
	}
}
