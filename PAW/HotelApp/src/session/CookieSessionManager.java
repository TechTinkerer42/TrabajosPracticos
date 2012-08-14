package session;

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
		Cookie cookie = getCookie(COOKIE_NAME);
		if (cookie == null) {
			return false;
		}
		String id = cookie.getValue();
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

	private Cookie getCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}
		return null;
	}

}
