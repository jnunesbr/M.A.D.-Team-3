package com.team3.projectmad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.team3.basics.MenuItem;
import com.team3.database.MenuItemDAO;
import com.team3r.projectmad.R;

public class AddFoodItem extends Activity {

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_food_item);

		Spinner type_spinner = (Spinner) findViewById(R.id.widget40);
		Spinner smallc_spinner = (Spinner) findViewById(R.id.widget41);
		type_spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
		smallc_spinner
				.setOnItemSelectedListener(new MyOnItemSelectedListener());
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
				this, R.array.ItemType_array,
				android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		type_spinner.setAdapter(adapter1);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
				this, R.array.Cost_array, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		smallc_spinner.setAdapter(adapter2);

		/*
		 * if (savedInstanceState == null) {
		 * getSupportFragmentManager().beginTransaction() .add(R.id.container,
		 * new PlaceholderFragment()).commit(); }
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_food_item, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		// int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		return super.onOptionsItemSelected((android.view.MenuItem) item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*
	 * public static class PlaceholderFragment extends Fragment {
	 * 
	 * public PlaceholderFragment() { }
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) { View rootView =
	 * inflater.inflate(R.layout.fragment_add_food_item, container, false);
	 * return rootView; } }
	 */
	public void additemtodb(View view) {

		Spinner type_spinner = (Spinner) findViewById(R.id.widget40);
		Spinner smallc_spinner = (Spinner) findViewById(R.id.widget41);
		EditText ItemName = (EditText) findViewById(R.id.widget39);
		String ItemNameString = ItemName.getText().toString();
		MenuItemDAO menuItemDAO = new MenuItemDAO(getApplicationContext());
		MenuItem newitem = new MenuItem(ItemNameString,
				String.valueOf(type_spinner.getSelectedItem()),
				Integer.parseInt(String.valueOf(smallc_spinner
						.getSelectedItem())));
		menuItemDAO.addMenuItem(newitem);
		Toast.makeText(this, "New Item Inserted into Food Item Menu",
				Toast.LENGTH_SHORT).show();
		setResult(RESULT_OK);
		finish();

	}

	public void goback(View view) {
		Intent intent = new Intent(this, ViewMenuItems.class);
		startActivity(intent);

	}

}
