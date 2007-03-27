package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.rule.CollectionSelectField;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTextField;

import java.util.List;

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

        addField(NOTIFICATION_DETAILS_FIELD_GROUP, new CollectionSelectField(
                "selectedRoles", "Roles", true,
                getAllRoles(), null, null));

        addField(NOTIFICATION_DETAILS_FIELD_GROUP, new DefaultTextField(
                "to", "Email Address", true));
        
        addField(NOTIFICATION_DETAILS_FIELD_GROUP, new DefaultTextField(
                "subject", "Subject", true));
        
        addField(NOTIFICATION_DETAILS_FIELD_GROUP, new DefaultTextArea(
                "message", "Message", true));
        
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
	
}
