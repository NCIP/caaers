package gov.nih.nci.cabig.caaers.dao.report;


import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;
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

	protected static final Log logger = LogFactory.getLog(ScheduledNotificationDao.class);

	@Override
	public Class<ScheduledNotification> domainClass() {
		return ScheduledNotification.class;
	}

    @Transactional(readOnly=false)
	public void save(ScheduledNotification nf){
		getHibernateTemplate().saveOrUpdate(nf);
	}

    @Transactional(readOnly=false)
    public void recallScheduledNotifications(final List<ScheduledNotification> notifications){
    	updateDeliveryStatus(notifications, DeliveryStatus.SCHEDULED, DeliveryStatus.RECALLED);
    }

    @Transactional(readOnly=false)
    public void updateDeliveryStatus(final ScheduledNotification notification, DeliveryStatus oldStatus, DeliveryStatus newStatus){
    	List<ScheduledNotification> notifications = new ArrayList<ScheduledNotification>();
    	notifications.add(notification);
    	updateDeliveryStatus(notifications,oldStatus, newStatus);
    }


    private void updateDeliveryStatus(final List<ScheduledNotification> notifications, final DeliveryStatus oldStatus, final DeliveryStatus newStatus){
    	getHibernateTemplate().execute(new HibernateCallback(){
    		public Object doInHibernate(Session session)
    				throws HibernateException, SQLException {
    			Query query = session.createQuery("update ScheduledNotification snf set snf.deliveryStatus = :newStatus" +
    					" where snf.deliveryStatus = :oldStatus and snf.id = :snfId");
    			for(ScheduledNotification nf : notifications){
    				if(!nf.getDeliveryStatus().equals(oldStatus)) continue;

    				query.setInteger("oldStatus", oldStatus.getCode());
    				query.setInteger("newStatus", newStatus.getCode());
    				query.setInteger("snfId", nf.getId());
        			query.executeUpdate();

        			if(logger.isDebugEnabled()){
        				logger.debug("Updating deliveryStatus [scheduledNotificationId :" +
        						nf.getId() + ", oldStatus : " + oldStatus.name() + ", newStatus : " + newStatus.name() + " ]");
        			}
    			}
    			return null;
    		}
    	});

    }
}
