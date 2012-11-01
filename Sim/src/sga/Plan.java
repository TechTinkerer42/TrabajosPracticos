package sga;

import java.util.List;

public class Plan {
	
	private String name, focalization;
	private List<Semester> basicSemesters;
	private List<Semester> professionalSemesters;
	private List<Semester> electives;
	
	public Plan(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBasicSemesters(List<Semester> basicSemesters) {
		this.basicSemesters = basicSemesters;
	}
	
	public List<Semester> getBasicSemesters() {
		return basicSemesters;
	}
	
	public void setProfessionalSemesters(List<Semester> professionalSemesters) {
		this.professionalSemesters = professionalSemesters;
	}
	
	public List<Semester> getProfessionalSemesters() {
		return professionalSemesters;
	}
	
	public void setFocalization(String focalization) {
		this.focalization = focalization;
	}
	
	public String getFocalization() {
		return focalization;
	}
	
	public List<Semester> getElectives() {
		return electives;
	}
	
	public void setElectives(List<Semester> electives) {
		this.electives = electives;
	}
}
