package gov.nih.nci.cabig.caaers.web.rule.notification;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.ReportCalendarTemplateDao;

import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * For now list all the notifications from the notifications table. 
 * 
 * */
public class ListNotificationController extends SimpleFormController {

	private ReportCalendarTemplateDao reportCalendarTemplateDao;
	
	public ListNotificationController() {
		setCommandClass(ListNotificationCommand.class);
		setBindOnNewForm(true);
		setFormView("rule/notification/list");
		setSuccessView("rule/notification/list");
	}
	
	@Override
	public Object formBackingObject(HttpServletRequest request) {
		return new ListNotificationCommand(reportCalendarTemplateDao);
	}

	/**
	 * @return the reportCalendarTemplateDao
	 */
	public ReportCalendarTemplateDao getReportCalendarTemplateDao() {
		return reportCalendarTemplateDao;
	}

	/**
	 * @param reportCalendarTemplateDao the reportCalendarTemplateDao to set
	 */
	public void setReportCalendarTemplateDao(
			ReportCalendarTemplateDao reportCalendarTemplateDao) {
		this.reportCalendarTemplateDao = reportCalendarTemplateDao;
	}

	
}
