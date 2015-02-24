package org.sjsucmpe131.erapp;

import org.sjsucmpe131.expenselisting.All_Activity;
import org.sjsucmpe131.expenselisting.ThisMonth_Activity;
import org.sjsucmpe131.expenselisting.Today_Activity;
import org.sjsucmpe131.expenselisting.ThisYear_Activity;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;



@SuppressWarnings("deprecation")
public class ViewExpense extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_expense);
		
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost();
 
		// Today tab
		Intent intentToday = new Intent().setClass(this, Today_Activity.class);
		TabSpec tabSpecToday = tabHost
		  .newTabSpec("Today")
		  .setIndicator("Today") 
		  .setContent(intentToday);


		// This month tab
		Intent intentMonth = new Intent().setClass(this, ThisMonth_Activity.class);
		TabSpec tabSpecMonth = tabHost
		  .newTabSpec("This Month")
		  .setIndicator("This Month", ressources.getDrawable(R.drawable.tab_this_month)) 
		  .setContent(intentMonth);

		// This year tab
		Intent intentYear = new Intent().setClass(this, ThisYear_Activity.class);
		TabSpec tabSpecYear = tabHost
		  .newTabSpec("This Year")
		  .setIndicator("This Year", ressources.getDrawable(R.drawable.tab_this_year)) 
		  .setContent(intentYear);

		// All tab
		Intent intentAll = new Intent().setClass(this, All_Activity.class);
		TabSpec tabSpecAll = tabHost
		  .newTabSpec("All")
		  .setIndicator("All", ressources.getDrawable(R.drawable.tab_all)) 
		  .setContent(intentAll);
				 
		// add all tabs 
		tabHost.addTab(tabSpecToday);
		tabHost.addTab(tabSpecMonth);
		tabHost.addTab(tabSpecYear);
		tabHost.addTab(tabSpecAll);

		//set ThisWeek tab as default (zero based)
		tabHost.setCurrentTab(0);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_expense, menu);
		return true;
	}
	



}
