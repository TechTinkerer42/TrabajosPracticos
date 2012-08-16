package ar.edu.itba.it.paw.hotelapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.session.CookieSessionManager;

public class SessionUpdaterFilter implements Filter {

	private CookieSessionManager sessionManager;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		sessionManager = new CookieSessionManager();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		sessionManager.setHttpParams(httpReq, httpResp);
		chain.doFilter(req, resp);
	}
	
	@Override
	public void destroy() {
		sessionManager = null;
	}

}
