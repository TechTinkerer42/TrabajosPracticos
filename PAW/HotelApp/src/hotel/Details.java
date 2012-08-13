package hotel;

import java.util.LinkedList;
import java.util.List;

public class Details {

	private String schedule;
	private int rooms;
	private String fee;
	private List<Comment> comments;

	public Details() {
		comments = new LinkedList<Comment>();
	}
	
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	public String getSchedule() {
		return schedule;
	}
	
	public int getRooms() {
		return rooms;
	}
	
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	
	public void setFee(String fee) {
		this.fee = fee;
	}
	
	public String getFee() {
		return fee;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void addComments(String author, String comment) {
		comments.add(new Comment(author, comment));
	}
}
