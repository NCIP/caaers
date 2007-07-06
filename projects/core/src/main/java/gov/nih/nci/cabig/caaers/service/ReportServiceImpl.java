package gov.nih.nci.cabig.caaers.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ExpeditedReportPerson;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * This is an service class, which is used to obtain the correct address (toAddress) of a
 * recipient.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 31, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class ReportServiceImpl  implements ReportService {
    private NowFactory nowFactory;

    public  List<String> findToAddresses(PlannedNotification pnf, Report rs){
		assert pnf != null : "PlannedNotification should not be null";
		List<String> toAddressList = new ArrayList<String>();
		String address;
		String type = ExpeditedReportPerson.EMAIL;
		if(pnf instanceof PlannedEmailNotification)
			type = ExpeditedReportPerson.EMAIL;

		for(Recipient r : pnf.getRecipients()){
			if(r instanceof ContactMechanismBasedRecipient){
				address = ((ContactMechanismBasedRecipient)r).getContactName();
				toAddressList.add(address);
			}else if(r instanceof RoleBasedRecipient){
				String roleName = ((RoleBasedRecipient)r).getRoleName();
				toAddressList.add(findContactMechanismValue(roleName, type, rs.getAeReport()));
			}
		}//for each r

		return toAddressList;
	}

    // package-level for testing
    String findContactMechanismValue(
        String role, String mechanismType, ExpeditedAdverseEventReport aeReport) {
		// TODO : do runtime expression evaluation using roleEntityMapping.
        // TODO: these role names should be defined as constants somewhere
        if (StringUtils.equals("Reporter", role)) {
            return aeReport.getReporter().getContactMechanisms().get(mechanismType);
        } else if (StringUtils.equals("Site Study PI", role)) {

        } else if (StringUtils.equals("Study Chair", role)) {

        } else if (StringUtils.equals("Treating Physician", role)) {
            return aeReport.getPhysician().getContactMechanisms().get(mechanismType);
        } else if (StringUtils.equals("Sponsor", role)) {

        } else if (StringUtils.equals("IRB", role)) {

        }
        return null;
	}

	

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.service.ReportService#applyRuntimeReplacements(java.lang.String, gov.nih.nci.cabig.caaers.domain.report.Report)
	 */
	public String applyRuntimeReplacements(String rawText, Report report) {
		Configuration cfg = new Configuration();
        try {
            Template t = new Template("message", new StringReader(rawText),cfg);
            Map<Object, Object> map = getContextVariables(report);
            StringWriter writer = new StringWriter();
            t.process(map, writer);
            return writer.toString();
        } catch (TemplateException e) {
            throw new CaaersSystemException("Error while applying freemarker template [PlannedNotificatiton.body]", e);
        } catch (IOException e) {
            throw new CaaersSystemException("Error while applying freemarker template [PlannedNotificatiton.body]", e);
        }
	}

	public Map<Object,Object> getContextVariables(Report report){
		//TODO : properly populate the following....
		//TODO: add appropriate null-checks
		Map<Object, Object> map = new HashMap<Object, Object>();
		// map.put("nCIProtocolNumber", report.getAeReport().getStudy().getPrimaryIdentifier().getValue());
		// map.put("reportId", report.getAeReport().getId());
		
		return map;
	}

    /**
     * Creates a report from the given definition and associates it with the
     * given aeReport.  Initiates all notifications for the report.
     */
    public Report createReport(ReportDefinition repDef, ExpeditedAdverseEventReport aeReport) {
        assert repDef != null : "ReportDefinition must be not null. Unable to create a Report";
        assert aeReport != null : "ExpeditedAdverseEventReport should not be null. Unable to create a Report";

        Date now = nowFactory.getNow();
        Calendar cal = GregorianCalendar.getInstance();
        
        Report report = repDef.createReport();
        report.setCreatedOn(now);

        //attach the aeReport to report
        aeReport.addReport(report);

        //set the due date
        cal.setTime(now);
        cal.add(repDef.getTimeScaleUnitType().getCalendarTypeCode(), repDef.getDuration());
        report.setDueOn(cal.getTime());

        //populate the delivery definitions
        if (repDef.getDeliveryDefinitions() != null) {
            for (ReportDeliveryDefinition rdd : repDef.getDeliveryDefinitions()) {
                ReportDelivery rd = new ReportDelivery();
                rd.setDeliveryStatus(DeliveryStatus.CREATED);
                rd.setEndPoint(""); // TODO: this field is required, but I'm not clear what it is for
                rd.setReportDeliveryDefinition(rdd);
                //fetch the contact mechanism for role based entities.
                if (rdd.getEntityType() == rdd.ENTITY_TYPE_ROLE) {
                    findContactMechanismValue(rdd.getEndPoint(), rdd.getEndPointType(), aeReport);
                } else {
                    rd.setEndPoint(rdd.getEndPoint());
                }
                report.addReportDelivery(rd);
                rd.setReport(report);
            }//~for rdd
        }//~if

        //populate the scheduled notifications.
        //Note:- ScheduledNotification is per Recipient. A PlannedNotificaiton has many recipients.
        if (repDef.getPlannedNotifications() != null) {

            String subjectLine = null;
            String bodyContent = null;

            for (PlannedNotification pnf : repDef.getPlannedNotifications()) {

                //obtain all valid recipient address
                List<String> toAddressList = findToAddresses(pnf, report);
                // for each recipient(address) create a ScheduledNotification.
                for (String to : toAddressList) {

                    ScheduledNotification snf = null;
                    // TODO: instanceof indicates an abstraction failure.  Could this be domain logic?
                    if (pnf instanceof PlannedEmailNotification) {
                    	PlannedEmailNotification penf = (PlannedEmailNotification) pnf;
                    	ScheduledEmailNotification senf  = penf.createScheduledNotification(to);
                        snf = senf;
                    	if (subjectLine == null) {
                            subjectLine = applyRuntimeReplacements(penf.getSubjectLine(), report);
                        }
                        senf.setSubjectLine(subjectLine);
                    }

                    if (bodyContent == null) {
                        bodyContent = applyRuntimeReplacements(pnf.getNotificationBodyContent().getBodyAsString(), report);
                    }
                    snf.setBody(bodyContent.getBytes());

                    // TODO: consider some or all of this domain logic, too
                    snf.setCreatedOn(now);
                    snf.setDeliveryStatus(DeliveryStatus.CREATED);
                    cal.setTime(now);
                    cal.add(repDef.getTimeScaleUnitType().getCalendarTypeCode(), pnf.getIndexOnTimeScale());
                    snf.setScheduledOn(cal.getTime());

                    report.addScheduledNotification(snf);

                }//for each to
            }//for each pnf
        }//~if
        return report;
    }

    public void setNowFactory(NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }
}