package gov.nih.nci.cabig.caaers.scheduler.dummy;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;


public class ReportSchedulerService {
	private static ReportSchedulerService sercive = new ReportSchedulerService();
	
	private static int count = 0; 
	
	public ReportStatus getReportScheduleStatus(int reportScheduleID){
		if(count == 10 )
			return ReportStatus.COMPLETED;
		count++;
		return ReportStatus.PENDING;
	}
	public static ReportSchedulerService getInstance(){
		return sercive;
	}
}
