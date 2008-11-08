package gov.nih.nci.cabig.caaers.domain.workflow;

public class NotificationRecipient {
	
	private int type;//ROLE or USER //TODO : should use a common enum 
	
	private String name; //eg: user1 (login id) or (StudyPI - a role)
}
