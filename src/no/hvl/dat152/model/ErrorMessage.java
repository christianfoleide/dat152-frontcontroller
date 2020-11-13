package no.hvl.dat152.model;

public class ErrorMessage {

	private int statusCode;
	private String description;
	
	public ErrorMessage() {}
	
	public ErrorMessage withStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	
	public ErrorMessage withDescription(String description) {
		this.description = description;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

}
