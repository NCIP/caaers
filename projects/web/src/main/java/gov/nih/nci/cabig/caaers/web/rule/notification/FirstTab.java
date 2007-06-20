package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.rule.notification.enums.NotificationType;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.validation.Errors;

public class FirstTab extends DefaultTab {

	private static final String NOTIFICATION_TYPE_FIELD_GROUP = "ruleset";
	
	public FirstTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}
	
	public FirstTab() {
		this("Create Report Calendar","Basic Details","rule/notification/firstTab");
	}

	@Override
    protected void initFields() {
        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultTextField(
        			"name", "Name", true));
        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultTextArea(
    			"description", "Description", false));
//        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultSelectField(
//                "notificationType", "Notification Method", true,
//                    createMapFromArray(NotificationType.values())));
//        
        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultSelectField(
                "timeScaleType", "Time Scale UOM", true,
                   createMapFromArray(TimeScaleUnit.values())));
        
        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultTextField(
    			"duration", "Time Till Report Due", true));
        
    }

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.web.tabs.Tab#postProcess(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void postProcess(HttpServletRequest req, RuleInputCommand cmd, Errors errors) {
		super.postProcess(req,cmd,errors);
		ReportDefinitionCommand nfCmd = (ReportDefinitionCommand)cmd;
		if(!errors.hasErrors()) nfCmd.updateReportCalendarTemplate();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.web.tabs.Tab#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(RuleInputCommand cmd, Errors errors) {
		super.validate(cmd,errors);
		ReportDefinitionCommand nfCmd = (ReportDefinitionCommand)cmd;
		if(StringUtils.isEmpty(nfCmd.getName())){
			errors.rejectValue("name", "REQUIRED", "Missing Name");
		}
		if(!NumberUtils.isDigits(nfCmd.getDuration())){
			errors.rejectValue("duration","REQUIRED", "Invalid Time Till Report Due");
		}
		if(StringUtils.isEmpty(nfCmd.getTimeScaleType())){
			errors.rejectValue("timeScaleType","REQUIRED","Invalid Time Scale UOM"); 
		}
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.web.rule.DefaultTab#referenceData(gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand)
	 */
	@Override
	public Map<String, Object> referenceData(RuleInputCommand command) {
		return super.referenceData(command);
	}
	
	@Override
	public boolean isAllowDirtyForward() {
		return false;
	}
	
}