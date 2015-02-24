package org.sjsucmpe131.erapp;
import org.sjsucmpe131.erapp.R;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	/** Called when the activity is first created. */		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//** Called when the user clicks the Log In button */
	public void logIn(View view) {
	    final Intent intent = new Intent(this, UserDashboard.class);
	    final AlertDialog.Builder builder = new AlertDialog.Builder(this);	
	        
	    EditText editText = (EditText) findViewById(R.id.edit_Name);
	    String name = editText.getText().toString();
	    
	    editText = (EditText) findViewById(R.id.edit_Password);
	    String password = editText.getText().toString();
	    
	   
	    ParseUser.logInInBackground(name, password, new LogInCallback() {
	    	  public void done(ParseUser user, ParseException e) {
	    		if (user != null) {
	    	    	Log.i("ERApp", "signIn succeed In Backgroud");	    		    
	    		    startActivity(intent);
	    	    }
	    	//    else if (user == null) {
	    	//  }
	    	    else {
	    	    	Log.i("ERApp", "signIn not succeed In Backgroud");
	    	    	
					 //will pop the Alert Dialog    					 
		  	    	 builder.setMessage(R.string.dialog_signIn_error)
		    		.setTitle(R.string.dialog_title_error)
		    		.setPositiveButton("OK",new DialogInterface.OnClickListener() {
		    			public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, let user sing In again
		    		    //????? need clean up the value of user input before
		    			}
		    		});		
		  	    	AlertDialog dialog = builder.create();
			    	dialog.show();	 		    	   	
	    	    }//end of else
	    	  }
	    	});
	}
	
	//** Called when the user clicks the Register button */
	public void Register(View view) {
	    Intent intent = new Intent(this, UserRegister.class);
	    //here need some code to add user email and password to database
	    startActivity(intent);
	}

}
