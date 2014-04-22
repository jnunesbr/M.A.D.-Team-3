package com.jnunes.projectmad;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jnunes.basics.Customer;
import com.jnunes.database.CustomerDAO;

public class CustomerActivity extends Activity implements OnItemClickListener,
		OnClickListener {
	private ListView listview;
	private Context context;
	private CustomerAdapter adapter;
	private CustomerDAO dao;
	private List<Customer> customers;
	private long id;
	private int position;
	private View viewItemClicked;
	private Dialog informationDialog;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer);
		context = this;
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		dao = new CustomerDAO(this);
		EditText etSearch = (EditText) findViewById(R.id.etSearchcustomer);
		etSearch.clearFocus();
		customers = dao.getAllCustomers();

		listview = (ListView) findViewById(R.id.listviewcustomer);
		adapter = new CustomerAdapter(this, customers);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	public void addCustomer(View view) {
		Intent intent = new Intent(this, AddCustomerActivity.class);
		intent.putExtra("code", "add");
		startActivityForResult(intent, 0);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			CustomerDAO dao = new CustomerDAO(this);
			Customer customer;
			if(requestCode == 0){
				customer = dao.getCustomer(Long.parseLong(data.getExtras().getString("id")));
				customers.add(customer);
			}
			if(requestCode == 1){
				customer = dao.getCustomer(id);
				customers.get(position).setName(customer.getName());
				customers.get(position).setPhone(customer.getPhone());
				customers.get(position).setAddress(customer.getAddress());
				informationDialog.dismiss();
			}
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		viewItemClicked = view;
		final Dialog popup = new Dialog(context);
		final Context context = this.context;
		this.position = position;
		informationDialog = popup;
		popup.setContentView(R.layout.layout_customer_information);
		popup.setTitle("Customer Details");
		
		TextView tv = (TextView)view.findViewById(R.id.txtCustomerIdRow);
		this.id = Long.parseLong(tv.getText().toString());
		
		CustomerDAO dao = new CustomerDAO(this);
		Customer c = dao.getCustomer(this.id);
		TextView tvName = (TextView) popup
				.findViewById(R.id.txtCustomerInformationName);
		tvName.setText(c.getName());

		TextView tvPhone = (TextView) popup
				.findViewById(R.id.txtCustomerInformationPhone);
		tvPhone.setText(c.getPhone());

		TextView tvAddress = (TextView) popup
				.findViewById(R.id.txtCustomerInformationAddress);
		tvAddress.setText(c.getAddress());

		Button btnDelete = (Button) popup
				.findViewById(R.id.btnCustomerInformationDelete);
		btnDelete.setOnClickListener(CustomerActivity.this);

		Button btnEdit = (Button) popup
				.findViewById(R.id.btnCustomerInformationEdit);
		btnEdit.setOnClickListener(CustomerActivity.this);

		popup.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnCustomerInformationDelete:
			final AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(
					this);
			confirmationDialog
					.setTitle("Are you sure you want to delete this customer?");
			confirmationDialog.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							CustomerDAO dao = new CustomerDAO(
									getApplicationContext());
							dao.deleteCustomer(id);
							customers.remove(position);
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
		case R.id.btnCustomerInformationEdit:
			Intent intent = new Intent(this, AddCustomerActivity.class);
			intent.putExtra("code", "edit");
			
			CustomerDAO dao = new CustomerDAO(this);
			Customer c = dao.getCustomer(id);
			
			intent.putExtra("id", Long.toString(id));
			intent.putExtra("name",c.getName());
			intent.putExtra("phone",c.getPhone());
			intent.putExtra("address",c.getAddress());
			startActivityForResult(intent, 1);
			break;
		}
	}

}
