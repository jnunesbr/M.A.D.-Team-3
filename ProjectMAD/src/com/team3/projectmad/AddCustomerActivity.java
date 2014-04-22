package com.jnunes.projectmad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jnunes.basics.Customer;
import com.jnunes.database.CustomerDAO;

public class AddCustomerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_layout);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			if (!extras.isEmpty()) {
				if (extras.getString("code").equals("edit")) {
					EditText etName = (EditText) findViewById(R.id.txtAddName);
					etName.setText(extras.getString("name"));

					EditText etPhone = (EditText) findViewById(R.id.txtAddPhone);
					etPhone.setText(extras.getString("phone"));

					EditText etAddress = (EditText) findViewById(R.id.txtAddAddress);
					etAddress.setText(extras.getString("address"));
				}
			}
		}
	}

	public void addCustomer(View view) {

		EditText text = (EditText) findViewById(R.id.txtAddName);
		String name = text.getText().toString();
		EditText text1 = (EditText) findViewById(R.id.txtAddPhone);
		String phone = text1.getText().toString();
		EditText text2 = (EditText) findViewById(R.id.txtAddAddress);
		String address = text2.getText().toString();

		CustomerDAO dao = new CustomerDAO(this);
		Customer customer = new Customer(phone, name, address);
		Intent intent = getIntent();
		if (intent.getExtras().getString("code").equals("add")) {
			intent.putExtra("id", Long.toString(dao.addCustomer(customer)));
		}
		if (getIntent().getExtras().getString("code").equals("edit")) {
			customer.setId(Long.parseLong(intent.getExtras().getString("id")));
			dao.editCustomer(customer);
		}
		setResult(RESULT_OK, intent);
		finish();
	}

	public void cancel(View view) {
		finish();
	}

}
