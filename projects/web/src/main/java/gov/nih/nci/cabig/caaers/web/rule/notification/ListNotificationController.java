package gov.nih.nci.cabig.caaers.web.rule.notification;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;

import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * For now list all the notifications from the notifications table. 
 * 
 * */
public class ListNotificationController extends SimpleFormController {

	private NotificationDao notificationDao;
	
	public ListNotificationController() {
		setCommandClass(ListNotificationCommand.class);
		setBindOnNewForm(true);
		setFormView("rule/notification/list");
		setSuccessView("rule/notification/list");
	}
	
	@Override
	public Object formBackingObject(HttpServletRequest request) {
		return new ListNotificationCommand(notificationDao);
	}

	public NotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}
}
