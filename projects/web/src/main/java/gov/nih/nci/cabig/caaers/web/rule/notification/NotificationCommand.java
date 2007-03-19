package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.List;


/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class NotificationCommand implements RuleInputCommand {

	private String name;
	
	private String type;
	
	private List<String> toUsers;
	
	private List<String> toGroups;
	
	private String to;
	
	private String from;
	
	private String subject;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getToGroups() {
		return toGroups;
	}

	public void setToGroups(List<String> toGroups) {
		this.toGroups = toGroups;
	}

	public List<String> getToUsers() {
		return toUsers;
	}

	public void setToUsers(List<String> toUsers) {
		this.toUsers = toUsers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
}