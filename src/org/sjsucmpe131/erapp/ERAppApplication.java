package org.sjsucmpe131.erapp;

import com.parse.Parse;
import android.app.Application;


public class ERAppApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		//Connect to Parse 
		Parse.initialize(this,"XXxSkO6Agraiwcbbqsy6uj26buORgCVnvHHKycRd",
				"CLNLE4wzAfwPcqL8WT2StLkiS0eBt3JjGjGE0Phw");
	
		//ParseUser.enableAutomaticUser();
		//ParseACL defaultACL = new ParseACL();
		// Optionally enable public read access.
		// defaultACL.setPublicReadAccess(true);
		//ParseACL.setDefaultACL(defaultACL, true);
	}

}
