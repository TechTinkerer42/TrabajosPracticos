package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpSessionManager {
	
	void setHttpParams(HttpServletRequest req, HttpServletResponse resp);
	
	boolean userIsSet();
	
	boolean setUser(String name, String password);
	
	boolean unsetUser(String name);
}
