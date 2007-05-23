package gov.nih.nci.cabig.caaers.dao;


import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.domain.notification.ScheduledNotification;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional
public class ScheduledNotificationDao extends GridIdentifiableDao<ScheduledNotification>{

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
	 */
	@Override
	public Class<ScheduledNotification> domainClass() {
		return ScheduledNotification.class;
	}
	
	public void save(ScheduledNotification rc){
		getHibernateTemplate().saveOrUpdate(rc);
	}
	
	public Session getHibernateSession(){
		return super.getSession();
	}
	
	
}
