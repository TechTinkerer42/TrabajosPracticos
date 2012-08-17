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
		sessionManager = CookieSessionManager.getInstance();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		String requestUrl = httpReq.getRequestURI();
		if (isStaticRequest(requestUrl)) {	// ignore statis requests
			chain.doFilter(req, resp);
			return;
		}
		if (sessionManager.userIsSet() || requestUrl.contains(ServletName.LOGIN_SERVLET.addrs)) {
			chain.doFilter(req, resp);
		} else {
			// createRedirectCookie(httpReq, httpResp);
			httpResp.sendRedirect(ServletName.LOGIN_SERVLET.addrs);
		}
	}
	
	private boolean isStaticRequest(String url) {
		return url.endsWith(".css") || url.endsWith(".js");
	}
	
	private void createRedirectCookie(HttpServletRequest req, HttpServletResponse resp) {
		StringBuffer url = req.getRequestURL();
		String query = req.getQueryString();
		if (query != null && !query.isEmpty()) {
			url = url.append("?").append(query);
		}		
		resp.addCookie(new Cookie("redirect", url.toString()));
	}
	
	@Override
	public void destroy() {
		sessionManager = null;
	}
}
