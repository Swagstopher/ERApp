//package org.sjsucmpe131.model;
//import java.util.List;
//
//import android.net.ParseException;
//import android.util.Log;
//
//import com.parse.FindCallback;
//import com.parse.ParseFile;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.ParseUser;
//
///**
// * https://parse.com/docs/android/api/com/parse/ParseUser.html
// * 
// * @author
// * 
// */
//
//public class User extends ParseUser {
//	public User() {
//
//	}
//
//	// users register
//	// user login
//	// user addexpenses
//	/**
//	 * User can submit expense reports. Only completed ones are saved in
//	 * database.
//	 * 
//	 * @param e
//	 * @return
//	 */
//	public boolean submitExpenseReport(ExpenseReportObject e) {
//		boolean isSubmitted = false;
//		if (e.isComplete()) {
//			put("listOfExpenseReports", e);
//			isSubmitted = true;
//		} else {
//			e.saveInBackground();
//			Log.i("ERAPP",
//					"expense report is not completed; User's isComplete will remain false.");
//		}
//		return isSubmitted;
//	}
//
//	/**
//	 * User can view expenses ONLY HE/SHE IS ALLOWED TO SEE.
//	 * Associations
//	 * @return
//	 */
//	public List<ExpenseReportObject> getExpenseReports() {
//		ParseQuery query = ParseQuery.getQuery("ExpenseReport");
//		query.whereEqualTo("username", this.getUsername());
//		List<ExpenseReportObject> listOfUserExpenseReports = null;
//		try {
//			listOfUserExpenseReports = query.find();
//		} catch (com.parse.ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return listOfUserExpenseReports;
//	}
//	
//	public ParseQuery returnQuery_corporate_ExpenseReports() {
//		ParseQuery query = ParseQuery.getQuery("user");
//		query.whereEqualTo("corpName", this.get("corpName"));
//		query.include("listOfExpenseReports");
//		return query;
//	}
//
//	// user export expense
//
//	/**
//	 * User exports by month WARNING. GETMONTH() WILL RETURN IN INTERVALS OF 0 -
//	 * 11
//	 * 
//	 * @param timePeriod
//	 * @return
//	 */
////	public ParseFile exportExpenseReport_byMonth(int aMonth) {
////		ParseQuery query = ParseQuery.getQuery("ExpenseReport");
////		query.whereEqualTo("username", this.getUsername());
////		List<ExpenseReportObject> listOfUserExpenseReports = query.find();
////		for (ExpenseReportObject expenseReport : listOfUserExpenseReports) {
////			if (expenseReport.getCreatedAt().getMonth() == (aMonth - 1)) {
////
////			}
////		}
////		return null;
////
////	}
//
//	/**
//	 * User exports byear
//	 * 
//	 * @param timePeriod
//	 * @return
//	 */
////	public ParseFile exportExpenseReport_byYear(int aYear) {
////		ParseQuery query = ParseQuery.getQuery("ExpenseReport");
////		query.whereEqualTo("username", this.getUsername());
////		List<ExpenseReportObject> listOfUserExpenseReports = query.find();
////		for (ExpenseReportObject expenseReport : listOfUserExpenseReports) {
////			if (expenseReport.getCreatedAt().getYear() == aYear) {
////			}
////		}
////
////	}
//}