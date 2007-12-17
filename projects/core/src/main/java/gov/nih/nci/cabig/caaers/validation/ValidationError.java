package gov.nih.nci.cabig.caaers.validation;

public class ValidationError {
	private String code;
	private String message;
	
	public ValidationError(){
		this("0","");
	}
	public ValidationError(String code, String msg) {
		this.code = code;
		this.message = msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "code :" + code + ", message :" + message;
	}
}
