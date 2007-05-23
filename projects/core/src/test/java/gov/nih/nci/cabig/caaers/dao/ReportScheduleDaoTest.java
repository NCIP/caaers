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
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.notification.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.notification.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.notification.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.notification.Recipient;
import gov.nih.nci.cabig.caaers.domain.notification.ReportCalendarTemplate;
import gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule;
import gov.nih.nci.cabig.caaers.domain.notification.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledNotification;
import gov.nih.nci.cabig.caaers.domain.notification.TimeScaleUnit;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class ReportScheduleDaoTest extends DaoTestCase<ReportScheduleDao> {
	
	Session hibernateSession = null;
	Transaction hibernateTransaction = null;
	ReportScheduleDao rsDao;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		rsDao = getDao();
		loadHibernateSession();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		unloadHibernateSession();
		rsDao  = null;
		super.tearDown();
		
	}
	private void loadHibernateSession(){
		hibernateSession = rsDao.getHibernateSession();
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
		System.out.println("domainClass :" + rsDao.domainClass().getName());
		assertEquals(ReportSchedule.class.getName(), rsDao.domainClass().getName());
	}

	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.ReportCalendarTemplateDao#save(gov.nih.nci.cabig.caaers.domain.notification.ReportCalendarTemplate)}.
	 */
	public void testSave() {
		
		ReportSchedule rs = new ReportSchedule();
		rs.setAeReport(null);
		rs.setName("My Sample Report");
		rs.setCreatedOn(new Date());
		rs.setDueOn(new Date());
		rs.setSubmittedOn(new Date());
		rs.setGridId("ADEDR99393939");
		
		beginTransaction();
		
		rsDao.save(rs);
	//	commit();
		Integer id = rs.getId();
		
		System.out.println("ReportSchedule ID: [" + id + "]");
		
	}
	
	public void testGetByName(){
		String name = "Sample Report";
		ReportSchedule rs = rsDao.getByName(name);
		assertEquals("The name is not matching", name, rs.getName());
	}
	
	public void testGetAllByDueDate(){
		List<ReportSchedule> list = rsDao.getAllByDueDate(new Date());
		System.out.println("size::::" + String.valueOf(list));
		for(ReportSchedule s : list){
			System.out.println(s.getId());
		}
	}
	
	public void testUpdate(){
		beginTransaction();
		
		//obtain a previously saved report
		ReportSchedule rs = rsDao.getById(-223);
		//obtain a calendar template
		ReportCalendarTemplateDao rctDao = (ReportCalendarTemplateDao)getApplicationContext().getBean("reportCalendarTemplateDao");
		ReportCalendarTemplate rc = rctDao.getById(-222);
		rs.setReportCalendarTemplate(rc);
		
		//set the scheduled email notification
		ScheduledEmailNotification sen = new ScheduledEmailNotification();
		sen.setBody("Hi this is body content".getBytes()); 
		sen.setCreatedOn(new Date());
		sen.setDeliveryStatus(DeliveryStatus.ERROR);
		sen.setFromAddress("from@from.com");
		sen.setGridId("ggg9d9d9d9d");
		sen.setScheduledOn(new Date());
		sen.setToAddress("to@to.com");
		sen.setPlanedNotificaiton(rc.getPlannedNotifications().get(0));
		List<ScheduledNotification> snfList = new ArrayList<ScheduledNotification>();
		snfList.add(sen);
		rs.setScheduledNotifications(snfList);
		
		//obtain an AE report
//		AdverseEventReportDao aeDao = (AdverseEventReportDao) getApplicationContext().getBean("adverseEventReportDao");
//		AdverseEventReport aeReport = aeDao.getById(-1);
//		aeReport.setReportSchedule(rs);
		
	//	commit();
		int id = rs.getId();
		interruptSession();
		
		beginTransaction();
		ReportSchedule rs2 = rsDao.getById(id);
		assertEquals("ReportSchedule Name is not the same", rs.getName(), rs2.getName());
		if(rs2.getScheduledNotifications() != null && rs2.getScheduledNotifications().size() > 0){
			ScheduledNotification sn = rs2.getScheduledNotifications().get(0);
			assertEquals("ScheduledNotification Body is not the same", new String(sn.getBody()), "Hi this is body content");
		}
	//	commit();
	}
	public void testExample(){
		assert(true);
	}

}
