/**
 * 
 */
package com.team3.projectmad;

/**
 * @author Lokesh
 *
 */

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.team3.basics.Customer;
import com.team3.basics.MenuItem;
import com.team3.basics.Pickup;
import com.team3.database.CustomerDAO;
import com.team3.database.MenuItemDAO;
import com.team3.database.PickUpDAO;
import com.team3r.projectmad.R;

public class AddPickups extends Activity implements  NumberPicker.OnValueChangeListener {

	ArrayAdapter<String> adapter;
	ArrayList<MenuItem> menuitems = new ArrayList<MenuItem>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	Pickup pup = new Pickup();
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addpickups);
		//final ArrayList<MenuItem> menuitems = new ArrayList<MenuItem>();
		//ArrayList<Customer> customers = new ArrayList<Customer>();
		final MenuItemDAO menuitemDAO = new MenuItemDAO(getBaseContext());
		CustomerDAO customerDAO = new CustomerDAO(getBaseContext());
		menuitems = menuitemDAO.getAllMenuItems();
		customers = customerDAO.getAllCustomers();
		String[] Inames=new String[menuitems.size()];
		String[] Cnames=new String[customers.size()];
		for(int i=0; i<menuitems.size();i++){
			if(menuitems.get(i).getName() !=null )
			{	Inames[i] = menuitems.get(i).getName();  } } 
		final ListView lv = (ListView)findViewById(R.id.widget35);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, Inames);
	    lv.setAdapter(adapter);
	    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Object o= lv.getItemAtPosition(position);
				showlok(o.toString());	
						
			}
	});
	    
	    
	    for(int i=0; i<customers.size();i++){
	    	if(customers.get(i).getName() !=null )
			{	Cnames[i] = customers.get(i).getName();  } } 
	    @SuppressWarnings({ "rawtypes", "unchecked" })
	    Spinner name_spinner   = (Spinner) findViewById(R.id.customername);
	    ArrayAdapter<CharSequence> adapter3 =new ArrayAdapter(this, 
			    android.R.layout.simple_spinner_item,  Cnames);
	    name_spinner.setAdapter(adapter3);
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
public void onClick(View v) {
		
		// TODO Auto-generated method stub
		
	}

public void placeorder(View v) {
	
	Spinner name_spinner   = (Spinner) findViewById(R.id.customername);
	Date d = new Date();
	for(int i=0; i<customers.size();i++){
    	if(customers.get(i).getName().equals(String.valueOf(name_spinner.getSelectedItem())) )
		{	
    		PickUpDAO PDAO = new PickUpDAO(getApplicationContext());
    		pup.setCustomer(customers.get(i));
    		//pup.setPickupDate(pickupDate)
    		PDAO.addPickup(pup);
    		
		} } 
	// TODO Auto-generated method stub
	
}

	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		// TODO Auto-generated method stub
		
	}
	
	private void showlok(final String s) {
		// TODO Auto-generated method stub
		final Dialog d = new Dialog(AddPickups.this);
		final Object l = 0;
		d.setTitle("Select the Count");
		d.setContentView(R.layout.itemcountselect);
		Button b1 = (Button) d.findViewById(R.id.lb1);
        Button b2 = (Button) d.findViewById(R.id.lb2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(10);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        b1.setOnLongClickListener(new OnLongClickListener()
        {
         @Override
		public boolean onLongClick(View v) {
        	 
        	for(int i=0; i<menuitems.size();i++){
        		if(menuitems.get(i).getName().equals(s) )
     				{     				
        			Toast.makeText(getBaseContext(),"Item Added to the Order",Toast.LENGTH_SHORT).show();
     				//pup.addItem(menuitems.get(i), Integer.parseInt(String.valueOf(np.getValue())));
     				break;
     				} 
     			}  
        	 d.dismiss();
    		return false;
		}    
         });
        b2.setOnLongClickListener(new OnLongClickListener()
        {
         
		@Override
		public boolean onLongClick(View v) {
			d.dismiss();
			return false;
		}    
         });
        d.show();
		
	}
	
}
