package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Notification;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the Notification domain object.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
@Transactional(readOnly = true)
public class NotificationDao extends CaaersDao<Notification> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    public Class<Notification> domainClass() {
        return Notification.class;
    }

    /**
     * Get the list of all notifications.
     * 
     * @return return the list of notifications.
     */
    @SuppressWarnings("unchecked")
    public List<Notification> getAll() {
        return getHibernateTemplate().find("from Notification");
    }

    /**
     * Save the notification.
     * 
     * @param notification
     *                The notification to be saved.
     */
    @Transactional(readOnly = false)
    public void save(Notification notification) {
        getHibernateTemplate().saveOrUpdate(notification);
    }
}