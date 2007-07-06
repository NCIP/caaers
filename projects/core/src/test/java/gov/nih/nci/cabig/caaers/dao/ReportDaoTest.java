package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersTestCase.*;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * @author Rhett Sutphin
 */
public class ReportDaoTest extends DaoTestCase<ReportDao> {
	private ReportDao rsDao;
    private TransactionTemplate transactionTemplate;
    private ExpeditedAdverseEventReportDao aeDao;

    @Override
    protected void setUp() throws Exception {
		super.setUp();
		rsDao = getDao();

        transactionTemplate = (TransactionTemplate) getApplicationContext().getBean("transactionTemplate");
        aeDao = (ExpeditedAdverseEventReportDao) getApplicationContext().getBean("expeditedAdverseEventReportDao");
    }

    public void testDomainClass() {
		log.debug("domainClass :" + rsDao.domainClass().getName());
		assertEquals(Report.class.getName(), rsDao.domainClass().getName());
	}

    public void testGetById() throws Exception {
        Report actual = getDao().getById(-223);
        assertDayOfDate("Wrong created on", 2007, Calendar.MAY, 15, actual.getCreatedOn());
        assertDayOfDate("Wrong due on", 2007, Calendar.MAY, 16, actual.getDueOn());
        assertTrue("Should be required", actual.isRequired());
        assertEquals("Wrong def", -222, (int) actual.getReportDefinition().getId());
    }

    public void testSave() {
		
		Report rs = new Report();
		rs.setAeReport(null);
	//	rs.setName("My Sample Report");
		rs.setCreatedOn(new Date());
		rs.setDueOn(new Date());
		rs.setSubmittedOn(new Date());
		rs.setGridId("ADEDR99393939");
		
		rsDao.save(rs);
		Integer id = rs.getId();

		log.debug("Report ID: [" + id + ']');

        // TODO: this has no assertions
    }
	
	
	public void testGetAllByDueDate(){
		List<Report> list = rsDao.getAllByDueDate(new Date());
		log.debug("size::::" + String.valueOf(list));
		for(Report s : list){
			log.debug(s.getId());
		}
	}
	
	public void testUpdate() {
        final Integer id;
        {
            //obtain a previously saved report
            final Report rs = rsDao.getById(-223);
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
            sen.setSubjectLine("This is my subject");
            sen.setPlanedNotificaiton(rc.getPlannedNotifications().get(0));
            List<ScheduledNotification> snfList = new ArrayList<ScheduledNotification>();
            snfList.add(sen);
            rs.setScheduledNotifications(snfList);

            //obtain an AE report
            ExpeditedAdverseEventReport aeReport = aeDao.getById(-1);
            aeReport.addReport(rs);
            rs.setStatus(ReportStatus.PENDING);
            rs.setAeReport(aeReport);

            //save the report
            id = (Integer) transactionTemplate.execute(new TransactionCallback() {
                public Object doInTransaction(TransactionStatus status) {
                    rsDao.save(rs);
                    assertNotNull("ID still null after save", rs.getId());
                    return rs.getId();
                }
            });
        }

        interruptSession();

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                Report reloaded = rsDao.getById(id);
                if(reloaded.getScheduledNotifications() != null && reloaded.getScheduledNotifications().size() > 0){
                    ScheduledNotification sn = reloaded.getScheduledNotifications().get(0);
                    assertEquals("ScheduledNotification Body is not the same", new String(sn.getBody()), "Hi this is body content");
                    assertEquals("Subject should be same", "This is my subject", ((ScheduledEmailNotification)sn).getSubjectLine());
                }

                // fetch AE report and see if we can get hold of the report schedule.
                ExpeditedAdverseEventReport aeReport = aeDao.getById(-1);
                Report fromAeReport = aeReport.getReports().get(0);
                assertNotNull(fromAeReport);
                assertEquals("Report obtained from AEReport is not correct", reloaded.getId(), fromAeReport.getId());
                assertEquals(ReportStatus.PENDING, ReportStatus.PENDING);
            }
        });
    }
    
    public void testDeleteByID(){
		Report rs = new Report();
		rs.setAeReport(null);
		//rs.setName("My Sample Report");
		rs.setCreatedOn(new Date());
		rs.setDueOn(new Date());
		rs.setSubmittedOn(new Date());
		rs.setGridId("ADEDR99393939");
		
		rsDao.save(rs);
		Integer id = rs.getId();

        interruptSession();

        boolean deleted = rsDao.deleteById(id);
        assertTrue("unable to delete report schedule", deleted);
        assertNull("Deleted report still present", rsDao.getById(id));
    }

    public void testDeleteExistingById() {
        //delete existing object
        Report rs = rsDao.getById(-223);
        boolean deleted = rsDao.deleteById(rs.getId());
        assertTrue("unable to delete report schedule", deleted);
        interruptSession();
        assertNull("Deleted report still present", rsDao.getById(-223));
    }

}
