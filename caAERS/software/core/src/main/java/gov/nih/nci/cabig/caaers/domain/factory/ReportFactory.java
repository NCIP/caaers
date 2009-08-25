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
	//known site specific roles.
	private transient static final  String[] studySiteSpecificRoles = {"caaers_participant_cd",
			  							 "caaers_ae_cd",
			  							 "caaers_site_cd",
			  							 "SI",/*Site Investigator*/
			  							 "SPI" /*Site Principal Investigator*/};
	  
	  //known report specific roles. 
	 private transient static final String[] reportSpecificRoles = {"REP",/*Reporter*/
			  						  "SUB" /*Submitter*/};
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
        
        //populate the delivery definitions
        if (reportDefinition.getDeliveryDefinitions() != null) {
            for (ReportDeliveryDefinition reportDeliveryDefinition : reportDefinition.getDeliveryDefinitions()) {
                //fetch the contact mechanism for role based entities.
                if (reportDeliveryDefinition.getEntityType() == ReportDeliveryDefinition.ENTITY_TYPE_ROLE) {
                	String roleName = reportDeliveryDefinition.getEndPoint();
                	List<String> addresses = null;
                	if(ArrayUtils.contains(reportSpecificRoles, roleName)){
                		addresses = report.findEmailAddressByRole(roleName);
                	}else if(ArrayUtils.contains(studySiteSpecificRoles, roleName)){
                		addresses = aeReport.getStudySite().findEmailAddressByRole(roleName);
                	}else{
                		addresses = aeReport.getStudy().findEmailAddressByRole(roleName);
                	}
                    for (String address : addresses) {
                        if (StringUtils.isNotEmpty(address)) {
                            ReportDelivery reportDelivery = reportDeliveryDefinition.createReportDelivery();
                            reportDelivery.setEndPoint(address);
                            report.addReportDelivery(reportDelivery);
                        }
                    }
                } else {
                    if (StringUtils.isNotEmpty(reportDeliveryDefinition.getEndPoint())) {
                        ReportDelivery rd = reportDeliveryDefinition.createReportDelivery();
                        rd.setEndPoint(reportDeliveryDefinition.getEndPoint());
                        report.addReportDelivery(rd);
                    }
                }

            }//~for rdd
        }//~if
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
    
//    public void addScheduledNotifications_Old(ReportDefinition reportDefinition, Report report){
//    	//populate the scheduled notifications.
//        //Note:- ScheduledNotification is per Recipient. A PlannedNotificaiton has many recipients.
//    	
//    	Date now = nowFactory.getNow();
//        Calendar cal = GregorianCalendar.getInstance();
//        Map<Object, Object> variableMap = report.getContextVariables();
//        if (reportDefinition.getPlannedNotifications() != null) {
//
//            for (PlannedNotification plannedNotification : reportDefinition.getPlannedNotifications()) {
//                String subjectLine = null;
//                String bodyContent = null;
//
//                //obtain all valid recipient address
//                List<String> toAddressList = plannedNotification.findToAddressesForReport(report);
//                // for each recipient(address) create a ScheduledNotification.
//                for (String to : toAddressList) {
//
//                    ScheduledNotification scheduledNotification = null;
//                    // TODO: instanceof indicates an abstraction failure.  Could this be domain logic?
//                    if (plannedNotification instanceof PlannedEmailNotification) {
//                        PlannedEmailNotification penf = (PlannedEmailNotification) plannedNotification;
//                        ScheduledEmailNotification senf = penf.createScheduledNotification(to);
//                        scheduledNotification = senf;
//                        if (subjectLine == null) {
//                            subjectLine = freeMarkerService.applyRuntimeReplacementsForReport(penf.getSubjectLine(), variableMap);
//                        }
//                        senf.setSubjectLine(subjectLine);
//                    }
//
//                    if (bodyContent == null) {
//                        bodyContent = freeMarkerService.applyRuntimeReplacementsForReport(plannedNotification.getNotificationBodyContent().getBody(), variableMap);
//                    }
//                    scheduledNotification.setBody(bodyContent);
//
//                    // TODO: consider some or all of this domain logic, too
//                    scheduledNotification.setCreatedOn(now);
//                    scheduledNotification.setDeliveryStatus(DeliveryStatus.CREATED);
//                    cal.setTime(now);
//                    cal.add(reportDefinition.getTimeScaleUnitType().getCalendarTypeCode(), (plannedNotification.getIndexOnTimeScale()));
//                    scheduledNotification.setScheduledOn(cal.getTime());
//
//                    report.addScheduledNotification(scheduledNotification);
//
//                }//for each to
//            }//for each pnf
//        }//~if
//    }


    public void setNowFactory(final NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }
    
}
