package org.sjsucmpe131.parse;

import java.util.Date;
import android.util.Log;


import com.parse.ParseObject;

public class ParseDataSource {
	
	public static final String LOGTAG = "ERApp";	
	ParseObject userObject = new ParseObject("UsersObject");
	ParseObject expenseObject = new ParseObject("ExpenseObject");
		
	//insert data to UsersObject
	public void insertData_Users(String name, String email, String password, boolean adminFlag){
		Log.i(LOGTAG, "Inserted data to Parse Object Users");	
		userObject.put("Name", name);
		userObject.put("Email", email);
		userObject.put("Password", password);
		userObject.put("AdminFlag", adminFlag);
		userObject.saveInBackground();
		
	}

	//insert data to ExpensesObject
	public void insertData_Expenses(Date date, String userId, 
			String category, String payMethod, Double amount, 
			String merchant, String description, String receipt){
		
		Log.i(LOGTAG, "Inserted data to Parse Object Expenses");	
		expenseObject.put("Date", date);
		expenseObject.put("UserId", userId);
		expenseObject.put("Category", category);
		expenseObject.put("PayMethod", payMethod);
		expenseObject.put("Amount", amount);
		
		if(merchant != null){
			expenseObject.put("Merchant", merchant);
		}
		
		if(description != null){
			expenseObject.put("Description", description);
		}
		
		if(receipt != null){
			expenseObject.put("Receipt", receipt);
		}
		
		expenseObject.saveInBackground();  // save to Parse
	}
}

