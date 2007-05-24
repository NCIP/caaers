package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		NotificationCommand nfCmd = (NotificationCommand)cmd;
		//update the report calendar 
		nfCmd.updateReportCalendarTemplate();
		super.postProcess(req,cmd,errors);
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
	
}
