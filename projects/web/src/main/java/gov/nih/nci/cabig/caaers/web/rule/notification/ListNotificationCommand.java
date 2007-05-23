package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.ReportCalendarTemplateDao;
import gov.nih.nci.cabig.caaers.domain.notification.ReportCalendarTemplate;

import java.util.List;

public class ListNotificationCommand {

	private List<ReportCalendarTemplate> reportCalendarList;
	
	

	public ListNotificationCommand(ReportCalendarTemplateDao rctDao) {
		setReportCalendarTemplateList(rctDao.getAll());
	}



	/**
	 * @return the reportCalendarList
	 */
	public List<ReportCalendarTemplate> getReportCalendarTemplateList() {
		return reportCalendarList;
	}



	/**
	 * @param reportCalendarList the reportCalendarList to set
	 */
	public void setReportCalendarTemplateList(
			List<ReportCalendarTemplate> reportCalendarList) {
		this.reportCalendarList = reportCalendarList;
	}
	
}
