package ar.edu.itba.it.paw.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.user.UserManager;

public class HttpRequestSessionManager implements HttpSessionManager {

	private static String ATT_SESSION_USER = "session_id";
	
	private UserManager user = UserManager.getInstance();
	private HttpSession session;

	@Override
	public void setHttpParams(HttpServletRequest req, HttpServletResponse resp) {
		this.session = req.getSession();
	}

	@Override
	public boolean userIsSet() {
		return getUser() != null;
	}

	@Override
	public boolean setUser(String name, String password) {
		if (user.userExists(name, password)) {
			session.setAttribute(ATT_SESSION_USER, name);
			return true;
		}
		return false;
	}

	@Override
	public String getUser() {
		return (String) session.getAttribute(ATT_SESSION_USER);
	}

	@Override
	public boolean unsetUser() {
		session.removeAttribute(ATT_SESSION_USER);
		return true;
	}


}
