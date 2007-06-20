/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class ReportDaoTest extends DaoTestCase<ReportDao> {
	
	
	ReportDao rsDao;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		rsDao = getDao();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		rsDao  = null;
		super.tearDown();
		
	}

	
	
	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao#domainClass()}.
	 */
	public void testDomainClass() {
		log.debug("domainClass :" + rsDao.domainClass().getName());
		assertEquals(Report.class.getName(), rsDao.domainClass().getName());
	}

	/**
	 * Test method for {@link gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao#save(gov.nih.nci.cabig.caaers.helper.ReportDefinition)}.
	 */
	public void testSave() {
		
		Report rs = new Report();
		rs.setAeReport(null);
		rs.setName("My Sample Report");
		rs.setCreatedOn(new Date());
		rs.setDueOn(new Date());
		rs.setSubmittedOn(new Date());
		rs.setGridId("ADEDR99393939");
		
		rsDao.getHibernateSession().beginTransaction();
		rsDao.save(rs);
		Integer id = rs.getId();
		
		log.debug("Report ID: [" + id + "]");
		
	}
	
	public void testGetByName(){
		String name = "Sample Report";
		Report rs = rsDao.getByName(name);
		assertEquals("The name is not matching", name, rs.getName());
	}
	
	public void testGetAllByDueDate(){
		List<Report> list = rsDao.getAllByDueDate(new Date());
		log.debug("size::::" + String.valueOf(list));
		for(Report s : list){
			log.debug(s.getId());
		}
	}
	
	public void testUpdate(){
		
		//obtain a previously saved report
		Report rs = rsDao.getById(-223);
		//obtain a calendar template
		ReportDefinitionDao rctDao = (ReportDefinitionDao)getApplicationContext().getBean("reportDefinitionDao");
		ReportDefinition rc = rctDao.getById(-222);
		rs.setReportDefinition(rc);
		
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
		ExpeditedAdverseEventReportDao aeDao = (ExpeditedAdverseEventReportDao) getApplicationContext().getBean("expeditedAdverseEventReportDao");
		ExpeditedAdverseEventReport aeReport = aeDao.getById(-1);
		aeReport.addReport(rs);
		aeReport.setStatus(ReportStatus.PENDING);
		rs.setAeReport(aeReport);
		
		rsDao.getHibernateSession().beginTransaction();
		//save the report
		rsDao.save(rs);
		
		int id = rs.getId();
		interruptSession();
		
		rsDao.getHibernateSession().beginTransaction();
		Report rs2 = rsDao.getById(id);
		assertEquals("Report Name is not the same", rs.getName(), rs2.getName());
		if(rs2.getScheduledNotifications() != null && rs2.getScheduledNotifications().size() > 0){
			ScheduledNotification sn = rs2.getScheduledNotifications().get(0);
			assertEquals("ScheduledNotification Body is not the same", new String(sn.getBody()), "Hi this is body content");
		}
		
		//fetch AE report and see if we can get hold of the report schedule.
		aeReport = aeDao.getById(-1);
		Report rs3 = aeReport.getReports().get(0);
		assertNotNull(rs3);
		assertEquals("Report obtained from AEReport is not correct",rs2.getName(), rs3.getName());
		assertEquals(aeReport.getStatus(), ReportStatus.PENDING);
		
	}
	public void testDeleteByID(){
		Report rs = new Report();
		rs.setAeReport(null);
		rs.setName("My Sample Report");
		rs.setCreatedOn(new Date());
		rs.setDueOn(new Date());
		rs.setSubmittedOn(new Date());
		rs.setGridId("ADEDR99393939");
		
		rsDao.getHibernateSession().beginTransaction();
		rsDao.save(rs);
		Integer id = rs.getId();
		boolean deleted = rsDao.deleteById(id);
		assertTrue("unable to delete report schedule", deleted);
		//delete existing object
		 rs = rsDao.getById(-223);
		 deleted = rsDao.deleteById(rs.getId());
		assertTrue("unable to delete report schedule", deleted);
	}
	
}
