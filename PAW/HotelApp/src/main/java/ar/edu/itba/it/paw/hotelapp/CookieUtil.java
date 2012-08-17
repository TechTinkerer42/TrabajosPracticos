package ar.edu.itba.it.paw.hotelapp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static String getCookieValue(Cookie[] cookies, String name) {
		Cookie c = getCookie(cookies, name);
		if (c != null) {
			return c.getValue();
		}
		return null;
	}
	
	public static Cookie getCookie(Cookie[] cookies, String name) {
		if (cookies == null) {
			return null;
		}
		for (Cookie c: cookies) {
			if (name.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}
	
	public static String deleteCookie(Cookie[] cookies, String name, HttpServletResponse resp) {
		if (cookies == null) {
			return null;
		}
		for (Cookie c: cookies) {
			if (name.equals(c.getName())) {
				Cookie other = new Cookie(name, "");
				other.setMaxAge(0);
				String value = c.getValue();
				resp.addCookie(other);
				return value;
			}
		}
		return null;
	}
	
}
