package ar.edu.itba.it.paw.hotel;

import java.util.LinkedList;
import java.util.List;

public class Comments {

	private List<Comment> comments;

	public Comments() {
		comments = new LinkedList<Comment>();
	}
	
	public List<Comment> getAll() {
		return comments;
	}
	
	public void add(String author, String comment) {
		comments.add(new Comment(author, comment));
	}
}
