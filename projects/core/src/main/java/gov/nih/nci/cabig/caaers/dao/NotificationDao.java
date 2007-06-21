package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Notification;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
@Transactional(readOnly=true)
public class NotificationDao extends CaaersDao<Notification> {
	public Class<Notification> domainClass() {
		return Notification.class;
	}

    @SuppressWarnings("unchecked")
    public List<Notification> getAll() {
        return getHibernateTemplate().find("from Notification");
    }

    @Transactional(readOnly=false)
    public void save(Notification notification) {
        getHibernateTemplate().saveOrUpdate(notification);
    }    
}