package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Notification;

import java.util.List;

import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the Notification domain object.
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
@Transactional(readOnly = true)
public class NotificationDao extends GridIdentifiableDao<Notification> implements MutableDomainObjectDao<Notification> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
	 @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
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


    


}