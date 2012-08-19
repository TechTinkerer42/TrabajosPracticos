package ar.edu.itba.it.paw.hotelapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.Config;
import ar.edu.itba.it.paw.session.HttpSessionManager;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private HttpSessionManager sessionManager;
	
	@Override
	public void init() throws ServletException {
		super.init();
		sessionManager = Config.sessionManager;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = req.getParameter("username");
		String password = req.getParameter("password");
		if (sessionManager.setUser(user, password)) {
			redirectLoggedUser(req, resp);
			return;
		}
		redirectLoggedUser(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String logout = req.getParameter("logout");
		if ("true".equals(logout)) {
			sessionManager.unsetUser();
			resp.sendRedirect(ServletName.LOGIN_SERVLET.addrs);
			return;
		} else if (sessionManager.userIsSet()) {
			redirectLoggedUser(req, resp);
			return;
		}
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}
	
	private void redirectLoggedUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String redirect = ServletName.LIST_HOTELS.addrs;
		String redirectAtt = (String) req.getAttribute("ATT_REDIRECT");
		if (redirectAtt != null) {
			req.removeAttribute("ATT_REDIRECT");
			redirect = redirectAtt;
		}
		resp.sendRedirect(redirect);
	}

}
