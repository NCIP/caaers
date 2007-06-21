package gov.nih.nci.cabig.caaers.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ExpeditedReportPerson;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;

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
        String role, String mechanismType, ExpeditedAdverseEventReport aeReport
    ) {
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

	public  void applyCalendarTemplate(ReportDefinition rcTemplate, Report rs){

		assert rcTemplate != null : "ReportDefinition must be not null, inorder to schedule notfications";
		Date now = new Date();
		Calendar cal = GregorianCalendar.getInstance();

		//set the due date
		cal.setTime(now);
		cal.add(rcTemplate.getTimeScaleUnitType().getCalendarTypeCode(), rcTemplate.getDuration());
		rs.setDueOn(cal.getTime());

		//populate scheduled notifications
		List<ScheduledNotification> snfList = new ArrayList<ScheduledNotification>();
		List<PlannedNotification> pnfList = rcTemplate.getPlannedNotifications();

		for(PlannedNotification pnf : pnfList){
			//obtain the toAddress to use.
			List<String> toAddressList = findToAddresses(pnf, rs);

			for(String to : toAddressList){
				ScheduledNotification snf = null;
				if(pnf instanceof PlannedEmailNotification){
					PlannedEmailNotification penf = (PlannedEmailNotification)pnf;
					ScheduledEmailNotification senf = new ScheduledEmailNotification();
					snf = senf;
					//set the values specific to email
					senf.setFromAddress(penf.getFromAddress());
					senf.setToAddress(to);
				}
				assert snf != null : "ScheduledNotification (snf) must be initiailized";
				snf.setBody(pnf.getNotificationBodyContent().getBody());
				snf.setCreatedOn(now);
				snf.setDeliveryStatus(DeliveryStatus.CREATED);
				snf.setPlanedNotificaiton(pnf);
				cal.setTime(now);
				cal.add(rcTemplate.getTimeScaleUnitType().getCalendarTypeCode(), pnf.getIndexOnTimeScale());
				snf.setScheduledOn(cal.getTime());

				snfList.add(snf);
			}//for each to
		}//for each pnf
		//set the scheduled notificaitons
		rs.setScheduledNotifications(snfList);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.service.ReportService#applyRuntimeReplacements(java.lang.String, gov.nih.nci.cabig.caaers.domain.report.Report)
	 */
	public String applyRuntimeReplacements(String rawText, Report report) {
		Configuration cfg = new Configuration();
		try {
			Template t = new Template("message", new StringReader(rawText),cfg);
			Map<String, Object> map = getContextVariables(report);
			StringWriter writer = new StringWriter();
			t.process(map, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new CaaersSystemException("Error while applying freemarker template[PlannedNotificaiton.body]", e);
		}
	}

	public Map<String,Object> getContextVariables(Report report){
		return new HashMap<String, Object>();
	}

}