package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.report.ScheduledNotificationDao;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;

import java.util.List;

public class ScheduledNotificationDaoStub extends ScheduledNotificationDao {
    @Override
    public void updateDeliveryStatus(ScheduledNotification notification, DeliveryStatus oldStatus,
                    DeliveryStatus newStatus) {
        notification.setDeliveryStatus(newStatus);
    }

    @Override
    public void recallScheduledNotifications(List<ScheduledNotification> notifications) {
        for (ScheduledNotification nf : notifications) {
            if (!nf.getDeliveryStatus().equals(DeliveryStatus.SCHEDULED)) continue;
            nf.setDeliveryStatus(DeliveryStatus.RECALLED);
        }
    }
}
