/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ScheduledNotificationDao;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 13, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class ScheduledNotificationDaoTest extends DaoTestCase<ScheduledNotificationDao> {

    ScheduledNotificationDao snDao;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        snDao = getDao();
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        snDao = null;
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
     * Test method for
     * {@link gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao#save(gov.nih.nci.cabig.caaers.helper.ReportDefinition)}.
     */
    public void testUpdate() {

        ScheduledNotification snf = snDao.getById(-223);
        assertEquals("GridID is not the same", snf.getGridId(), "AK8282828");
        snf.setDeliveryStatus(DeliveryStatus.CREATED);
        snf.setDeliveryStatus(DeliveryStatus.RETRY);
        snDao.save(snf);
        
        interruptSession();
        
        ScheduledNotification snf2 = snDao.getById(-223);
        assertEquals(DeliveryStatus.RETRY, snf2.getDeliveryStatus());
        
    }
    
    
    public void testUpdateDeliveryStatus(){
    	ScheduledNotification snf1 = snDao.getById(-224);
    	snDao.updateDeliveryStatus(snf1, DeliveryStatus.SCHEDULED, DeliveryStatus.ERROR);
    	interruptSession();
    	ScheduledNotification snf2 = snDao.getById(-224);
    	
    	assertEquals(DeliveryStatus.ERROR, snf2.getDeliveryStatus());
    }
    
    public void testRecallScheduledNotifications() {
        List<ScheduledNotification> notifications = new ArrayList<ScheduledNotification>();
        ScheduledNotification snf = null;
        {
            snf = snDao.getById(-223);
            notifications.add(snf);
            snf = snDao.getById(-224);
            notifications.add(snf);
            snf = snDao.getById(-225);
            notifications.add(snf);
            snf = snDao.getById(-226);
            notifications.add(snf);

            snDao.recallScheduledNotifications(notifications);

        }
        interruptSession();

        {
            snf = snDao.getById(-223);
            assertEquals("Delivery status not equal", snf.getDeliveryStatus(),
                            DeliveryStatus.CREATED);
            snf = snDao.getById(-225);
            assertEquals("Delivery status not equal", snf.getDeliveryStatus(),
                            DeliveryStatus.RECALLED);

            snf = snDao.getById(-226);
            assertEquals("Delivery status not equal", snf.getDeliveryStatus(),
                            DeliveryStatus.RECALLED);
        }
    }

}
