package ar.edu.itba.it.paw.hotel;

public class Comment {

	private String details;
	private String author;
	
	public Comment(String author, String details) {
		this.author = author;
		this.details = details;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getDetails() {
		return details;
	}
}
