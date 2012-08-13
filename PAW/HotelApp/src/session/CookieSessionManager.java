package session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserManager;

public class CookieSessionManager implements SessionManager {

	private static String COOKIE_NAME = "session_id";

	private UserManager userManager = UserManager.getInstance();
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CookieSessionManager(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
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
			setCookie(COOKIE_NAME, id);
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

	private void setCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		response.addCookie(cookie);
	}
}
