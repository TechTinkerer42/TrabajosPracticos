package hotelapp.filter;

import hotelapp.ServletName;

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

import session.CookieSessionManager;

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
		String requestUrl = httpReq.getRequestURL().toString();
		sessionManager.setHttpParams(httpReq, httpResp);

		if (sessionManager.userIsSet() || requestUrl.contains(ServletName.LOGIN_SERVLET.addrs)) {
			chain.doFilter(req, resp);
		} else {
			httpResp.addCookie(new Cookie("redirect", getRedirectUrl(requestUrl)));
			httpResp.sendRedirect(ServletName.LOGIN_SERVLET.addrs);
		}
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
