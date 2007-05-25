package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

public class ThirdTab extends DefaultTab{

	private static final String NOTIFICATION_TYPE_FIELD_GROUP = "ruleset";
	
	public ThirdTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}
	
	public ThirdTab() {
		this("Review","Review","rule/notification/thirdTab");
	}

	@Override
    protected void initFields() {
    }

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.web.tabs.Tab#postProcess(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void postProcess(HttpServletRequest req, RuleInputCommand cmd, Errors errors) {
		//System.out.println("ThirdTab: post process is called ");
		//System.out.println("cmd :" + String.valueOf(cmd));
		//System.out.println("errors :" + String.valueOf(errors));
		//System.out.println("___________________________________");
		super.postProcess(req,cmd,errors);
		NotificationCommand nfCmd = (NotificationCommand)cmd;
		nfCmd.setValidationFailed(errors.hasErrors());
		if(errors.hasErrors()) return;
		nfCmd.removePlannedNotification();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.web.tabs.Tab#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(RuleInputCommand cmd, Errors errors) {
		//System.out.println("Third tab : validate method called....");
		//System.out.println("cmd : " + String.valueOf(cmd));
		//System.out.println("errors :" + String.valueOf(errors));
		//System.out.println("___________________________________");
		super.validate(cmd,errors);
	}
}
