package org.sjsucmpe131.erapp;

import junit.framework.TestCase;

import org.junit.Test;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRole;
import com.parse.ParseUser;

public class ERApp_Tests extends TestCase {
	@Test
	public void testUserLoad() {
//		for (int i = 0; i < 1000; i++) {
//			ParseUser user = new ParseUser();
//			user.setUsername("" + i);
//			user.setPassword("");
//
//			try {
//				user.signUp();
//				user.saveInBackground();
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		try {
			int num = 1000;
			assertEquals(query.count() >= num, true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void testCorpLoad() throws ParseException {
//		for (int i = 0; i < 10; i++) {
//			ParseRole corp = new ParseRole("Corp" + i);
//			for (int j = 0; j < 100; j++) {
//				ParseUser user = new ParseUser();
//				user.setUsername("corpUser" + j);
//				user.setPassword("");
//				user.saveInBackground();
//				corp.getUsers().add(user);
//				try {
//					user.signUp();
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//			corp.saveInBackground();
//		}

	
		ParseQuery<ParseRole> query = ParseRole.getQuery();
		try {
			int num = 10;
			assertEquals(query.count() == num, true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
