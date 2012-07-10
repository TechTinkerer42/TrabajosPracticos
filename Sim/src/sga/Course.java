package sga;

import java.util.List;


public class Course {
	
	private String code;
	private String name;
	private String credits;
	private String reqCredits;
	private List<String> correlCodes;
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCredits(String credits) {
		this.credits = credits;
	}
	
	public String getCredits() {
		return credits;
	}
	
	public void setReqCredits(String reqCredits) {
		this.reqCredits = reqCredits;
	}
	
	public String getReqCredits() {
		return reqCredits;
	}
	
	public void setCorrel(List<String> correlCodes) {
		this.correlCodes = correlCodes;
	}
	
	public List<String> getCorrel() {
		return correlCodes;
	}
	
	@Override
	public String toString() {
		int correl = correlCodes == null ? 0 : correlCodes.size();
		return correl + "{ " + code + ", " + name + ", {" + correlCodes + "}}";
	}
}
