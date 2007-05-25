package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

/***
 * This tab has the details of Email Message.
 * 
 * 
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class SecondTab extends DefaultTab {

	private List<String> allRoles;
	
	private static final String NOTIFICATION_DETAILS_FIELD_GROUP = "ruleset";
	
	public SecondTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}
	
	public SecondTab() {
		this("Specify Notification Details", "Notification Details", "rule/notification/secondTab");
	}

	
    protected void initializeFields() {
        //
		// Specify Recipients
		//

        //Subject ----- show a text field to enter the subject
        //Drop down list of Roles
        
        
        // merge the variables using velocity.
        // think of a good editor for message (Instead of text area)
    }

	public List<String> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<String> roles) {
		this.allRoles = roles;
		initializeFields();
	}
	public void postProcess(HttpServletRequest req, RuleInputCommand cmd, Errors errors) {
		//System.out.println("SecondTab: post process is called ");
		//System.out.println("cmd :" + String.valueOf(cmd));
		//System.out.println("errors :" + String.valueOf(errors));
		//System.out.println("___________________________________");
		super.postProcess(req,cmd,errors);
		NotificationCommand nfCmd = (NotificationCommand)cmd;
		//update the report calendar 
		if(errors.getErrorCount() < 1)
			nfCmd.updateReportCalendarTemplate();
		else
			nfCmd.setPointOnScale(nfCmd.getLastPointOnScale());
		
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.web.tabs.Tab#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(RuleInputCommand cmd, Errors errors) {
		//System.out.println("Second tab : validate method called....");
		//System.out.println("cmd : " + String.valueOf(cmd));
		//System.out.println("errors :" + String.valueOf(errors));
		//System.out.println("___________________________________");
		super.validate(cmd,errors);
		NotificationCommand nfCmd = (NotificationCommand)cmd;
		boolean mustValidate = StringUtils.isNotEmpty(nfCmd.getFromAddress()) ||
			StringUtils.isNotEmpty(nfCmd.getMessage()) || 
			CollectionUtils.isNotEmpty(nfCmd.getRoleRecipient())||
			CollectionUtils.isNotEmpty(nfCmd.getDirectRecipient())||
			StringUtils.isNotEmpty(nfCmd.getSubjectLine());
		if(mustValidate){
			if(StringUtils.isEmpty(nfCmd.getFromAddress()))
				errors.rejectValue("fromAddress", "REQUIRED","From Address Invalid");
			if(StringUtils.isEmpty(nfCmd.getMessage()))
				errors.rejectValue("message", "REQUIRED","Message Invalid");
			if(StringUtils.isEmpty(nfCmd.getSubjectLine()))
				errors.rejectValue("subjectLine", "REQUIRED", "Subject Line Invalid");
			if(CollectionUtils.isNotEmpty(nfCmd.getRoleRecipient())){
				for(String role : nfCmd.getRoleRecipient()){
					if(StringUtils.isEmpty(role)){
						errors.rejectValue("roleRecipient","REQUIRED", "Invalid Recipient Information");
					}
				}
			}
			if(CollectionUtils.isNotEmpty(nfCmd.getDirectRecipient())){
				for(String email : nfCmd.getDirectRecipient()){
					if(StringUtils.isEmpty(email)){
						errors.rejectValue("directRecipient", "REQUIRED", "Invalid Recipient Information");
					}
				}
			}
				
		}
		nfCmd.setValidationFailed(errors.hasErrors());
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.web.rule.DefaultTab#referenceData(gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand)
	 */
	@Override
	public Map<String, Object> referenceData(RuleInputCommand cmd) {
		//populate the command from calendar template
		NotificationCommand nfCmd = (NotificationCommand)cmd;
		nfCmd.populate();
		// TODO Auto-generated method stub
		//System.out.println("second tab ref data ----------------");
		Map<String, Object> refData = super.referenceData(cmd);
		refData.put("allRoles", allRoles);
		return refData;
	}
	
	@Override
	public boolean isAllowDirtyBack() {
		return false;
	}
	
	@Override
	public boolean isAllowDirtyForward() {
		return false;
	}
	
	
}
