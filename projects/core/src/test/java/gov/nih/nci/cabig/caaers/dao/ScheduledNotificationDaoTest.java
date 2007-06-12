/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ScheduledNotificationDao;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportSchedule;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class ScheduledNotificationDaoTest extends DaoTestCase<ScheduledNotificationDao> {
	
	Session hibernateSession = null;
	Transaction hibernateTransaction = null;
	ScheduledNotificationDao snDao;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		snDao = getDao();
		loadHibernateSession();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		unloadHibernateSession();
		snDao  = null;
		super.tearDown();
		
	}
	private void loadHibernateSession(){
		hibernateSession = snDao.getHibernateSession();
	}
	private void unloadHibernateSession(){
		hibernateSession = null;
	}
	private void beginTransaction(){
		if(hibernateSession == null)
			loadHibernateSession();
		hibernateTransaction = hibernateSession.beginTransaction();
	}
	
	private void commit(){
		if(hibernateTransaction != null)
			hibernateTransaction.commit();
	}
	private void rollback(){
		if(hibernateTransaction != null)
			hibernateTransaction.rollback();
	}
	
	@Override
	protected void interruptSession() {
		unloadHibernateSession();
		super.interruptSession();
		loadHibernateSession();
	}
	
	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.report.ReportCalendarTemplateDao#domainClass()}.
	 */
	public void testDomainClass() {
		System.out.println("domainClass :" + snDao.domainClass().getName());
		assertEquals(ScheduledNotification.class.getName(), snDao.domainClass().getName());
	}

	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.report.ReportCalendarTemplateDao#save(gov.nih.nci.cabig.caaers.helper.ReportDefinition)}.
	 */
	public void testUpdate() {
		
		beginTransaction();
		ScheduledNotification snf = snDao.getById(-223);
		assertEquals("GridID is not the same", snf.getGridId(), "AK8282828");
		snf.setDeliveryStatus(DeliveryStatus.CREATED);
	//	commit();
		
	}
	public void testExample(){
		assert(true);
	}
	
}
