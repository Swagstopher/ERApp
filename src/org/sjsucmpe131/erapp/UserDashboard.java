package org.sjsucmpe131.erapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class UserDashboard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_dashboard);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_dashboard, menu);
		return true;
	}
	
	//** Called when the user clicks the View Expense button */
	public void viewScreen(View view) {
	    Intent intent = new Intent(this, ViewExpense.class);
	    //need add some 
	    startActivity(intent);
	}
	
	
	//** Called when the user clicks the  Expense Report button */
	public void reportScreen(View view) {
	    Intent intent = new Intent(this, ExpenseReport.class);
	    //need add some 
	    startActivity(intent);
	}

	//** Called when the user clicks the Add Expense button */
//	public void addExpense(View view) {
//	    Intent intent = new Intent(this, AddExpense.class);
//	    //need add some 
//	    startActivity(intent);
//	}
}
