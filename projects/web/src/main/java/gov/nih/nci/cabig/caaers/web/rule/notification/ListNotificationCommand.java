package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.domain.Notification;

import java.util.List;

public class ListNotificationCommand {

	private List<Notification> notifications;
	
	

	public ListNotificationCommand(NotificationDao notificationDao) {
		setNotifications(notificationDao.getAll());
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
}
