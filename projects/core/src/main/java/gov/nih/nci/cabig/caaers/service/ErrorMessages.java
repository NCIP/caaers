package gov.nih.nci.cabig.caaers.service;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessages {
	List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
	public List<ErrorMessage> getMessages() {
		return messages;
	}
	public void addErrorMessage(int code, String msg, String property){
		messages.add(new ErrorMessage(code, "Invalid " + msg, property));
	}

	public boolean hasErros(){
		return messages.size() > 0;
	}

	public List<ErrorMessage> messages(){
		return messages;
	}

	@Override
	public String toString() {
		return messages.toString();
	}

}
