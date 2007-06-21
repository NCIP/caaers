package gov.nih.nci.cabig.caaers.dao.report;


import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional(readOnly=true)
public class ScheduledNotificationDao extends GridIdentifiableDao<ScheduledNotification>{
	@Override
	public Class<ScheduledNotification> domainClass() {
		return ScheduledNotification.class;
	}

    @Transactional(readOnly=false)
	public void save(ScheduledNotification rc){
		getHibernateTemplate().saveOrUpdate(rc);
	}
}
