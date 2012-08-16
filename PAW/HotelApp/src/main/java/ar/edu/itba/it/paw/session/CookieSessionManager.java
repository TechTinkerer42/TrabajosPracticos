package ar.edu.itba.it.paw.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.hotelapp.CookieUtil;
import ar.edu.itba.it.paw.user.UserManager;

public class CookieSessionManager implements HttpSessionManager {

	private static String COOKIE_NAME = "session_id";
	
	private static CookieSessionManager instance = new CookieSessionManager();
	
	public static CookieSessionManager getInstance() {
		return instance;
	}
	
	private UserManager userManager = UserManager.getInstance();
	private HttpServletRequest request;
	private HttpServletResponse response;

	private CookieSessionManager() {
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
	
	public String getUser() {
		String id = CookieUtil.getCookieValue(request.getCookies(), COOKIE_NAME);
		return (id == null) ? null : userManager.getUserName(id);
	}

	@Override
	public boolean unsetUser() {
		String id = CookieUtil.deleteCookie(request.getCookies(), COOKIE_NAME, response);
		if (id == null) {
			return false;
		}
		return userManager.logout(id);
	}

}
