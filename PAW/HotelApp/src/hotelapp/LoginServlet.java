package hotelapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import session.CookieSessionManager;
import session.HttpSessionManager;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSessionManager sessionManager = new CookieSessionManager(req, resp);
		String user = req.getParameter("username");
		String password = req.getParameter("password");
		if (sessionManager.setUser(user, password)) {
			redirect(req, resp);
			return;
		}
		resp.sendRedirect(ServletName.LOGIN_SERVLET + "");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSessionManager sessionManager = new CookieSessionManager(req, resp);
		if (sessionManager.userIsSet()) {
			redirect(req, resp);
			return;
		}
		PrintWriter out = resp.getWriter();
		addLoginForm(out);
	}
	
	private void addLoginForm(PrintWriter out) {
		out.println("<form method='POST' action='" + ServletName.LOGIN_SERVLET + "'>");
		out.println("<h3>Sign in</h3>");
		out.println("<h4>Username:</h4>");
		out.println("<input name='username'/>");
		out.println("<h4>Password:</h4>");
		out.println("<input name='password' type='password'/><br/>");
		out.println("<input type='submit' value='Log in'/>");
		out.println("</form>");
	}
	
	private void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Cookie[] cookies = req.getCookies();
		String redirect = ServletName.LIST_HOTELS.addrs;
		if (cookies != null) {
			for (Cookie cookie: cookies) {
				if ("redirect".equals(cookie.getName())) {
					redirect = cookie.getValue();
					break;
				}
			}
		}
		resp.sendRedirect(redirect);
	}
}
