package ar.edu.itba.it.paw.user;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}

	private Map<String, UserInfo> allUsers;

	private UserManager() {
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
	
	public int getUserId(String username) {
		UserInfo userInfo = allUsers.get(username);
		if (userInfo == null) {
			return -1;
		}
		return userInfo.id;
	}
	
	private static class UserInfo {
		private static int next_id = 1;
		
		int id;
		String passwd;

		public UserInfo(String passwd) {
			id = next_id++;
			this.passwd = passwd;
		}

	}
}
