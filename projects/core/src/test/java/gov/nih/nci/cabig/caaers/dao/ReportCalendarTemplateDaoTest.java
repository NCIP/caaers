/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.notification.NotificationAttachment;
import gov.nih.nci.cabig.caaers.domain.notification.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.notification.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.notification.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.notification.Recipient;
import gov.nih.nci.cabig.caaers.domain.notification.ReportCalendarTemplate;
import gov.nih.nci.cabig.caaers.domain.notification.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.notification.TimeScaleUnit;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class ReportCalendarTemplateDaoTest extends DaoTestCase<ReportCalendarTemplateDao> {
	
	Session hibernateSession = null;
	Transaction hibernateTransaction = null;
	ReportCalendarTemplateDao rctDao;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		rctDao = getDao();
		loadHibernateSession();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		unloadHibernateSession();
		rctDao  = null;
		super.tearDown();
		
	}
	private void loadHibernateSession(){
		hibernateSession = rctDao.getHibernateSession();
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
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.ReportCalendarTemplateDao#domainClass()}.
	 */
	public void testDomainClass() {
		System.out.println("domainClass :" + rctDao.domainClass().getName());
		assertEquals(ReportCalendarTemplate.class.getName(), rctDao.domainClass().getName());
	}
	
	public void testGetByName(){
		String name = "RCT-222";
		ReportCalendarTemplate rct = rctDao.getByName(name);
		assertEquals("The name is not matching", name, rct.getName());
	}

	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.ReportCalendarTemplateDao#save(gov.nih.nci.cabig.caaers.domain.notification.ReportCalendarTemplate)}.
	 */
	public void testSave() {
		ReportCalendarTemplate rct = new ReportCalendarTemplate();
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

		
		beginTransaction();
		rctDao.save(rct);
		commit();
		id = rct.getId();
			
			
		interruptSession();	

		beginTransaction();
		
		ReportCalendarTemplate rctLoaded = null;
		rctLoaded = rctDao.getById(id);
		
		System.out.println(rctLoaded.getDuration());
		PlannedEmailNotification nf = (PlannedEmailNotification)rctLoaded.getPlannedNotifications().get(0);
		assertEquals("SubjectLine Equality failed:", "MySubjectline", nf.getSubjectLine());
		assertEquals("Body Content Equality Failed", "This is my body", nf.getNotificationBodyContent().getBodyAsString() );
		//update the values.
		nf.setIndexOnTimeScale(4);
		nf.setSubjectLine("New Subject Line");
		
		commit();
		
		
		System.out.println("============= after save ===============");
	}
	
}
