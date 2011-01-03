package net.googlemaps.learn;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainMenu extends Activity{
	
	LinearLayout linearLayout;
	ListView listView;
	SharedPreferences preferences;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] mainMenuOptions = new String[]{"Get Current Location","View Previous Locations"};
       
        linearLayout = new LinearLayout(this);        
        setContentView(linearLayout);
        listView = new ListView(this);
        LinearLayout.LayoutParams listViewLayoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT );
        linearLayout.addView(listView,listViewLayoutParams);
        
        ArrayAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mainMenuOptions);
        listView.setAdapter(listAdapter);
        listView.setFocusableInTouchMode(true);        
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
        	
        		String selectedOption = listView.getItemAtPosition(position).toString();
        		if(selectedOption.equals("Get Current Location"))
        		{
        			Intent myIntent = new Intent(v.getContext(), Gmap.class);
					startActivityForResult(myIntent, 0);      			
        		}
        		else if(selectedOption.equals("View Previous Locations"))
        		{
        			Intent myIntent = new Intent(v.getContext(), Gmap.class);
					startActivityForResult(myIntent, 0);
        		}       	
        	 }	
        });
        
        
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
   

    }
	
    
}
