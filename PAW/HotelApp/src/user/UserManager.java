package user;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

	private static UserManager instance = new UserManager();
	private static SessionIdentifierGenerator sessionGen = new SessionIdentifierGenerator();

	// For fast checking against session ids.
	// SessionId -> Username
	private Map<String, String> loggedUsersById;

	// For fast checking against new users trying to login.
	// Username -> SessionId
	private Map<String, String> loggedUsersByUsername;

	public static UserManager getInstance() {
		return instance;
	}

	private UserManager() {
		loggedUsersById = new HashMap<String, String>();
		loggedUsersByUsername = new HashMap<String, String>();
	}

	public String login(String username, String passwd) {
		String loggedInId = loggedUsersByUsername.get(username); 
		if (loggedInId != null) {
			return loggedInId;
		}
		User user = User.getInstance();
		if (user.userExists(username, passwd)) {
			String id = sessionGen.nextSessionId();
			loggedUsersById.put(id, username);
			loggedUsersByUsername.put(username, id);
			return id;
		}
		return null;
	}

	public boolean logout(String id) {
		String username = loggedUsersById.remove(id);
		if (username != null) {
			loggedUsersByUsername.remove(username);
			return true;
		}
		return false;
	}

	public boolean isLoggedIn(String id) {
		return loggedUsersById.get(id) != null;
	}
	
	public String getUserName(String id) {
		return loggedUsersById.get(id);
	}
}
