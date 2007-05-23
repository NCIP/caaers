package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.notification.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.rule.notification.enums.NotificationType;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultTextField(
    			"description", "Description", false));
//        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultSelectField(
//                "notificationType", "Notification Method", true,
//                    createMapFromArray(NotificationType.values())));
//        
        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultSelectField(
                "timeScaleType", "Time Scale Unit", true,
                   createMapFromArray(TimeScaleUnit.values())));
        
        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultTextField(
    			"duration", "Time Still Report Due", true));
        
    }

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.web.tabs.Tab#postProcess(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void postProcess(HttpServletRequest req, RuleInputCommand cmd, Errors errors) {
		System.out.println("FirstTab: post process is called ");
		System.out.println("cmd :" + String.valueOf(cmd));
		System.out.println("errors :" + String.valueOf(errors));
		System.out.println("___________________________________");
		
		NotificationCommand nfCmd = (NotificationCommand)cmd;
		nfCmd.updateReportCalendarTemplate();
		super.postProcess(req,cmd,errors);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.web.tabs.Tab#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(RuleInputCommand cmd, Errors errors) {
		System.out.println("First tab : validate method called....");
		System.out.println("cmd : " + String.valueOf(cmd));
		System.out.println("errors :" + String.valueOf(errors));
		System.out.println("___________________________________");
		super.validate(cmd,errors);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.web.rule.DefaultTab#referenceData(gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand)
	 */
	@Override
	public Map<String, Object> referenceData(RuleInputCommand command) {
		// TODO Auto-generated method stub
		return super.referenceData(command);
	}
	
	
}