package gov.nih.nci.cabig.caaers.rules.dao;

import gov.nih.nci.cabig.caaers.rules.domain.Notification;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
public class NotificationDao extends HibernateDaoSupport {


    public Notification getById(int id) {
        return (Notification) getHibernateTemplate().get(domainClass(), id);
    }
	
    @SuppressWarnings("unchecked")
    public List<Notification> getAll() {
        return getHibernateTemplate().find("from Notification");
    }
	
    public void save(Notification notification) {
        getHibernateTemplate().saveOrUpdate(notification);
    }

	protected Class domainClass() {
		// TODO Auto-generated method stub
		return Notification.class;
	}    
}