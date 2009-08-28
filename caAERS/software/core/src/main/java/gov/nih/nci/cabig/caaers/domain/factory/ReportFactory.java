package gov.nih.nci.cabig.caaers.domain.factory;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.service.FreeMarkerService;
import gov.nih.nci.cabig.caaers.utils.RoleUtils;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Biju Joseph
 */
public class ReportFactory {
	private NowFactory nowFactory;
    
    public Report createReport(final ReportDefinition reportDefinition, final ExpeditedAdverseEventReport aeReport, Date baseDate) {
        assert reportDefinition != null : "ReportDefinition must be not null. Unable to create a Report";
        assert aeReport != null : "ExpeditedAdverseEventReport should not be null. Unable to create a Report";
        
        Date now = nowFactory.getNow();
        
        Report report = reportDefinition.createReport();
        report.setCreatedOn(now);
        Date dueDate = reportDefinition.getExpectedDueDate(baseDate == null ? now : baseDate);
        report.setDueOn(dueDate);
        report.getLastVersion().setReportVersionId("0"); //default

        //attach the aeReport to report
        aeReport.addReport(report);

        addScheduledNotifications(reportDefinition, report);
        return report;
    }
    
    
    public Integer getCurrentReportVersion(ExpeditedAdverseEventReport aeReport){
    	String nciInstituteCode = aeReport.getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode();
    	Integer currentBaseVersion = Integer.parseInt(aeReport.getCurrentVersionForSponsorReport(nciInstituteCode));
    	return currentBaseVersion;
    }
    
    public void addScheduledNotifications(ReportDefinition reportDefinition, Report report){
    	//Note : there is a change in busineess requirement, that at firing time only we generate the message/recipients
    	//So only one Scheduled Notification per Planned Notification. 
    	
    	if(CollectionUtils.isEmpty(reportDefinition.getPlannedNotifications())) return;
    	
    	Date now = nowFactory.getNow();
    	Calendar cal = GregorianCalendar.getInstance();
    	
    	for (PlannedNotification plannedNotification : reportDefinition.getPlannedNotifications()) {
    		
    		ScheduledNotification scheduledNotification = plannedNotification.createScheduledNotification("dummy");
    		
    		if(plannedNotification instanceof PlannedEmailNotification){
    			ScheduledEmailNotification scheduledEmailNotification = (ScheduledEmailNotification)scheduledNotification;
    			scheduledEmailNotification.setBody("dummy");
    			scheduledEmailNotification.setSubjectLine("dummy");
    		}
    		
    		//set the scheduled dates
            cal.setTime(now);
            cal.add(reportDefinition.getTimeScaleUnitType().getCalendarTypeCode(), (plannedNotification.getIndexOnTimeScale()));
            scheduledNotification.setScheduledOn(cal.getTime());

            report.addScheduledNotification(scheduledNotification);
    	}
    
    	
    }
  


    public void setNowFactory(final NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }
    
}
