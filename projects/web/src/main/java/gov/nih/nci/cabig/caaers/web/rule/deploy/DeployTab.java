package gov.nih.nci.cabig.caaers.web.rule.deploy;

import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;

public class DeployTab extends DefaultTab {

    public DeployTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public DeployTab() {
        this("Create Trigger Snapshot", "Create Snapshot", "rule/deploy/deployTab");
    }

    @Override
    protected void initFields() {
        /*
         * addField(NOTIFICATION_TYPE_FIELD_GROUP, InputFieldFactory.createTextField( "name",
         * "Name", true)); //ArrayList list = new ArrayList(); //list.add("Default Email
         * Notification"); //list.add("Study Email Notification");
         * addField(NOTIFICATION_TYPE_FIELD_GROUP, new CollectionSelectField( "type", "Notification
         * Method", true, Arrays.asList(NotificationType.values()), "name", null)); //list, "name",
         * null));
         */}
}