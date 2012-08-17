package ar.edu.itba.it.paw.user;

import java.util.HashMap;
import java.util.Map;

public class User {

	private static User instance = new User();

	public static User getInstance() {
		return instance;
	}

	private Map<String, UserInfo> allUsers;

	private User() {
		allUsers = new HashMap<String, UserInfo>();
		addUser("admin", "admin");
		addUser("gonzalo", "mypassword");
		addUser("asd", "asd");
	}
	
	public void addUser(String username, String passwd) {
		if (allUsers.get(username) != null) {
			throw new IllegalArgumentException("Username is already in use");
		}
		allUsers.put(username, new UserInfo(passwd));
	}

	public void removeUser(String username) {
		allUsers.remove(username);
	}

	public boolean userExists(String username, String passwd) {
		UserInfo user = allUsers.get(username);
		if (user == null || !user.passwd.equals(passwd)) {
			return false;
		}
		return true;
	}
	
	private static class UserInfo {

		String passwd;

		public UserInfo(String passwd) {
			this.passwd = passwd;
		}

	}
}
