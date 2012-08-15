package session;

import hotelapp.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserManager;

public class CookieSessionManager implements HttpSessionManager {

	private static String COOKIE_NAME = "session_id";

	private UserManager userManager = UserManager.getInstance();
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CookieSessionManager() {
	}

	public CookieSessionManager(HttpServletRequest req, HttpServletResponse resp) {
		setHttpParams(req, resp);
	}
	
	@Override
	public void setHttpParams(HttpServletRequest req, HttpServletResponse resp) {
		this.request = req;
		this.response = resp;
	}	
	
	@Override
	public boolean userIsSet() {
		String id = CookieUtil.getCookieValue(request.getCookies(), COOKIE_NAME);
		if (id == null) {
			return false;
		}
		return userManager.isLoggedIn(id);
	}

	@Override
	public boolean setUser(String name, String password) {
		String id = userManager.login(name, password);
		if (id != null) {
			response.addCookie(new Cookie(COOKIE_NAME, id));
			return true;
		}
		return false;
	}

	@Override
	public boolean unsetUser(String name) {
		String id = CookieUtil.getCookieValue(request.getCookies(), COOKIE_NAME);
		if (id == null) {
			return false;
		}
		return userManager.logout(id);
	}

}
