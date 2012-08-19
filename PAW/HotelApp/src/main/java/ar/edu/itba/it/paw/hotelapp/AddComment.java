package ar.edu.itba.it.paw.hotelapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.Config;
import ar.edu.itba.it.paw.hotel.Hotel;
import ar.edu.itba.it.paw.hotel.HotelManager;
import ar.edu.itba.it.paw.session.HttpSessionManager;

public class AddComment extends HttpServlet {

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
		String codeString = req.getParameter("code");
		if (codeString == null || codeString.isEmpty()) {
			req.getRequestDispatcher(ServletName.LIST_HOTELS.addrs).forward(
					req, resp);
			return;
		}
		int code;
		try {
			code = Integer.parseInt(codeString);
		} catch (NumberFormatException e) {
			req.getRequestDispatcher(ServletName.LIST_HOTELS.addrs).forward(
					req, resp);
			return;
		}
		String author = sessionManager.getUser();
		if (author == null) {
			author = "null!";
		}
		String comment = req.getParameter("comment");
		addComent(code, author, comment);
		resp.sendRedirect(ServletName.VIEW_HOTEL + "?code=" + code);
	}

	protected void addComent(int id, String author, String comment) {
		HotelManager hotelManager = new HotelManager();
		Hotel hotel = hotelManager.getHotel(id);
		if (hotel == null || author == null || author.isEmpty()
				|| comment == null || comment.isEmpty()) {
			return;
		}
		hotel.getComments().add(author, comment);
	}
}
