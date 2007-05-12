package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Notification;

import java.util.List;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
public class NotificationDao extends CaaersDao<Notification> {
	public Class<Notification> domainClass() {
		return Notification.class;
	}

    @SuppressWarnings("unchecked")
    public List<Notification> getAll() {
        return getHibernateTemplate().find("from Notification");
    }
	
    public void save(Notification notification) {
        getHibernateTemplate().saveOrUpdate(notification);
    }    
}