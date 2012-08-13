package hotelapp;

public enum ServletType {	
	
	LIST_HOTELS("ListHotels"), 
	VIEW_HOTEL("ViewHotel"), 
	ADD_COMMENT("AddComment"),
	LOGIN_SERVLET("LoginServlet");
	
	private final String address;
	
	private ServletType(String name) {
		this.address = name;
	}
	
	@Override
	public String toString() {
		return address;
	}
}
