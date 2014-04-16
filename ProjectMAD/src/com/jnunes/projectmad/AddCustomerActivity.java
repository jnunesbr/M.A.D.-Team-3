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
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (!extras.isEmpty()) {
				EditText et = (EditText) findViewById(R.id.editText2);
				et.setText(extras.getString("phone"));
			}
		}
	}

	public void addCustomer(View view) {

		EditText text = (EditText) findViewById(R.id.editText1);
		String name = text.getText().toString();
		EditText text1 = (EditText) findViewById(R.id.editText2);
		String phone = text1.getText().toString();
		EditText text2 = (EditText) findViewById(R.id.editText3);
		String address = text2.getText().toString();

		CustomerDAO dao = new CustomerDAO(this);
		Customer customer = new Customer(phone, name, address);
		dao.addCustomer(customer);
		setResult(RESULT_OK);
		finish();
	}

	public void cancel(View view) {
		finish();
	}

}
