/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api;

import java.util.ArrayList;
import java.util.List;

public class ProcessingOutcome {
    private boolean failed;
	
	// type of the entity
	private String klassName;
    
    private String caaersDBId;
	
	public String getKlassName() {
		return klassName;
	}

	public void setKlassName(String klassName) {
		this.klassName = klassName;
	}

	// the unique business identifier
	private String businessId;

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

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

    public String getCaaersDBId() {
        return caaersDBId;
    }

    public void setCaaersDBId(String caaersDBId) {
        this.caaersDBId = caaersDBId;
    }
}
