package com.team3.projectmad;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.team3.basics.MenuItem;
import com.team3.database.MenuItemDAO;
import com.team3r.projectmad.R;

public class ViewMenuItems extends Activity {
	EditText MenuSearch; // Search EditText
	ArrayList<HashMap<String, String>> productList; // ArrayList for List view
	ViewMenuItems context;
	ArrayAdapter<String> adapter;
	String[] Inames;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_menu_items);
		ArrayList<MenuItem> menuitems = new ArrayList<MenuItem>();
		MenuItemDAO menuitemDAO = new MenuItemDAO(getBaseContext());
		menuitems = menuitemDAO.getAllMenuItems();
		Inames = new String[menuitems.size()];
		for (int i = 0; i < menuitems.size(); i++) {
			if (menuitems.get(i).getName() != null) {
				Inames[i] = menuitems.get(i).getName();
			}
		}
		final ListView lv = (ListView) findViewById(R.id.FoodMenuView);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, Inames);
		lv.setAdapter(adapter);
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				final Object o = lv.getItemAtPosition(position);
				// Toast.makeText(getBaseContext(),o.toString(),Toast.LENGTH_SHORT).show();
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						ViewMenuItems.this);
				alertDialogBuilder.setTitle("Menu Edit Options");
				alertDialogBuilder
						.setMessage(
								"What would like to do " + o.toString()
										+ " ???")
						.setCancelable(false)
						.setPositiveButton("Edit",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										Intent i = new Intent(
												ViewMenuItems.this,
												UpdateMenuITem.class);
										startActivityForResult(i, 1);
										// MainActivity.this.finish();
										// dialog.cancel();

									}
								})
						.setNegativeButton("Delete",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										// dialog.cancel();
										String[] arg = new String[1];
										MenuItemDAO menuitemDAO = new MenuItemDAO(
												getBaseContext());
										menuitemDAO.deletMenuItem(o.toString());
										Toast.makeText(getBaseContext(),
												"Item Deleted",
												Toast.LENGTH_SHORT).show();
										finish();
										startActivity(getIntent());

									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				return false;

			}
		});

		/*
		 * if (savedInstanceState == null) {
		 * getSupportFragmentManager().beginTransaction() .add(R.id.container,
		 * new PlaceholderFragment()).commit(); }
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_menu_items, menu);
		return true;
	}

	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		// int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	@SuppressLint("NewApi")
	/*
	 * public static class PlaceholderFragment extends Fragment {
	 * 
	 * public PlaceholderFragment() { }
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) { View rootView =
	 * inflater.inflate(R.layout.fragment_view_menu_items, container, false);
	 * return rootView; } }
	 */
	public void additem(View view) {

		Intent intent = new Intent(this, AddFoodItem.class);
		startActivityForResult(intent, 2);

	}

	public void back(View view) {

		/*
		 * Intent intent = new Intent(this,AddFoodItem.class);
		 * startActivity(intent);
		 */

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == 1 || requestCode == 1) {
				finish();
				startActivity(getIntent());
			}
		}
	}

}
