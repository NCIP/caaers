package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.ReportCalendarTemplateDao;
import gov.nih.nci.cabig.caaers.domain.notification.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.notification.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.notification.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.notification.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.notification.Recipient;
import gov.nih.nci.cabig.caaers.domain.notification.ReportCalendarTemplate;
import gov.nih.nci.cabig.caaers.domain.notification.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.notification.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.rule.notification.enums.NotificationType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 22, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class NotificationCommand implements RuleInputCommand {
	
	
	private String name;
	
	private String to;
		
	private String message;
	private String description;
	//--- mine

	private String pointOnScale = "1"; //will show the point in scale we are now
	private String lastPointOnScale; 
	
	private String timeScaleType;
	private String duration;
	
	private String subjectLine;
	private String fromAddress;
	
	private String notificationType;
	private ReportCalendarTemplate calendarTemplate;
	
	
	private List<String> roleRecipient = new ArrayList<String>();
	private List<String> directRecipient = new ArrayList<String>();
	
	
	
	ReportCalendarTemplateDao calendarTemplateDao;
	private List<String> allRoles;
	
	private boolean validationFailed;
	
	private String delete;
	
	
	/**
	 * @return the delete
	 */
	public String getDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(String delete) {
		this.delete = delete;
	}

	/**
	 * @return the validationFailed
	 */
	public boolean isValidationFailed() {
		return validationFailed;
	}

	/**
	 * @param validationFailed the validationFailed to set
	 */
	public void setValidationFailed(boolean validationFailed) {
		this.validationFailed = validationFailed;
	}

	/**
	 * @return the calendarTemplateDao
	 */
	public ReportCalendarTemplateDao getCalendarTemplateDao() {
		return calendarTemplateDao;
	}

	/**
	 * @param calendarTemplateDao the calendarTemplateDao to set
	 */
	public void setCalendarTemplateDao(ReportCalendarTemplateDao calendarTemplateDao) {
		this.calendarTemplateDao = calendarTemplateDao;
	}

	public void reset(int index){
		if(calendarTemplate == null){
		  System.out.println("NULL calendarTemplate");
		  return;
		}
		updateReportCalendarTemplate();
		PlannedNotification pn = calendarTemplate.fetchPlannedNotification(index);
		populate(pn);
		lastPointOnScale = "" + index;
	}
	
	public void reset(){
		subjectLine ="";
		fromAddress = "";
		roleRecipient.clear();  //= new ArrayList<String>();
		directRecipient.clear();  //= new ArrayList<String>();
		message = "";
	}
	
	public void populate(){
		if(calendarTemplate == null)
			return;
		int indexOnScale = Integer.parseInt(pointOnScale);
		PlannedNotification pn = calendarTemplate.fetchPlannedNotification(indexOnScale);
		populate(pn);
		lastPointOnScale = pointOnScale;
	}
	
	
	
	public void populate(PlannedNotification pn){
		if(pn instanceof PlannedEmailNotification){
			PlannedEmailNotification pen = (PlannedEmailNotification) pn;
			subjectLine = pen.getSubjectLine();
			fromAddress = pen.getFromAddress();
			message = (pen.getNotificationBodyContent() != null) ? pen.getNotificationBodyContent().getBodyAsString() : "";
			List<Recipient> recipientList = pn.getRecipients();
			String str;
			for(Recipient r : recipientList){
				if(r instanceof RoleBasedRecipient){
					str = ((RoleBasedRecipient)r).getRoleName();
					roleRecipient.add(str);
				}else if(r instanceof ContactMechanismBasedRecipient){
					str = ((ContactMechanismBasedRecipient)r).getContactName();
					directRecipient.add(str);
				}
			}
		}else{
			reset();
		}
	}
	
	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public void removePlannedNotification(){
		if(StringUtils.isEmpty(delete))
			return;
		int indexToDelete = NumberUtils.toInt(delete);
		for(Iterator<PlannedNotification> it = calendarTemplate.getPlannedNotifications().iterator(); it.hasNext();){
			PlannedNotification pen = it.next();
			if(pen.getIndexOnTimeScale() == indexToDelete){
				it.remove();
				break;
			}
		}
	}
	public void updateReportCalendarTemplate(){
		if(validationFailed)
			return; //dont populate invalid values
		
		calendarTemplate.setName(name);
		calendarTemplate.setDescription(description);
		calendarTemplate.setTimeScaleUnitType(TimeScaleUnit.valueOf(timeScaleType));
		calendarTemplate.setDuration(Integer.parseInt(duration));
		//configure planned notification if lastPointOnScale is not empty
		if(StringUtils.isEmpty(lastPointOnScale))
			return;
		
		Integer lastPoint = Integer.valueOf(lastPointOnScale);
		if(NotificationType.EMAIL_NOTIFICATION.name().equals(notificationType)){
			//only add values if atleast one filed has value.
			boolean mustAdd = StringUtils.isNotEmpty(fromAddress) ||
								   StringUtils.isNotEmpty(message) || 
								   CollectionUtils.isNotEmpty(roleRecipient)||
								   CollectionUtils.isNotEmpty(directRecipient)||
								   StringUtils.isNotEmpty(subjectLine);
			if(!mustAdd)
				return;
			PlannedEmailNotification pen = (PlannedEmailNotification) calendarTemplate.fetchPlannedNotification(lastPoint);
			if(pen == null){
				pen = new PlannedEmailNotification();
				calendarTemplate.addPlannedNotification(pen);
			}
			pen.setIndexOnTimeScale(lastPoint);
			pen.setSubjectLine(subjectLine);
			pen.setFromAddress(fromAddress);
			if(StringUtils.isNotEmpty(message)){
				NotificationBodyContent nfBody = new NotificationBodyContent();
				nfBody.setBody(message.getBytes());
				pen.setNotificationBodyContent(nfBody);
			}
			List<Recipient> recipientList = new ArrayList<Recipient>();
			for(String role : roleRecipient){
				Recipient r = new RoleBasedRecipient(role);
				recipientList.add(r);
			}
			for(String email : directRecipient){
				Recipient r = new ContactMechanismBasedRecipient(email);
				recipientList.add(r);
			}
			pen.setRecipients(recipientList);
			roleRecipient.clear();
			directRecipient.clear();
		}
	}
	
	/**
	 * @return the calendarTemplate
	 */
	public ReportCalendarTemplate getCalendarTemplate() {
		return calendarTemplate;
	}

	/**
	 * @param calendarTemplate the calendarTemplate to set
	 */
	public void setCalendarTemplate(ReportCalendarTemplate calendarTemplate) {
		this.calendarTemplate = calendarTemplate;
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the subjectLine
	 */
	public String getSubjectLine() {
		return subjectLine;
	}

	/**
	 * @param subjectLine the subjectLine to set
	 */
	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}

	/**
	 * @return the pointOnScale
	 */
	public String getPointOnScale() {
		return pointOnScale;
	}

	/**
	 * @param pointOnScale the pointOnScale to set
	 */
	public void setPointOnScale(String pointOnScale) {
		this.pointOnScale = pointOnScale;
	}

	/**
	 * @return the timeScaleType
	 */
	public String getTimeScaleType() {
		return timeScaleType;
	}

	/**
	 * @param timeScaleType the timeScaleType to set
	 */
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
	
	public void save() {
	
		calendarTemplateDao.save(calendarTemplate);
	}

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the notificationType
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * @return the directRecipient
	 */
	public List<String> getDirectRecipient() {
		return directRecipient;
	}

	/**
	 * @param directRecipient the directRecipient to set
	 */
	public void setDirectRecipient(List<String> directRecipient) {
		this.directRecipient = directRecipient;
	}

	/**
	 * @return the lastPointOnScale
	 */
	public String getLastPointOnScale() {
		return lastPointOnScale;
	}

	/**
	 * @param lastPointOnScale the lastPointOnScale to set
	 */
	public void setLastPointOnScale(String lastPointOnScale) {
		this.lastPointOnScale = lastPointOnScale;
	}

	/**
	 * @return the roleRecipient
	 */
	public List<String> getRoleRecipient() {
		return roleRecipient;
	}

	/**
	 * @param roleRecipient the roleRecipient to set
	 */
	public void setRoleRecipient(List<String> roleRecipient) {
		this.roleRecipient = roleRecipient;
	}

	/**
	 * @return the allRoles
	 */
	public List<String> getAllRoles() {
		return allRoles;
	}

	/**
	 * @param allRoles the allRoles to set
	 */
	public void setAllRoles(List<String> allRoles) {
		this.allRoles = allRoles;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	private List<Integer> getConfiguredIndexes(){
		List<Integer> list = new ArrayList<Integer>();
		if(calendarTemplate != null){
			List<PlannedNotification> pnList = calendarTemplate.getPlannedNotifications();
			for(PlannedNotification pn : pnList){
			 list.add(pn.getIndexOnTimeScale());	
			}
		}
		return list;
	}
	
	}