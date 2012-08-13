package hotelapp;

import hotel.Comment;
import hotel.Hotel;
import hotel.HotelManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewHotel extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String codeString = req.getParameter("code");
		if (codeString == null || codeString.isEmpty()) {
			out.println("<h2>Invalid hotel code</h2>");
			return;
		}
		int code;
		try {
			code = Integer.parseInt(codeString);
		} catch (NumberFormatException e) {
			out.println("<h2>Hotel code must be numeric</h2>");
			return;
		}
		HotelManager hotelManager = HotelManager.getInstance();
		Hotel hotel = hotelManager.getHotel(code);
		if (hotel == null) {
			out.printf("<h2>Hotel %d does not exists</h2>", code);
			return;
		}
		addHotelInfo(out, hotel);
		addComentsArea(out, hotel);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private void addHotelInfo(PrintWriter out, Hotel hotel) {
		out.println("<a href='" + ServletType.LIST_HOTELS + "'><< Volver</a>");
		out.printf("<h2>Hotel %s - %d stars</h2>", hotel.getName(), hotel.getRating());
		out.println("<ul>");
		out.printf("<li>Address: %s</li>", hotel.getAddress());
		out.printf("<li>Price: %s</li>", hotel.getDetails().getFee());
		out.printf("<li>Comments: %s</li>", hotel.getAddress());
		out.println("</ul>");
		out.println("<h4>User comments:</h4>");
		out.println("<table border=\"1\">");
		for (Comment comment : hotel.getDetails().getComments()) {
			out.println("<tr>");
			out.println("<th>" + comment.getAuthor() + "</th>");
			out.println("<th>" + comment.getDetails() + "</th>");
			out.println("</tr>");
		}
		out.println("</table>");
	}
	
	private void addComentsArea(PrintWriter out, Hotel hotel) {
		out.println("<form method='POST' action='" + ServletType.ADD_COMMENT + "'>");
		out.println("<h3>Add your coment!</h3>");
		out.print("<h4>Your name:</h4>");
		out.println("<input name='author'/>");
		out.println("<h4>Your comment:</h4>");
		out.print("<input name='comment'/>");
		out.printf("<input name='code' type='hidden' value='%s'/>", hotel.getCode());
		out.println("<input type='submit' value='Add comment'/>");
		out.println("</form>");
	}
}
