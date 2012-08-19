package ar.edu.itba.it.paw.hotelapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.Config;
import ar.edu.itba.it.paw.hotel.Hotel;
import ar.edu.itba.it.paw.hotel.HotelManager;
import ar.edu.itba.it.paw.session.HttpSessionManager;

public class ListHotels extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HttpSessionManager sessionManager;

	@Override
	public void init() throws ServletException {
		super.init();
		sessionManager = Config.sessionManager;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h2>Welcome " + sessionManager.getUser() + "!</h2>");
		addHotelList(out);
		String logoutUrl = ServletName.LOGIN_SERVLET + "?logout=true";
		out.println("<a href='" + logoutUrl + "'>Log out</a>");
		out.println("</body>");
		out.println("</html>");
	}
	
	private void addHotelList(PrintWriter out) {
		out.println("<table border=\"1\">");
		HotelManager hotelManager = new HotelManager();
		for (Hotel hotel : hotelManager.getHotels()) {
			out.println("<tr>");
			out.println("<th>" + buildHotelLink(hotel) + "</th>");
			out.println("<th>" + hotel.getDescription() + "</th>");
			out.println("</tr>");
		}
		out.println("</table>");
	}

	private String buildHotelLink(Hotel hotel) {
		int code = hotel.getCode();
		String name = hotel.getName();
		return "<a href='" + ServletName.VIEW_HOTEL + "?code=" + code + "'>"
				+ name + "</a>";
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	public String getHola() {
		return "Hola";
	}

}