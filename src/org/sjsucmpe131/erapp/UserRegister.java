package org.sjsucmpe131.erapp;

import org.sjsucmpe131.model.AccountManager;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class UserRegister extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_register);
	}

	
	/**
	 * Assigns a functionality to a AccountType button.
	 * 
	 * */
	public void onClick_AccountType(String buttonName, ParseUser user) {
		AccountManager accountManager = new AccountManager();
		
		if (buttonName.equals("Individual")) {
			accountManager.setIndividualUserRole(user);	
		} else if (buttonName.equals("Corporate Administrator")) {
			accountManager.setCorpAdminRole(user);
		} else {//Corp Employee
			accountManager.setCorpEmployeeRole(user);
		}
	}
	
	
	// ** Called when the user clicks the submit button */
	public void submit(View view) {
		final Intent intent = new Intent(this, UserDashboard.class);
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);

		EditText editText = (EditText) findViewById(R.id.edit_Name);
		String name = editText.getText().toString();

		editText = (EditText) findViewById(R.id.edit_Email);
		String email = editText.getText().toString();

		editText = (EditText) findViewById(R.id.edit_Password);
		String password = editText.getText().toString();

		editText = (EditText) findViewById(R.id.edit_Password2);
		String password2 = editText.getText().toString();

		if (password.equals(password2) && name != null && !name.isEmpty()
				&& password != null && !password.isEmpty() && email != null
				&& !email.isEmpty()) {
			// Use ParseUser to signUp for new users
			ParseUser user = new ParseUser();
			user.setUsername(name);
			user.setPassword(password);
			user.setEmail(email);
			user.put("adminFlag", false);
			user.signUpInBackground(new SignUpCallback() {
				@Override
				public void done(com.parse.ParseException e) {
					if (e == null) {
						startActivity(intent);
						Log.i("ERApp", "signUp In Backgroud succeed");
					} else {
						// will pop the Alert Dialog
						Log.i("ERApp", "signUp In Backgroud did not succeed");

						builder.setMessage(R.string.dialog_signUp_error)
								.setTitle(R.string.dialog_title_error)
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												// if this button is clicked,
												// let user sing up again
											}
										});
						AlertDialog dialog = builder.create();
						dialog.show();

					} // end of else
				}
			});// end of signUpInBackground
		} // end of if
		else if (password.isEmpty() || name.isEmpty() || email.isEmpty()) {
			// will pop the Alert Dialog
			Log.i("ERApp", "signUp with empty input");
			builder.setMessage(R.string.dialog_input_empty)
					.setTitle(R.string.dialog_title_error)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// if this button is clicked, close current
									// activity
								}
							});
			AlertDialog dialog = builder.create();
			dialog.show();

		}

		else {
			// will pop the Alert Dialog
			builder.setMessage(R.string.dialog_passwordNotMatch)
					.setTitle(R.string.dialog_title_error)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// if this button is clicked, close current
									// activity
									// MainActivity.this.finish();
								}
							});
			AlertDialog dialog = builder.create();
			dialog.show();
		} // end of else

	} // end of submit

	// ** Called when the user clicks the submit button */
	public void back(View view) {
		final Intent intent = new Intent(this, UserDashboard.class);
		startActivity(intent);
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

}