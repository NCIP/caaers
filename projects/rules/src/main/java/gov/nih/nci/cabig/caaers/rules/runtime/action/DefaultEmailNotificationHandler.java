package gov.nih.nci.cabig.caaers.rules.runtime.action;

import gov.nih.nci.cabig.caaers.rules.runtime.RuleContext;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class DefaultEmailNotificationHandler implements NotificationHandler {
	
	private String message;
	
	private String recipients;
	
	private String from;
	
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public void performNotify(ActionContext actionContext, RuleContext ruleContext) {
		System.out.print("Going to invoke the Notification Service");
		
		/**
		 * 1. Where is the Email Service?
		 * 2. What is the Email Content?
		 * 3. 
		 * 4. To whom all email needs to be send?
		 * 5. How many times the eail neds to be send?
		 * */
		
	}

}