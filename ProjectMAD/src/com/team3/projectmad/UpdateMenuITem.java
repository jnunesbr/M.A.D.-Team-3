package com.team3.projectmad;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.team3.database.MenuItemDAO;
import com.team3r.projectmad.R;

public class UpdateMenuITem extends Activity {

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_menu_item);
		Spinner type_spinner = (Spinner) findViewById(R.id.widget36);
		Spinner smallc_spinner = (Spinner) findViewById(R.id.widget37);
		Spinner ItemName_spinner = (Spinner) findViewById(R.id.widget38);
		type_spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
		smallc_spinner
				.setOnItemSelectedListener(new MyOnItemSelectedListener());
		ItemName_spinner
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
		ArrayList<com.team3.basics.MenuItem> menuitems = new ArrayList<com.team3.basics.MenuItem>();
		MenuItemDAO menuitemDAO = new MenuItemDAO(getBaseContext());
		menuitems = menuitemDAO.getAllMenuItems();
		String[] Inames = new String[menuitems.size()];
		for (int i = 0; i < menuitems.size(); i++) {
			if (menuitems.get(i).getName() != null) {
				Inames[i] = menuitems.get(i).getName();
			}
		}
		@SuppressWarnings("rawtypes")
		ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, Inames);
		ItemName_spinner.setAdapter(adapter3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_menu_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 * 
	 * public static class PlaceholderFragment extends Fragment {
	 * 
	 * public PlaceholderFragment() { }
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 *           container, Bundle savedInstanceState) { View rootView =
	 *           inflater.inflate( R.layout.fragment_update_menu_item,
	 *           container, false); return rootView; } }
	 */

	public void update(View view) {

		Spinner type_spinner = (Spinner) findViewById(R.id.widget36);
		Spinner smallc_spinner = (Spinner) findViewById(R.id.widget37);
		Spinner itemname_spinner = (Spinner) findViewById(R.id.widget38);
		MenuItemDAO menuItemDAO = new MenuItemDAO(getApplicationContext());
		com.team3.basics.MenuItem newitem = new com.team3.basics.MenuItem(
				String.valueOf(itemname_spinner.getSelectedItem()),
				String.valueOf(type_spinner.getSelectedItem()),
				Integer.parseInt(String.valueOf(smallc_spinner
						.getSelectedItem())));
		menuItemDAO.UMenuItem((com.team3.basics.MenuItem) newitem);
		setResult(RESULT_OK);
		finish();
	}
}
