package hotelapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import session.CookieSessionManager;
import session.SessionManager;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SessionManager sessionManager = new CookieSessionManager(req, resp);
		if (sessionManager.userIsSet()) {
			resp.sendRedirect(ServletType.LIST_HOTELS + "");
			return;
		}
		String user = req.getParameter("username");
		String password = req.getParameter("password");
		if (sessionManager.setUser(user, password)) {
			redirectToHome(resp);
			return;
		}
		resp.sendRedirect(ServletType.LOGIN_SERVLET + "");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SessionManager sessionManager = new CookieSessionManager(req, resp);
		if (sessionManager.userIsSet()) {
			redirectToHome(resp);
			return;
		}
		PrintWriter out = resp.getWriter();
		addLoginForm(out);
	}
	
	private void addLoginForm(PrintWriter out) {
		out.println("<form method='POST' action='" + ServletType.LOGIN_SERVLET + "'>");
		out.println("<h3>Sign in</h3>");
		out.println("<h4>Username:</h4>");
		out.println("<input name='username'/>");
		out.println("<h4>Password:</h4>");
		out.println("<input name='password' type='password'/><br/>");
		out.println("<input type='submit' value='Log in'/>");
		out.println("</form>");
	}
	
	private void redirectToHome(HttpServletResponse resp) throws IOException {
		resp.sendRedirect(ServletType.LIST_HOTELS + "");
	}
}
