package sga;

import java.util.LinkedList;
import java.util.List;


public class Semester {

	private String name;
	private List<Course> courses;
	
	public Semester(String name) {
		this(name, new LinkedList<Course>());
	}
	
	public Semester(String name, List<Course> courses) {
		this.courses = courses;
	}
	
	public void add(Course c) {
		courses.add(c);
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return courses.toString() + "\n";
	}
}
