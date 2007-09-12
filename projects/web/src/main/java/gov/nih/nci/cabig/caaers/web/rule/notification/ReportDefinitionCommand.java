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
	private String pointOnScale = "0"; // the selected point in the time scale
	private String lastPointOnScale;
	private String indexToFetch = "0";
	private Map<Object, Object> roles;

	private Map<String, Integer> mandatoryFieldMap;

	// supporting domain objects
	protected ReportDefinition rpDef;
	protected ReportDefinitionDao rpDefDao;

	//flow support variables
	private boolean validationFailed;
	private String delete; //index in the list to be deleted
	private String entity; //entity to be deleted
	private String tempProperty;

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
	public void initializeMandatoryFieldMap(){
		mandatoryFieldMap = new LinkedHashMap<String, Integer>();
		String path;
		int i = 0;
		for(ReportMandatoryFieldDefinition mf : rpDef.getMandatoryFields()){
			path = mf.getFieldPath();
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

	public String getPointOnScale() {
		return pointOnScale;
	}

	public void setPointOnScale(String pointOnScale) {
		this.pointOnScale = pointOnScale;
	}

	public String getLastPointOnScale() {
		return lastPointOnScale;
	}

	public void setLastPointOnScale(String lastPointOnScale) {
		this.lastPointOnScale = lastPointOnScale;
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
	public String getTempProperty() {
		return tempProperty;
	}
	public void setTempProperty(String tempProperty){
		this.tempProperty = tempProperty;
	}
}