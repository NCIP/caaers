package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.domain.Notification;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class NotificationCommand implements RuleInputCommand {

	private List<String> allRoles;
	
	private Map map;
	
	private String type;
	
	private NotificationDao notificationDao;
	
	public NotificationCommand(List<String> roleList, Map map, NotificationDao notificationDao) {
		this.allRoles = roleList;
		this.map = map;
		setNotificationDao(notificationDao);
	}
	
	private List<String> selectedRoles;
	
	private String name;
	
	private String to;
		
	private String subject;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
	
	public void save() {
		try {
			this.to = this.to.replace(',', ';');
			if(!this.to.endsWith(";")) {
				this.to = this.to + ";";
			}
			for(int i = 0; i < selectedRoles.size(); i++) {
				List<String> emails = (List<String>)map.get(selectedRoles.get(i));
				for(int j = 0; j < emails.size(); j++) {
					this.to = this.to + emails.get(j) + ";";
				}
			}
			Notification notification = new Notification();
			notification.setEmail(this.to);
			notification.setContent(getMessage());
			notification.setSubject(getSubject());
			notification.setName(getName());
			getNotificationDao().save(notification);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<String> roles) {
		this.selectedRoles = roles;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public NotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
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
}