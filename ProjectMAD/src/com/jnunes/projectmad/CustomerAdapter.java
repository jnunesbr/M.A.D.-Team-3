package com.jnunes.projectmad;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jnunes.basics.Customer;

public class CustomerAdapter extends BaseAdapter {
	private Context context;
	private List<Customer> data;
	private static LayoutInflater inflater = null;

	public CustomerAdapter(Context context, List<Customer> data) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
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
			vi = inflater.inflate(R.layout.customer_row, null);
		Log.d("Test","Position: "+position);
		Customer customer = data.get(position);
		
		Log.d("Test","Name: "+customer.getName());
		Log.d("Test","Phone: "+customer.getPhone());
		TextView txtName = (TextView) vi.findViewById(R.id.txtCustomerName);
		txtName.setText(customer.getName());

		TextView txtDate = (TextView) vi.findViewById(R.id.txtCustomerPhone);
		txtDate.setText(customer.getPhone());

		return vi;
	}
}
