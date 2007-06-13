/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ScheduledNotificationDao;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;


/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class ScheduledNotificationDaoTest extends DaoTestCase<ScheduledNotificationDao> {
	
	ScheduledNotificationDao snDao;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		snDao = getDao();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		snDao  = null;
		super.tearDown();
		
	}
	
	
	@Override
	protected void interruptSession() {
		super.interruptSession();
	}
	
	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao#domainClass()}.
	 */
	public void testDomainClass() {
		log.debug("domainClass :" + snDao.domainClass().getName());
		assertEquals(ScheduledNotification.class.getName(), snDao.domainClass().getName());
	}

	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao#save(gov.nih.nci.cabig.caaers.helper.ReportDefinition)}.
	 */
	public void testUpdate() {
		
		ScheduledNotification snf = snDao.getById(-223);
		assertEquals("GridID is not the same", snf.getGridId(), "AK8282828");
		snf.setDeliveryStatus(DeliveryStatus.CREATED);
		
	}
	public void testExample(){
		assert(true);
	}
	
}
