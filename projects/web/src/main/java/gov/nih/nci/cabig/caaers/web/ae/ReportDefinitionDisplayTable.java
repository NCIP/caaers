package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputField;

public class ReportDefinitionDisplayTable {
	
	private String status;
	private boolean required;
	private InputField field;
	
	public ReportDefinitionDisplayTable(String status, boolean required, InputField field) {
		this.status = status;
		this.required = required;
		this.field = field;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean getRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public InputField getField() {
		return field;
	}
	public void setField(InputField field) {
		this.field = field;
	}
	
	
}