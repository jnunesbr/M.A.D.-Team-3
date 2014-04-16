package com.jnunes.projectmad;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jnunes.basics.Customer;
import com.jnunes.database.CustomerDAO;

public class CustomerActivity extends Activity implements OnItemClickListener {
	private ListView listview;
	private Context context;
	private CustomerAdapter adapter;
	private CustomerDAO dao;
	private List<Customer> customers;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer);
		context = this;
		dao = new CustomerDAO(this);
		EditText etSearch = (EditText) findViewById(R.id.etSearchcustomer);
		etSearch.clearFocus();
		customers = dao.getAllCustomers();
		
		customers.add(new Customer("97874514","pauloasdf","alknsfklansdf"));
		Log.d("Test", ""+customers.size());
		listview = (ListView) findViewById(R.id.listviewcustomer);
		adapter = new CustomerAdapter(this, customers);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	public void addCustomer(View view) {
		Intent intent = new Intent(this, AddCustomerActivity.class);
		startActivityForResult(intent, 0);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			customers = dao.getAllCustomers();
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		final Dialog popup = new Dialog(context);

		popup.setContentView(R.layout.layout_reservation_information);
		popup.setTitle("Customer Details");

		TextView tv = (TextView) popup.findViewById(R.id.txtName);
		tv.setText(customers.get(position).getName());
		popup.show();

	}

}
