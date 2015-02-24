package org.sjsucmpe131.model;

import java.util.HashMap;
import java.util.Map;

import com.parse.ParseObject;
import com.parse.ParseRole;
import com.parse.ParseUser;

public class AccountManager {
	ParseRole individual_user = new ParseRole("individual_user");
	ParseRole corp_admin_user = new ParseRole("corp_admin_user");
	ParseRole corp_employee_user = new ParseRole("corp_employee_user");

	Map corpNamesANDcorp = new HashMap<String, ParseUser>();

	Map corpsAndUsers = new HashMap<String, ParseUser>();

	public AccountManager() {
	}

	public void setIndividualUserRole(ParseUser user) {
		individual_user.getUsers().add(user);
	}

	public void setCorpAdminRole(ParseUser user) {
		corp_admin_user.getUsers().add(user);
	}

	public void setCorpEmployeeRole(ParseUser user) {
		corp_employee_user.getUsers().add(user);
	}

	public void setCorporateAssociation(String corpName, ParseUser user) {
		ParseObject corporationUserList = new ParseObject("corporationList");
		corporationUserList.put(corpName, user);
	}
}
