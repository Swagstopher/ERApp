package org.sjsucmpe131.erapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

public class IntegratingFacebookTutorialApplication extends Application {

static final String TAG = "MyApp";

@Override
public void onCreate() {
super.onCreate();

Parse.initialize(this, "XXxSkO6Agraiwcbbqsy6uj26buORgCVnvHHKycRd",
"CLNLE4wzAfwPcqL8WT2StLkiS0eBt3JjGjGE0Phw");

// Set your Facebook App Id in strings.xml
//ParseFacebookUtils.initialize(getString(R.string.app_id));

}

}