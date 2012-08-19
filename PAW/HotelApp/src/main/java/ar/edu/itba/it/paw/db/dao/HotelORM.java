package ar.edu.itba.it.paw.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.db.DBConnection;
import ar.edu.itba.it.paw.hotel.Hotel;

public class HotelORM {

	private Connection connection = DBConnection.getInstance().get();

	public Hotel get(int id) throws SQLException {
		Statement select = connection.createStatement();
		ResultSet result = select.executeQuery(
			"Select name, address, price, description, rating "
			+ "FROM Hotel where id = " + id);
		if (result.next()) {
			String name = result.getString(1);
			String address = result.getString(2);
			float price = result.getFloat(3);
			String description = result.getString(4);
			int rating = result.getInt(5);
			Hotel h = new Hotel();
			h.setName(name);
			h.setAddress(address);
			h.setPrice(price);
			h.setDescription(description);
			h.setRating(rating);
			return h;
		}
		return null;
	}
	
	public List<Hotel> getAll() throws SQLException {
		List<Hotel> hotels =  new LinkedList<Hotel>();
		Statement select = connection.createStatement();
		ResultSet result = select.executeQuery(
			"Select id, name, address, price, description, rating "
			+ "FROM Hotel");
		while (result.next()) {
			int id = result.getInt(1);
			String name = result.getString(2);
			String address = result.getString(3);
			float price = result.getFloat(4);
			String description = result.getString(5);
			int rating = result.getInt(6);
			Hotel h = new Hotel();
			h.setId(id);
			h.setName(name);
			h.setAddress(address);
			h.setPrice(price);
			h.setDescription(description);
			h.setRating(rating);
			hotels.add(h);
		}
		return hotels;
	}
}
