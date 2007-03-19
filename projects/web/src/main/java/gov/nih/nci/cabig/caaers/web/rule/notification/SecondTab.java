package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTextField;

/***
 * This tab has the details of Email Message.
 * 
 * 
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class SecondTab extends DefaultTab {

	private static final String NOTIFICATION_DETAILS_FIELD_GROUP = "ruleset";
	
	public SecondTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}
	
	public SecondTab() {
		this("Specify Notification Details", "Notification Details", "rule/notification/secondTab");
	}

	@Override
    protected void initFields() {
        //
		// Specify Recipients
		//
		
		addField(NOTIFICATION_DETAILS_FIELD_GROUP, new DefaultTextField(
                "from", "Name", true));

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
	
}
