package hotel;

public class Hotel {

	private int code, rating;
	private String name, desc;
	private String address;
	private Details details;
	
	public Hotel(int code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
		details = new Details();
	}
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setRating(int reating) {
		this.rating = reating;
	}
	
	public int getRating() {
		return rating;
	}
	
	public Details getDetails() {
		return details;
	}
}
