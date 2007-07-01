package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.web.rule.notification.enums.NotificationType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 *         Created-on : May 22, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public class ReportDefinitionCommand  {

	// page - 1
	private String name;
	private String description;
	private String timeScaleType;
	private String duration;
	private String notificationType;

	// page -3
	private String to;
	private String message;
	private String pointOnScale = "1"; // the selected point in the time scale
	private String lastPointOnScale;
	private String subjectLine;
	private String fromAddress;
	private List<String> roleRecipient = new ArrayList<String>();
	private List<String> directRecipient = new ArrayList<String>();
	private Map<String, String> roles;

	// supporting domain objects
	protected ReportDefinition rpDef;
	protected ReportDefinitionDao rpDefDao;

	//flow support variables
	private boolean validationFailed;
	private String delete;
	
	///LOGIC
	public void reset() {
		subjectLine = "";
		fromAddress = "";
		roleRecipient.clear(); // = new ArrayList<String>();
		directRecipient.clear(); // = new ArrayList<String>();
		message = "";
	}

	public void populate() {
		this.name = rpDef.getName();
		this.description = rpDef.getDescription();
		this.duration = String.valueOf(rpDef.getDuration());
		this.timeScaleType = rpDef.getTimeScaleUnitType().getName();
		int indexOnScale = Integer.parseInt(pointOnScale);
		PlannedNotification pn = rpDef
				.fetchPlannedNotification(indexOnScale);
		populate(pn);
		lastPointOnScale = pointOnScale;
	}

	public void populate(PlannedNotification pn) {
		
		//reset the screen fields 
		reset();
		if(pn == null) return;
		
		if (pn instanceof PlannedEmailNotification) {
			PlannedEmailNotification pen = (PlannedEmailNotification) pn;
			subjectLine = pen.getSubjectLine();
			fromAddress = pen.getFromAddress();
			message = (pen.getNotificationBodyContent() != null) ? pen
					.getNotificationBodyContent().getBodyAsString() : "";
			List<Recipient> recipientList = pn.getRecipients();
			String str;
			for (Recipient r : recipientList) {
				if (r instanceof RoleBasedRecipient) {
					str = ((RoleBasedRecipient) r).getRoleName();
					roleRecipient.add(str);
				} else if (r instanceof ContactMechanismBasedRecipient) {
					str = ((ContactMechanismBasedRecipient) r).getContactName();
					directRecipient.add(str);
				}
			}
		}
		
	}

	public void removePlannedNotification() {
		if (StringUtils.isEmpty(delete))
			return;
		int indexToDelete = NumberUtils.toInt(delete);
		for (Iterator<PlannedNotification> it = rpDef
				.getPlannedNotifications().iterator(); it.hasNext();) {
			PlannedNotification pen = it.next();
			if (pen.getIndexOnTimeScale() == indexToDelete) {
				it.remove();
				break;
			}
		}
	}

	public void updateReportCalendarTemplate() {
		
		rpDef.setName(name);
		rpDef.setDescription(description);
		rpDef.setTimeScaleUnitType(TimeScaleUnit
				.valueOf(timeScaleType));
		rpDef.setDuration(Integer.parseInt(duration));
		// configure planned notification if lastPointOnScale is not empty
		if (StringUtils.isEmpty(lastPointOnScale))
			return;

		Integer lastPoint = Integer.valueOf(lastPointOnScale);
		if (NotificationType.EMAIL_NOTIFICATION.name().equals(notificationType)) {
			// only add values if atleast one filed has value.
			boolean mustAdd = StringUtils.isNotEmpty(fromAddress)
					|| StringUtils.isNotEmpty(message)
					|| CollectionUtils.isNotEmpty(roleRecipient)
					|| CollectionUtils.isNotEmpty(directRecipient)
					|| StringUtils.isNotEmpty(subjectLine);
			if (!mustAdd)
				return;
			PlannedEmailNotification pen = (PlannedEmailNotification) rpDef
					.fetchPlannedNotification(lastPoint);
			if (pen == null) {
				pen = new PlannedEmailNotification();
				rpDef.addPlannedNotification(pen);
			}
			pen.setIndexOnTimeScale(lastPoint);
			pen.setSubjectLine(subjectLine);
			pen.setFromAddress(fromAddress);
			if (StringUtils.isNotEmpty(message)) {
				NotificationBodyContent nfBody = new NotificationBodyContent();
				nfBody.setBody(message.getBytes());
				pen.setNotificationBodyContent(nfBody);
			}
			List<Recipient> recipientList = pen.getRecipients();
			if (recipientList == null) {
				recipientList = new ArrayList<Recipient>();
				pen.setRecipients(recipientList);
			} else {
				recipientList.clear();
			}

			for (String role : roleRecipient) {
				Recipient r = new RoleBasedRecipient(role);
				recipientList.add(r);
			}
			for (String email : directRecipient) {
				Recipient r = new ContactMechanismBasedRecipient(email);
				recipientList.add(r);
			}
		}
		
		// reset the command(form) now
		reset();
	}
	
	///BEAN PROPERTIES
	
	/**
	 * Will tell the index of the PlannedNotification to be deleted.
	 */
	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	/**
	 * True if the validation, of the previous request failed.
	 */
	public boolean isValidationFailed() {
		return validationFailed;
	}

	public void setValidationFailed(boolean validationFailed) {
		this.validationFailed = validationFailed;
	}
	

	public ReportDefinitionDao getReportDefinitionDao() {
		return rpDefDao;
	}

	public void setReportDefinitionDao(ReportDefinitionDao rdDao) {
		this.rpDefDao = rdDao;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * The underlying domain object, used by this command object
	 * @return
	 */
	public ReportDefinition getReportDefinition() {
		return rpDef;
	}

	
	public void setReportDefinition(ReportDefinition calendarTemplate) {
		this.rpDef = calendarTemplate;
	}

	
	public String getFromAddress() {
		return fromAddress;
	}

	
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getSubjectLine() {
		return subjectLine;
	}

	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}

	
	public String getPointOnScale() {
		return pointOnScale;
	}

	public void setPointOnScale(String pointOnScale) {
		this.pointOnScale = pointOnScale;
	}

	public String getTimeScaleType() {
		return timeScaleType;
	}

	public void setTimeScaleType(String timeScaleType) {
		this.timeScaleType = timeScaleType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	
	public List<String> getDirectRecipient() {
		return directRecipient;
	}

	
	public void setDirectRecipient(List<String> directRecipient) {
		this.directRecipient = directRecipient;
	}
	public String getLastPointOnScale() {
		return lastPointOnScale;
	}

	public void setLastPointOnScale(String lastPointOnScale) {
		this.lastPointOnScale = lastPointOnScale;
	}

	public List<String> getRoleRecipient() {
		return roleRecipient;
	}

	public void setRoleRecipient(List<String> roleRecipient) {
		this.roleRecipient = roleRecipient;
	}

	public Map<String, String> getRoles() {
		return roles;
	}
	/**
	 * The roles, a map injected from spring config file, consits of the 
	 * roles to which notification can be sent.
	 * @param roles
	 */
	public void setRoles(Map<String, String> roles) {
		this.roles = roles;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	

	
	
	/*private List<Integer> getConfiguredIndexes() {
		List<Integer> list = new ArrayList<Integer>();
		if (rpDef != null) {
			List<PlannedNotification> pnList = rpDef
					.getPlannedNotifications();
			for (PlannedNotification pn : pnList) {
				list.add(pn.getIndexOnTimeScale());
			}
		}
		return list;
	}*/
	
	
}