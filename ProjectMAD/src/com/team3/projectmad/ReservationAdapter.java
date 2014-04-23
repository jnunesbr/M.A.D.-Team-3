package com.team3.projectmad;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.team3.basics.Reservation;
import com.team3r.projectmad.R;

class ReservationAdapter extends BaseAdapter {

	private Context context;
	private List<Reservation> data;
	private static LayoutInflater inflater = null;

	public ReservationAdapter(Context context, List<Reservation> data) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (vi == null)
			vi = inflater.inflate(R.layout.reservation_row, null);
		Reservation reservation = data.get(position);

		TextView txtId = (TextView) vi.findViewById(R.id.txtReservationIdRow);
		txtId.setText(Long.toString(reservation.getId()));

		TextView txtName = (TextView) vi.findViewById(R.id.txtCustomer);
		txtName.setText(reservation.getCustomer().getName());

		TextView txtDate = (TextView) vi.findViewById(R.id.txtDate);
		txtDate.setText(reservation.getReservationDate().getDateString());

		TextView txtPhone = (TextView) vi.findViewById(R.id.txtPhone);
		txtPhone.setText(reservation.getCustomer().getPhone());

		TextView txtHour = (TextView) vi.findViewById(R.id.txtHour);
		txtHour.setText(reservation.getReservationDate().getHourString());

		TextView txtPeople = (TextView) vi.findViewById(R.id.txtQntPeople);
		txtPeople.setText("" + reservation.getNumberPeople());
		
		return vi;
	}
}