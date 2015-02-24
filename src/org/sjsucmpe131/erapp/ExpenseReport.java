package org.sjsucmpe131.erapp;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
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
import android.widget.Spinner;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVWriter;

public class ExpenseReport extends Activity {
	
	 private Spinner spinner;
	 private String period;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_report);
	}	  
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_report, menu);
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
			
	//** Called when the user clicks the button Get Report */		
		public void Export(View view) {
		    Intent intent = new Intent(this, ReportResult.class);		    
			spinner = (Spinner) findViewById(R.id.spinner1);				    
		    period = spinner.getSelectedItem().toString();
		    Log.i("ERApp", "Spinner Item selected " + period);
		    ExportCSVTask task=new ExportCSVTask();  //do in background to send csv file
		    task.execute();		    		    	    
		    startActivity(intent);
		}
				
		//class to put task get csv file in background	
		private class ExportCSVTask extends AsyncTask<String, Boolean, Boolean>{
			    private List<ParseObject> expense;
			 	private File file=null;	    			
				private final ProgressDialog dialog = new ProgressDialog(ExpenseReport.this);
				
				@Override
				protected void onPreExecute() {
					this.dialog.setMessage("Exporting database...");
			        this.dialog.show();
					super.onPreExecute();
				}
			
				// Override this method to do custom remote calls
				protected Boolean doInBackground(final String... args){	
					
					ParseUser user = ParseUser.getCurrentUser();
					String userId =user.getObjectId();
					//Log.i("ERApp", "userId "+ userId);	
					
					Date month = new Date();
					//Log.d("ADebugTag", "Value: " + month.toString());
					int m =month.getMonth()+1; //current month					
					month.setDate(1);
					month.setHours(0);
					month.setMinutes(0);
					month.setSeconds(1);  //month is current month 

					Date year= new Date();
					year.setMonth(0);
					year.setDate(1);
					year.setHours(0);
					year.setMinutes(0);
					year.setSeconds(1);  //month is current month with 000				
					
					Date selection = new Date(); 
					int n= (int)spinner.getSelectedItemId(); 
					switch (n) {	
					//case if period is 1 Month
					case 0:
						month.setMonth(m-1-n);	
						selection = month;
						break;

					//case if period is 2 Month
					case 1:
						month.setMonth(m-1-n);	
						selection = month;
						break;

					//case if period is 3 Month
					case 2:
						month.setMonth(m-1-n);	
						selection = month;
						break;
						
						//case if period is 4 Month
					case 3:
						month.setMonth(m-1-n);	
						selection = month;
						break;
						
						//case if period is 5 Month
					case 4:
						month.setMonth(m-1-n);	
						selection = month;
						Log.d("ADebugTag", "select: " + selection);	
						break;
						
						//case if period is 6 Month
					case 5:
						month.setMonth(m-1-n);	
						selection = month;
						break;
						
						//case if period is 1 Year
					case 6:
						selection = year;
						Log.d("ADebugTag", "select 1 year: " + selection);							
						break;
						
						//case if period is 2 Year
					case 7:
						year.setYear(year.getYear()-1);
						selection =year;
						Log.d("ADebugTag", "select 2 year: " + selection);		
						break;					
					} 
					Log.d("ADebugTag", "selection: " + selection.toString());
					
					//query expense according to selected period
					ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ExpenseReportObject");
					query.whereEqualTo("UserId",userId );
					query.whereGreaterThan("Date", selection);					
					query.orderByDescending("Date");
					Log.i("ERApp", "Query datas result doInBackgroud");	
					try {
						expense = query.find();
					} catch (ParseException e) {	
					}
										
					 //get file dir
					  File exportDir = new File(Environment.getExternalStorageDirectory(), "");
				      if (!exportDir.exists()) {
				           exportDir.mkdirs();
				      }
				        //write to file  
				      Date today = new Date();
				      String filename = "expense_report_"+ period +"_" +String.valueOf(today).substring(4,10)+".csv";
				      file = new File(exportDir, filename);
				      try {         
				          file.createNewFile();
				          CSVWriter csvWrite = new CSVWriter(new FileWriter(file));	
				          String arrStr1[] ={"Date", "Amount", "Category", "Merchant","PayMethod", "Description"};
			              csvWrite.writeNext(arrStr1);			                 
			              if (!expense.isEmpty()) {
			    	         for (ParseObject expe : expense) {
			    	        	  String arrStr[]={String.valueOf(expe.get("Date")).substring(4, 10) + " - " 
			    	        			+String.valueOf(expe.get("Date")).substring(30, 34), 
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
			        	 Toast.makeText(ExpenseReport.this, "Export successful!", Toast.LENGTH_SHORT).show();
			             Log.i("ERApp", "Success to view CSV file");
			            // Intent openfile = new Intent(Intent.ACTION_GET_CONTENT, Uri.parse(file.getAbsolutePath()));
			             Intent openfile = new Intent(Intent.ACTION_VIEW,  Uri.fromFile(file));			             			             
			             try {
							startActivity(openfile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							Toast.makeText(ExpenseReport.this, "You don't have document viewer, please install first. "
									+ "Your report saved under your file manager",Toast.LENGTH_LONG).show();
						}
			         }
			         else {
			             Toast.makeText(ExpenseReport.this, "Export failed!", Toast.LENGTH_SHORT).show();
			         }
			     }//end of onPostExecute							
		}//end of class to put task get csv file in background		

}
