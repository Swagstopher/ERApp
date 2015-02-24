package org.sjsucmpe131.expenselisting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.sjsucmpe131.erapp.R;
import org.sjsucmpe131.erapp.ReportResult;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVWriter;


public class ThisYear_Activity extends ListActivity {
	private List<ParseObject> expense;
	public Dialog progressDialog;
	
	//class to put task to do in background	
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		
		// Override this method to do custom remote calls
		protected Void doInBackground(Void... params) {
			//get current userId
			ParseUser user = ParseUser.getCurrentUser();
			String userId =user.getObjectId();
			//Log.i("ERApp", "userId "+ userId);	
			
			
			Date year = new Date();	
			year.setMonth(1);
			year.setDate(1);
			year.setHours(0);
			year.setMinutes(0);
			year.setSeconds(1);
			
			Log.i("ERApp", "today date is ");
			Log.d("ADebugTag", "Value: " + year.toString());

			
			// Gets the today list of expense in sorted order
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ExpenseReportObject");
			query.whereEqualTo("UserId",userId );
			query.whereGreaterThan("Date", year);			
			query.orderByDescending("Date");
			Log.i("ERApp", "Query datas result doInBackgroud");	
			try {
				expense = query.find();
			} catch (ParseException e) {	
			}
			return null;			
		}
				
		@Override
		protected void onPreExecute() {
			ThisYear_Activity.this.progressDialog = ProgressDialog.show(ThisYear_Activity.this, "",
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
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThisYear_Activity.this,
							R.layout.view_result_row);
	        if (!expense.isEmpty()) {
	            for (ParseObject expe : expense) {
	            	String strResult = String.valueOf(expe.getDate("Date")).substring(4, 10) + "  " +"$" 
	            			+String.valueOf(expe.get("Amount")) + "  "
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
			ThisYear_Activity.this.progressDialog.dismiss();
			Log.i("ERApp", "Query datas succeed form Object Expense");	
		}
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_result_list);	
		//list the query result in background		
		new RemoteDataTask().execute();
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.this_year_, menu);
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
	
	//class to put task get csv file in background	
	private class ExportCSVTask extends AsyncTask<String, Boolean, Boolean>{
		 	private File file=null;	    
		
			private final ProgressDialog dialog = new ProgressDialog(ThisYear_Activity.this);
			
			@Override
			protected void onPreExecute() {
				this.dialog.setMessage("Exporting database...");
		        this.dialog.show();
				super.onPreExecute();
			}
		
			// Override this method to do custom remote calls
			protected Boolean doInBackground(final String... args){
					
				   
				    File exportDir = new File(Environment.getExternalStorageDirectory(), "");
			        if (!exportDir.exists()) {
			            exportDir.mkdirs();
			        }
			        //write to file   
			        Date today = new Date();
			        String filename = "expense_report_ThisYear_"+String.valueOf(today).substring(4,10)+".csv";
			        file = new File(exportDir, filename);
			        try {         
			            file.createNewFile();
			            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));	
			            String arrStr1[] ={"Date", "Amount", "Category", "Merchant","PayMethod", "Description"};
		                csvWrite.writeNext(arrStr1);
		                 
		                if (!expense.isEmpty()) {
		    	            for (ParseObject expe : expense) {
		    	            	String arrStr[]={String.valueOf(expe.get("Date")).substring(4, 10) + " - " 
		    	            			+String.valueOf(expe.get("Date")).substring(24, 28), 
		    	            			"$" +String.valueOf(expe.get("Amount")),
		    	            			(String) expe.get("Category"),
		    	            			(String) expe.get("Merchant"),
		    	            			(String) expe.get("PayMethod"),
		    	            			(String) expe.get("Description")};		    	 			              
				                csvWrite.writeNext(arrStr);
				              }//end of for loop
		                }//end of if expense not empty                    
			            csvWrite.close();
			            Log.i("ERApp", "Success to write to CSV file");
			            return true;
			        }
			        catch (IOException e){
			            Log.e("ERApp", e.getMessage(), e);
			            return false;
			        }			        			
				}//end of doInBackground
						
			@Override
		     protected void onPostExecute(final Boolean success) {
				super.onPostExecute(success);	 
		         if (this.dialog.isShowing()){
		             this.dialog.dismiss();
		         }
		         if (success){
		        	 Toast.makeText(ThisYear_Activity.this, "Export successful!", Toast.LENGTH_SHORT).show();
		             Log.i("ERApp", "Success to view CSV file");
		            // Intent openfile = new Intent(Intent.ACTION_GET_CONTENT, Uri.parse(file.getAbsolutePath()));
		             Intent openfile = new Intent(Intent.ACTION_VIEW,  Uri.fromFile(file));
		             
		             try {
						startActivity(openfile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Toast.makeText(ThisYear_Activity.this, "You don't have document viewer, please install first. "
								+ "Your report saved under your file manager", Toast.LENGTH_LONG).show();
					}
		         }
		         else {
		             Toast.makeText(ThisYear_Activity.this, "Export failed!", Toast.LENGTH_SHORT).show();
		         }
		     }								
	}//end of class to put task get csv file in background	

	public void generateReport(View view) {
	    Intent intent = new Intent(this, ReportResult.class);
	    ExportCSVTask task=new ExportCSVTask();  //do in back ground to send csv file
	    task.execute();
	    startActivity(intent);

	}
	
}