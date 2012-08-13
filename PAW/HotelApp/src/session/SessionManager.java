package session;

public interface SessionManager {

	boolean userIsSet();
	
	boolean setUser(String name, String password);
}
