package org.sjsucmpe131.erapp;

import java.util.List;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;


public class ViewResultList extends ListActivity  {

	private List<ParseObject> expense;
	public Dialog progressDialog;
	
	//class to put task to do in background
	
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		
		// Override this method to do custom remote calls
		protected Void doInBackground(Void... params) {
			// Gets the current list of expense in sorted order
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ExpenseReportObject");
			query.orderByDescending("_created_at");
			ParseUser user = new ParseUser().getCurrentUser(); 
			query.whereEqualTo("UserId", user.getUsername());
			Log.i("ERApp", "Query datas result doInBackgroud");	
			try {
				expense = query.find();
			} catch (ParseException e) {	
			}
			return null;			
		}
				
		@Override
		protected void onPreExecute() {
			ViewResultList.this.progressDialog = ProgressDialog.show(ViewResultList.this, "",
					"Loading...", true);
			super.onPreExecute();
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
	
		@Override
		protected void onPostExecute(Void result) {
			
			Log.i("ERApp", "onPostExecute");	  
			// Put the list of expense into the list view
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewResultList.this,
			//		R.layout.view_result_row);
					
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewResultList.this,
							R.layout.view_result_row);
	        if (!expense.isEmpty()) {
	            for (ParseObject expe : expense) {
	            	String strResult = String.valueOf(expe.getDate("Date")).substring(0, 10) + "  " 
	            			+String.valueOf(expe.get("Price")) + "  "
	            			+ (String) expe.get("Category") + "  " 
	            			+ (String) expe.get("Merchant") + "  "
	            			+ (String) expe.get("PayMethod") + "  "
	            			+ (String) expe.get("Description");
	                adapter.add(strResult);                
	                Log.i("ERApp", "*****Query datas expense");	                
	            }
	        }
	        else{
	        	Log.i("ERApp", "????Query datas from expense empty");	    
	        }
			setListAdapter(adapter);
	        ViewResultList.this.progressDialog.dismiss();
			Log.i("ERApp", "Query datas succeed form Object Expense");	
		}		
}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_result_list);
		
		//list the query result in background		
		new RemoteDataTask().execute();
		//registerForContextMenu(getListView());	
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_result_list, menu);
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

	//** Called when the user clicks the Other View button */
	
	public void backScreen(View view) {
	    Intent intent = new Intent(this, ViewExpense.class);
	    //need add some 
	    startActivity(intent);
	}

	//** Called when the user clicks the Other View button */
	
	public void generateReport(View view) {
	    Intent intent = new Intent(this, ReportResult.class);
	    //need add some 
	    startActivity(intent);
	}
	

}
