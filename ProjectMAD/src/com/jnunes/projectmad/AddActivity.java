package com.jnunes.projectmad;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends Dialog {

	public AddActivity(Context context) {
		super(context);
		setContentView(R.layout.add_layout);
	}

	public void add(View view) {

		EditText text = (EditText) findViewById(R.id.editText1);
		String value = text.getText().toString();
		EditText text1 = (EditText) findViewById(R.id.editText2);
		String value1 = text1.getText().toString();
		EditText text2 = (EditText) findViewById(R.id.editText3);
		String value2 = text2.getText().toString();
	}
	
	public void showScreen(String phone){
		EditText txtPhone = (EditText) findViewById(R.id.editText2);
		txtPhone.setText(phone);
		show();
	}

	public void cancel(View view) {
	}

}
