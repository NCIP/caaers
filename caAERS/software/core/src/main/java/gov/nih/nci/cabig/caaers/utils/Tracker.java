package gov.nih.nci.cabig.caaers.utils;

import java.util.Date;

import gov.nih.nci.cabig.caaers.domain.report.ReportTracking;
import gov.nih.nci.cabig.caaers.domain.report.ReportTrackingStatus;

public class Tracker {
	
	private static ReportTrackingStatus getReportTrackingStatus(boolean status,String message,Date now) { 
		ReportTrackingStatus reportTrackingStatus = new ReportTrackingStatus();
		reportTrackingStatus.setStatus(status);
		reportTrackingStatus.setStatusMessage(message);
		reportTrackingStatus.setRecordedTime(now);
		return reportTrackingStatus;
	}
	public static void logInitiation(ReportTracking reportTracking,boolean status,String message,Date now) {		
		reportTracking.setSubmissionInitiated(getReportTrackingStatus(status,message,now));
	}

	public static void logXmlGeneration(ReportTracking reportTracking,boolean status,String message,Date now) {
		reportTracking.setCaaersXMLGenerated(getReportTrackingStatus(status,message,now));
	}
	
	public static void logAttachmentGeneration(ReportTracking reportTracking,boolean status,String message,Date now) {
		reportTracking.setAttachmentGenerated(getReportTrackingStatus(status,message,now));
	}

	public static void logEmailNotification(ReportTracking reportTracking,boolean status,String message,Date now) {
		reportTracking.setEmailNotification(getReportTrackingStatus(status,message,now));
	}
	
	public static void logConnectionToESB(ReportTracking reportTracking,boolean status,String message,Date now) {
		reportTracking.setConnectedToESB(getReportTrackingStatus(status,message,now));
	}
	
	public static void logConnectionToExternalSystem(ReportTracking reportTracking,boolean status,String message,Date now) {
		reportTracking.setConnectedToExternalSystem(getReportTrackingStatus(status,message,now));
	}	
	
	public static void logSubmissionToExternalSystem(ReportTracking reportTracking,boolean status,String message,Date now) {
		reportTracking.setSubmissionToExternalSystem(getReportTrackingStatus(status,message,now));
	}

	public static void logResponseFromExternalSystem(ReportTracking reportTracking,boolean status,String message,Date now) {
		reportTracking.setResponseFromExternalSystem(getReportTrackingStatus(status,message,now));
	}
	
	public static void logEmailNotificationToSubmitter(ReportTracking reportTracking,boolean status,String message,Date now) {
		reportTracking.setNotificationToSubmitter(getReportTrackingStatus(status,message,now));
	}	

}
