package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.domain.Notification;

import java.util.List;

/**
 * @author Biju Joseph
 * @date 3/22/12
 */
public class ListSafetyNotificationCommand {

    List<Notification> notifications;

    public ListSafetyNotificationCommand(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
