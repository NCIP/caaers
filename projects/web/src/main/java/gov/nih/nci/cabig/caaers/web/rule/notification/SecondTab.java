package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.rule.notification.enums.NotificationType;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/***
 * This tab has the details of Email Message.
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 * */
public class SecondTab extends TabWithFields<ReportDefinitionCommand> {
	
	private RepeatingFieldGroupFactory rfgFactory;
	
	
	public SecondTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
		rfgFactory = new RepeatingFieldGroupFactory("main", "reportDefinition.plannedNotifications");
		rfgFactory.addField(new DefaultTextField("fromAddress","From Address", false));
		rfgFactory.addField(new DefaultTextField("recipients","Recipients", false));
		rfgFactory.addField(new DefaultTextField("subjectLine","Subject Line", false));
		rfgFactory.addField(new DefaultTextField("notificationBodyContent.bodyAsString","Message", false));
	}
	
	public SecondTab() {
		this("Specify Notification Details", "Notification Details", "rule/notification/secondTab");
	}

	
	public void postProcess(HttpServletRequest req, ReportDefinitionCommand cmd, Errors errors) {
		super.postProcess(req,cmd,errors);
		//update the report calendar 
		if(errors.getErrorCount() < 1)
			cmd.updateReportCalendarTemplate();
		else
			cmd.setPointOnScale(cmd.getLastPointOnScale());
		
	}

	@Override
	public Map<String, InputFieldGroup> createFieldGroups(ReportDefinitionCommand command) {
		InputFieldGroupMap map = new InputFieldGroupMap();
		List<PlannedNotification> pnfList = command.getReportDefinition().getPlannedNotifications();
		int size = (pnfList != null )?pnfList.size():0;
		map.addRepeatingFieldGroupFactory(rfgFactory, size);
		return map;
	}

	@Override
	protected void validate(ReportDefinitionCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
		super.validate(command, commandBean, fieldGroups, errors);
		NotificationType nfType = NotificationType.valueOf(command.getNotificationType());
		EmailValidator emailValidator = EmailValidator.getInstance();
		switch(nfType){
		
			case EMAIL_NOTIFICATION:
				//do I need to really validate??
				boolean mustValidate = StringUtils.isNotEmpty(command.getFromAddress()) ||
									   StringUtils.isNotEmpty(command.getMessage()) || 
									   CollectionUtils.isNotEmpty(command.getRoleRecipient())||
									   CollectionUtils.isNotEmpty(command.getDirectRecipient())||
									   StringUtils.isNotEmpty(command.getSubjectLine());
				if(!mustValidate) break;
				if(StringUtils.isEmpty(command.getFromAddress())){
					errors.rejectValue("fromAddress", "REQUIRED","From Address Invalid");
				}else if(!emailValidator.isValid(command.getFromAddress())){
					errors.rejectValue("fromAddress", "REQUIRED","From Address Invalid");
				}
				if(StringUtils.isEmpty(command.getMessage()))
					errors.rejectValue("message", "REQUIRED","Message Invalid");
				if(StringUtils.isEmpty(command.getSubjectLine()))
					errors.rejectValue("subjectLine", "REQUIRED", "Subject Line Invalid");
				if(CollectionUtils.isEmpty(command.getRoleRecipient()) && 
				   CollectionUtils.isEmpty(command.getDirectRecipient())){
					errors.rejectValue("roleRecipient","REQUIRED", "Invalid Recipient Information");
				}else{
				
					if(CollectionUtils.isNotEmpty(command.getRoleRecipient())){
						for(String role : command.getRoleRecipient()){
							if(StringUtils.isEmpty(role)){
								errors.rejectValue("roleRecipient","REQUIRED", "Invalid Recipient Information");
								break;
							}
						}
					}
				
					
					if(CollectionUtils.isNotEmpty(command.getDirectRecipient())){
						for(String email : command.getDirectRecipient()){
							if(!emailValidator.isValid(email)){
								errors.rejectValue("directRecipient", "REQUIRED", "Invalid Recipient Information");
								break;
							}
						}
					}
				}
			break;
			default: //TODO, other validations needs to be implemented, may be at that time refactor this into elsewhere.
	
		}
		
		command.setValidationFailed(errors.hasErrors());
	}

	@Override
	public Map<String, Object> referenceData(ReportDefinitionCommand command) {
		//show the previously keyed-in values, if validation failed.
		if(!command.isValidationFailed()) command.populate();
		
		Map<String, Object> refData = super.referenceData(command);
		return refData;
	}
}
