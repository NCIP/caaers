package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.web.rule.notification.enums.NotificationType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.InstantiateFactory;
import org.apache.commons.collections15.list.LazyList;
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


	// page -4
	private String to;
	private String message;
	private String pointOnScale = "0"; // the selected point in the time scale
	private String lastPointOnScale;
	private String indexToFetch = "0";
	private String subjectLine;
	private List<String> roleRecipient = new ArrayList<String>();
	private List<String> directRecipient = new ArrayList<String>();
	private Map<Object, Object> roles;

	private Map<String, Integer> mandatoryFieldMap;

	// supporting domain objects
	protected ReportDefinition rpDef;
	protected ReportDefinitionDao rpDefDao;

	//flow support variables
	private boolean validationFailed;
	private String delete; //index in the list to be deleted
	private String entity; //entity to be deleted

	public ReportDefinitionCommand(ReportDefinition rpDef, ReportDefinitionDao rpDefDao){
		this.rpDef = rpDef;
		this.rpDefDao = rpDefDao;
		initializeMandatoryFieldMap();
	}

	@SuppressWarnings("unchecked")
	public List<PlannedNotification> getEmailNotifications(){
		List<PlannedNotification> plannedNotifications = rpDef.fetchPlannedNotification(Integer.parseInt(indexToFetch));
		return  LazyList.decorate(plannedNotifications,
				new PlannedEmailNotificationFactory(this.rpDef.getPlannedNotifications())
			);
	}




	///LOGIC
	public void reset() {
		subjectLine = "";
//		fromAddress = "";
		roleRecipient.clear(); // = new ArrayList<String>();
		directRecipient.clear(); // = new ArrayList<String>();
		message = "";
	}

//	public void populate() {
//
//		int indexOnScale = Integer.parseInt(pointOnScale);
//		PlannedNotification pn = rpDef
//				.fetchPlannedNotification(indexOnScale);
//		populate(pn);
//		lastPointOnScale = pointOnScale;
//	}

	public void populate(PlannedNotification pn) {

		//reset the screen fields
		reset();
		if(pn == null) return;

		if (pn instanceof PlannedEmailNotification) {
			PlannedEmailNotification pen = (PlannedEmailNotification) pn;
			subjectLine = pen.getSubjectLine();
//			fromAddress = pen.getFromAddress();
            message = (pen.getNotificationBodyContent() != null) ? pen
                .getNotificationBodyContent().getBody() : "";
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

	public void initializeMandatoryFieldMap(){
		mandatoryFieldMap = new LinkedHashMap<String, Integer>();
		String path;
		int i = 0;
		for(ReportMandatoryFieldDefinition mf : rpDef.getMandatoryFields()){
			path = mf.getFieldPath();
//			path = StringUtils.replaceChars(path, '[', '(');
//			path = StringUtils.replaceChars(path, ']', ')');
//			path = StringUtils.replaceChars(path, '.', '_');
    		mandatoryFieldMap.put(path, i);
    		i++;
    	}
	}

	/**
	 * <p>
	 * This method will remove <code>PlannedNotification</code> object
	 * or <code>ReportDeliveryDefinition</code> from the internal <code>ReportDenfinition</code>
	 * identified by <code>rpDef</code>.
	 *<br/>
	 * The property <code>entity</code> signifies whether it is a {@link PlannedNotification} or {@link ReportDeliveryDefinition}.
	 * The property <code>delete</code> references the index of the item to be deleted:-
	 * <br />In the case of <code>PlannedNotification</code> it is the <code>indexOnTimeScale</code>
	 * <br />In the case of <code>ReportDeliverydeifnition</code>, it is the index in the lazy list returned by <code>getReportDeliveryDefinitions</code>.
	 *</p>
	 */
	public void removeEntities(){
		if (StringUtils.isEmpty(delete)  || StringUtils.isEmpty(entity))
			return;
		int indexToDelete = NumberUtils.toInt(delete);
		if(StringUtils.equals(entity, "notification")){
			for (Iterator<PlannedNotification> it = rpDef
					.getPlannedNotifications().iterator(); it.hasNext();) {
				PlannedNotification pen = it.next();
				if (pen.getIndexOnTimeScale() == indexToDelete) {
					it.remove();
					break;
				}
			}
		}else{
			//reportdeliverydefinitions
			rpDef.getDeliveryDefinitions().remove(indexToDelete);
		}
	}


	public void updateReportCalendarTemplate() {

		// configure planned notification if lastPointOnScale is not empty
		if (StringUtils.isEmpty(lastPointOnScale)) return;
		Integer lastPoint = Integer.valueOf(lastPointOnScale);

		//below things are to be done only for NotificationType.EMAIL
		// only add values if atleast one filed has value.
		boolean mustAdd = StringUtils.isNotEmpty(message)
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
		if (StringUtils.isNotEmpty(message)) {
			NotificationBodyContent nfBody = new NotificationBodyContent();
			nfBody.setBody(message);
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

	public Map<Object, Object> getRoles() {
		return roles;
	}
	/**
	 * The roles, a map injected from spring config file, consits of the
	 * roles to which notification can be sent.
	 * @param roles
	 */
	public void setRoles(Map<Object, Object> roles) {
		this.roles = roles;
	}



	/**
	 * The entity to delete, it could be <code>notification</code> or <code>reportdefinition</code>.
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * The entity to delete, it could be <code>notification</code> or <code>reportdefinition</code>.
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}


	public Map<String, Integer> getMandatoryFieldMap() {
		return mandatoryFieldMap;
	}

	public void setMandatoryFieldMap(
			Map<String, Integer> mandatoryFieldMap) {
		this.mandatoryFieldMap = mandatoryFieldMap;
	}



	 class PlannedEmailNotificationFactory<T extends PlannedNotification> implements Factory<T>{
		private List<PlannedNotification> plannedNotificationList;

		public PlannedEmailNotificationFactory(List<PlannedNotification> plannedNotificationList) {
			this.plannedNotificationList = plannedNotificationList;
		}

		@SuppressWarnings("unchecked")
		public T create() {
			PlannedEmailNotification nf = new PlannedEmailNotification();
			NotificationBodyContent bodyContent = new NotificationBodyContent();
			nf.setNotificationBodyContent(bodyContent);
			nf.setRecipients(new ArrayList<Recipient>());
			this.plannedNotificationList.add(nf);
			return (T) nf;
		}
	}



	/**
	 * @return the indexToFetch
	 */
	public String getIndexToFetch() {
		return indexToFetch;
	}

	/**
	 * @param indexToFetch the indexToFetch to set
	 */
	public void setIndexToFetch(String indexToFetch) {
		this.indexToFetch = indexToFetch;
	}

}