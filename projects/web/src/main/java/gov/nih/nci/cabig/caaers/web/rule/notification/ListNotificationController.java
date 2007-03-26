package gov.nih.nci.cabig.caaers.web.rule.notification;

import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * For now list all the notifications from the notifications table. 
 * 
 * */
public class ListNotificationController extends SimpleFormController {

	public ListNotificationController() {
		setCommandClass(ListNotificationCommand.class);
		setBindOnNewForm(true);
		setFormView("rule/notification/list");
		setSuccessView("rule/notification/list");
	}
}
