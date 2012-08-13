package hotelapp;

import hotel.Hotel;
import hotel.HotelManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListHotels extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<table border=\"1\">");
		HotelManager hotelManager = HotelManager.getInstance();
		for (Hotel hotel : hotelManager.getHotels()) {
			out.println("<tr>");
			out.println("<th>" + buildHotelLink(hotel) + "</th>");
			out.println("<th>" + hotel.getDesc() + "</th>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

	private String buildHotelLink(Hotel hotel) {
		int code = hotel.getCode();
		String desc = hotel.getDesc();
		return "<a href='" + ServletType.VIEW_HOTEL + "?code=" + code + "'>"
				+ desc + "</a>";
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}