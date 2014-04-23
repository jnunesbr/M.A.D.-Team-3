package com.team3.projectmad;



/**
 * @author Lokesh
 *
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.team3.basics.MenuItem;
import com.team3.basics.Pickup;
import com.team3.database.PickUpDAO;
import com.team3r.projectmad.R;

public class ViewPickup extends Activity  {

	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpickups);
		PickUpDAO PUPDao = new PickUpDAO(getApplicationContext());
		ArrayList<Pickup> PUPList = new ArrayList<Pickup>();
		PUPList = PUPDao.getAllPickUps();
		String[] PUPListName = new String[PUPList.size()];
		ListView lv = (ListView)findViewById(R.id.widget40);
		for (int i=0;i<PUPList.size();i++)
		{
			PUPListName[i] = PUPList.get(i).getCustomer().getName() + ";" + PUPList.get(i).getPickupDate();
		}
		ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, PUPListName);	
		lv.setAdapter(adapter);
		
		
		/*	if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		} */
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
	//	int id = item.getItemId();
	//	if (id == R.id.action_settings) {
	//		return true;
	//	}
		return super.onOptionsItemSelected((android.view.MenuItem) item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/* public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_add_food_item,
					container, false);
			return rootView;
		}
	} */
	
public void goback(View view){
	Intent intent = new Intent(this,ViewMenuItems.class);
	startActivity(intent);
		
	}


	
}
