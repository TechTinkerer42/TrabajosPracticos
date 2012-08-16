package ar.edu.itba.it.paw.hotelapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.hotelapp.ServletName;
import ar.edu.itba.it.paw.session.CookieSessionManager;

public class LoginCheckFilter implements Filter {

	private CookieSessionManager sessionManager;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		sessionManager = new CookieSessionManager();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		String requestUrl = getFullUrl(httpReq);
		sessionManager.setHttpParams(httpReq, httpResp);
		if (sessionManager.userIsSet() || requestUrl.contains(ServletName.LOGIN_SERVLET.addrs)) {
			chain.doFilter(req, resp);
		} else {
			httpResp.addCookie(new Cookie("redirect", getRedirectUrl(requestUrl)));
			httpResp.sendRedirect(ServletName.LOGIN_SERVLET.addrs);
		}
	}

	private String getFullUrl(HttpServletRequest req) {
		StringBuffer url = req.getRequestURL();
		String query = req.getQueryString();
		if (query != null && !query.isEmpty()) {
			url = url.append("?").append(query);
		}
		return url.toString();
	}
	
	private String getRedirectUrl(String fullUrl) {
		int index = fullUrl.lastIndexOf("/");
		return fullUrl.substring(index);
	}
	
	@Override
	public void destroy() {
		sessionManager = null;
	}
}
