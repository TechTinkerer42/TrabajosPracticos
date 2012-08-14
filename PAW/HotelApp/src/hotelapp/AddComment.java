package hotelapp;

import hotel.Hotel;
import hotel.HotelManager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddComment extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String codeString = req.getParameter("code");
		if (codeString == null || codeString.isEmpty()) {
			req.getRequestDispatcher(ServletName.LIST_HOTELS.addrs).forward(req, resp);
			return;
		}
		int code;
		try {
			code = Integer.parseInt(codeString);
		} catch (NumberFormatException e) {
			req.getRequestDispatcher(ServletName.LIST_HOTELS.addrs).forward(req, resp);
			return;
		}
		String author = req.getParameter("author");
		String comment = req.getParameter("comment");
		addComent(code, author, comment);
		resp.sendRedirect(ServletName.VIEW_HOTEL + "?code=" + code);
	}

	protected void addComent(int code, String author, String comment) {
		HotelManager hotelManager = HotelManager.getInstance();
		Hotel hotel = hotelManager.getHotel(code);
		if (hotel == null || author == null || author.isEmpty() || comment == null || comment.isEmpty()) {
			return;
		}
		hotel.getDetails().addComments(author, comment);
	}
}
