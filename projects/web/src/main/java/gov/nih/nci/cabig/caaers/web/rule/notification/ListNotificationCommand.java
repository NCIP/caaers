package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.report.ReportCalendarTemplateDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.List;

public class ListNotificationCommand {

	private List<ReportDefinition> reportCalendarList;
	
	

	public ListNotificationCommand(ReportCalendarTemplateDao rctDao) {
		setReportCalendarTemplateList(rctDao.getAll());
	}



	/**
	 * @return the reportCalendarList
	 */
	public List<ReportDefinition> getReportCalendarTemplateList() {
		return reportCalendarList;
	}



	/**
	 * @param reportCalendarList the reportCalendarList to set
	 */
	public void setReportCalendarTemplateList(
			List<ReportDefinition> reportCalendarList) {
		this.reportCalendarList = reportCalendarList;
	}
	
}
