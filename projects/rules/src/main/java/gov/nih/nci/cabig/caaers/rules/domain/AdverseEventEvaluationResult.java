package gov.nih.nci.cabig.caaers.rules.domain;

public class AdverseEventEvaluationResult {
	
	private String message;
	
	public AdverseEventEvaluationResult(){
		this.message="XYZ";
	}
	
	public void setMessage(String message){
		this.message=message;
	}
	public String getMessage(){
		return message;
	}

}
