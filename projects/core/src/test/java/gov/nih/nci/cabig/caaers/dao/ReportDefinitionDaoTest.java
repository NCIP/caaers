/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.dao;

import java.util.ArrayList;
import java.util.List;


import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.NotificationAttachment;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class ReportDefinitionDaoTest extends DaoTestCase<ReportDefinitionDao> {
	
	
	ReportDefinitionDao rctDao;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		rctDao = getDao();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		rctDao  = null;
		super.tearDown();
		
	}
	

	
	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.ReportDefinitionDao#domainClass()}.
	 */
	public void testDomainClass() {
		log.debug("domainClass :" + rctDao.domainClass().getName());
		assertEquals(ReportDefinition.class.getName(), rctDao.domainClass().getName());
	}
	
	public void testGetByName(){
		String name = "RCT-222";
		ReportDefinition rct = rctDao.getByName(name);
		assertEquals("The name is not matching", name, rct.getName());
	}

	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.ReportDefinitionDao#save(gov.nih.nci.cabig.caaers.domain.notification.ReportDefinition)}.
	 */
	public void testSave() {
		ReportDefinition rct = new ReportDefinition();
		rct.setDuration(5);
		rct.setGridId("202020202044iiei90");
		rct.setName("Test-RCT");
		rct.setTimeScaleUnitType(TimeScaleUnit.DAY);
		//create planned notifications
		List<PlannedNotification> pnlist = new ArrayList<PlannedNotification>();
		PlannedEmailNotification pen = new PlannedEmailNotification();
		//create notification body
		NotificationBodyContent nbc = new NotificationBodyContent();
		nbc.setBody("This is my body".getBytes());
		pen.setNotificationBodyContent(nbc);
		pen.setFromAddress("biju@semanticbits.com");
		pen.setGridId("90890d99dke");
		pen.setIndexOnTimeScale(2);
		pen.setSubjectLine("MySubjectline");
		
		List<Recipient> rlist = new ArrayList<Recipient>();
		RoleBasedRecipient r = new RoleBasedRecipient();
		r.setGridId("0d0d0ekjeoiio");
		r.setRoleName("Investigator");
		rlist.add(r);
		pen.setRecipients(rlist);
		
		//create attachents
		NotificationAttachment at = new NotificationAttachment();
		at.setContent("Hi attachment".getBytes());
		
		List<NotificationAttachment> alist = new ArrayList<NotificationAttachment>();
		alist.add(at);
		pen.setAttachments(alist);
		
		pnlist.add(pen);
		rct.setPlannedNotifications(pnlist);
		Integer id = null;

		rctDao.getHibernateSession().beginTransaction();
		rctDao.save(rct);
		id = rct.getId();
		interruptSession();	
		rctDao.getHibernateSession().beginTransaction();
		
		ReportDefinition rctLoaded = null;
		rctLoaded = rctDao.getById(id);
		
		log.debug(rctLoaded.getDuration());
		PlannedEmailNotification nf = (PlannedEmailNotification)rctLoaded.getPlannedNotifications().get(0);
		assertEquals("SubjectLine Equality failed:", "MySubjectline", nf.getSubjectLine());
		assertEquals("Body Content Equality Failed", "This is my body", nf.getNotificationBodyContent().getBodyAsString() );
		//update the values.
		nf.setIndexOnTimeScale(4);
		nf.setSubjectLine("New Subject Line");
		
		
		log.debug("============= after save ===============");
	}
	public void testExample(){
		assert(true);
	}
}
