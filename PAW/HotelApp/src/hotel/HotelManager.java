package hotel;


import java.util.LinkedList;
import java.util.List;

public class HotelManager {

	private static HotelManager instance = new HotelManager();
	
	public static HotelManager getInstance() {
		return instance;
	}
	
	private List<Hotel> hotels;
	
	private HotelManager() {
		hotels = new LinkedList<Hotel>();
		fillWithSomeHotels();
	}
	
	private void fillWithSomeHotels() {
		Hotel h;
		Details hotelDetails;
		h = new Hotel(0, "Hotel 1", "Description 1");
		h.setAddress("A la vuelta de la esquina");
		h.setDesc("El mejor hotel que jamas se halla construido!");
		h.setRating(4);
		hotelDetails = h.getDetails();
		hotelDetails.setFee("u$s100 por dia");
		hotelDetails.addComments("Gonzalo", "La comida era muy rica");
		hotelDetails.addComments("Harry Potter", "La decoracion no me gusto!");
		hotels.add(h);
		h = new Hotel(1, "Hotel 2", "Description 2");
		h.setAddress("Somewhere over the rainbow...");
		hotels.add(h);
		h = new Hotel(2, "Hotel 3", "Description 3");
		hotels.add(h);
	}
	
	public List<Hotel> getHotels() {
		return hotels;
	}
	
	public Hotel getHotel(int code) {
		for (Hotel h: hotels) {
			if (h.getCode() == code) {
				return h;
			}
		}
		return null;
	}
}
