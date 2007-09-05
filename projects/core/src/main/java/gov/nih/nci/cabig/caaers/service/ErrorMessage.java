package gov.nih.nci.cabig.caaers.service;

public class ErrorMessage {
	private int code;
	private String message;
	private String property;

	public ErrorMessage(int code, String message, String property){
		this.code = code;
		this.message = message;
		this.property = property;
	}

	@Override
	public String toString() {
		return code + ", " + property + ", " + message;
	}

	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public String getProperty() {
		return property;
	}
}
