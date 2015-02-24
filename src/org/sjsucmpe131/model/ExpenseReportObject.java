package org.sjsucmpe131.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("ExpenseReport")
public class ExpenseReportObject extends ParseObject {
	public ExpenseReportObject() {

	}

	/**
	 * Returns true if submitted Expense Report is Complete meaning it was saved
	 * in background Returns false if EXR is not complete, EXR is ready for
	 * retrieval
	 */
	public void fillExpenseFields(String date, String price, String category,
			String merchant, String payment, String description,
			TouchImageView photoImage) {

		put("Date", date);
		put("Price", price);
		put("Category", category);
		put("Merchant", merchant);
		put("PayMethod", payment);
		put("Description", description);
		// The photo is the optional problem.
		// parse will query if photoimage is null
		if (photoImage != null) {
			put("isComplete", true);
		}
		put("photoImage", photoImage);
	}

	public boolean isComplete() {
		return this.getBoolean("isComplete");
	}

}
