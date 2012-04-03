package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

public class EntityErrorMessage {
	
	// type of the entity
	private String klassName;
	
	public String getKlassName() {
		return klassName;
	}

	public void setKlassName(String klassName) {
		this.klassName = klassName;
	}

	// the unique business identifier
	private String businessId;
	
	// list of error messages
	private List<String> messages = new ArrayList<String>();
	

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	public void addMessage(String message){
		this.messages.add(message);
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

}
