package ar.edu.itba.it.paw.hotel;

import java.sql.SQLException;
import java.util.List;

import ar.edu.itba.it.paw.db.dao.HotelORM;

public class HotelManager {
	
	private HotelORM hotelOrm;
	
	public HotelManager() {
		hotelOrm = new HotelORM();
	}
	
	public List<Hotel> getHotels() {
		try {
			return hotelOrm.getAll();
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Hotel getHotel(int id) {
		try {
			return hotelOrm.get(id);
		} catch (SQLException e) {
			return null;
		}
	}
}
