package gov.nih.nci.cabig.caaers.domain.factory;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.*;
import gov.nih.nci.cabig.caaers.service.FreeMarkerService;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.*;

/**
 * @author Biju Joseph
 */
public class ReportFactory {

    private NowFactory nowFactory;
    
    private FreeMarkerService freeMarkerService;

    public Report createReport(final ReportDefinition reportDefinition, final ExpeditedAdverseEventReport aeReport, Boolean useDefaultVersion) {
        assert reportDefinition != null : "ReportDefinition must be not null. Unable to create a Report";
        assert aeReport != null : "ExpeditedAdverseEventReport should not be null. Unable to create a Report";

        Date now = nowFactory.getNow();
        Calendar cal = GregorianCalendar.getInstance();

        Report report = reportDefinition.createReport();
        report.setCreatedOn(now);

        ReportVersion reportVersion = new ReportVersion();
        reportVersion.setCreatedOn(now);
        reportVersion.setReportStatus(ReportStatus.PENDING);
        Integer currentBaseVersion = getCurrentReportVersion(aeReport);
        if(useDefaultVersion){
        	// Set the new version with the current version number
        	// This will happen in edit mode
        	reportVersion.setReportVersionId(currentBaseVersion.toString());
        }
        else{
        	// Increment the current version number and assign it to the new report instance
        	// This will happen in createNew and amend mode.
        	Integer newVersionId = currentBaseVersion + 1;
        	reportVersion.setReportVersionId(newVersionId.toString());
        }
        report.addReportVersion(reportVersion);

        //attach the aeReport to report
        aeReport.addReport(report);

        //set the due date
        cal.setTime(now);
        cal.add(reportDefinition.getTimeScaleUnitType().getCalendarTypeCode(), reportDefinition.getDuration());
        report.setDueOn(cal.getTime());
        reportVersion.setDueOn(cal.getTime());

        //populate the delivery definitions
        if (reportDefinition.getDeliveryDefinitions() != null) {
            for (ReportDeliveryDefinition reportDeliveryDefinition : reportDefinition.getDeliveryDefinitions()) {
                //fetch the contact mechanism for role based entities.
                if (reportDeliveryDefinition.getEntityType() == ReportDeliveryDefinition.ENTITY_TYPE_ROLE) {
                    List<String> addresses = aeReport.findEmailAddress(reportDeliveryDefinition.getEndPoint());
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
    	//populate the scheduled notifications.
        //Note:- ScheduledNotification is per Recipient. A PlannedNotificaiton has many recipients.
    	
    	Date now = nowFactory.getNow();
        Calendar cal = GregorianCalendar.getInstance();
        Map<Object, Object> variableMap = report.getContextVariables();
        if (reportDefinition.getPlannedNotifications() != null) {

            for (PlannedNotification plannedNotification : reportDefinition.getPlannedNotifications()) {
                String subjectLine = null;
                String bodyContent = null;

                //obtain all valid recipient address
                List<String> toAddressList = plannedNotification.findToAddressesForReport(report);
                // for each recipient(address) create a ScheduledNotification.
                for (String to : toAddressList) {

                    ScheduledNotification scheduledNotification = null;
                    // TODO: instanceof indicates an abstraction failure.  Could this be domain logic?
                    if (plannedNotification instanceof PlannedEmailNotification) {
                        PlannedEmailNotification penf = (PlannedEmailNotification) plannedNotification;
                        ScheduledEmailNotification senf = penf.createScheduledNotification(to);
                        scheduledNotification = senf;
                        if (subjectLine == null) {
                            subjectLine = freeMarkerService.applyRuntimeReplacementsForReport(penf.getSubjectLine(), variableMap);
                        }
                        senf.setSubjectLine(subjectLine);
                    }

                    if (bodyContent == null) {
                        bodyContent = freeMarkerService.applyRuntimeReplacementsForReport(plannedNotification.getNotificationBodyContent().getBody(), variableMap);
                    }
                    scheduledNotification.setBody(bodyContent);

                    // TODO: consider some or all of this domain logic, too
                    scheduledNotification.setCreatedOn(now);
                    scheduledNotification.setDeliveryStatus(DeliveryStatus.CREATED);
                    cal.setTime(now);
                    cal.add(reportDefinition.getTimeScaleUnitType().getCalendarTypeCode(), (plannedNotification.getIndexOnTimeScale()));
                    scheduledNotification.setScheduledOn(cal.getTime());

                    report.addScheduledNotification(scheduledNotification);

                }//for each to
            }//for each pnf
        }//~if
    }


    public void setNowFactory(final NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }
    @Required
    public void setFreeMarkerService(FreeMarkerService freeMarkerService) {
		this.freeMarkerService = freeMarkerService;
	}
    
}
