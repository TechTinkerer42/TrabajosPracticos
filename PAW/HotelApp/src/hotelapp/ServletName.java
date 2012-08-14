package hotelapp;

public enum ServletName {	
	
	LIST_HOTELS("ListHotels"), 
	VIEW_HOTEL("ViewHotel"), 
	ADD_COMMENT("AddComment"),
	LOGIN_SERVLET("LoginServlet");
	
	public final String addrs;
	
	private ServletName(String name) {
		this.addrs = name;
	}
	
	@Override
	public String toString() {
		return addrs;
	}
}
