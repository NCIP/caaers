package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.rule.CollectionSelectField;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.rule.notification.enums.NotificationType;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstTab extends DefaultTab {

	private static final String NOTIFICATION_TYPE_FIELD_GROUP = "ruleset";
	
	public FirstTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}
	
	public FirstTab() {
		this("Select Notification Type","Select Type","rule/notification/firstTab");
	}

	@Override
    protected void initFields() {
        addField(NOTIFICATION_TYPE_FIELD_GROUP, new DefaultTextField(
                "name", "Name", true));
        //ArrayList list = new ArrayList();
        //list.add("Default Email Notification");
        //list.add("Study Email Notification");
        addField(NOTIFICATION_TYPE_FIELD_GROUP, new CollectionSelectField(
                "type", "Notification Method", true,
                    Arrays.asList(NotificationType.values()), "name", null));
                //list, "name", null));
    }
}