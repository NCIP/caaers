package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.ReconciledAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ReconciliationReport;

import java.util.Date;

/**
 * @author Ramakrishna Gundala
 */
public class ReconciliationReportDaoTest extends CaaersDbNoSecurityTestCase {
	
	private ReconciliationReportDao dao;
	private ReconciledAdverseEventDao reconciledAdverseEventDao;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = (ReconciliationReportDao) getDeployedApplicationContext().getBean("reconciliationReportDao");
		reconciledAdverseEventDao = (ReconciledAdverseEventDao) getDeployedApplicationContext().getBean("reconciledAdverseEventDao");
		adverseEventReportingPeriodDao = (AdverseEventReportingPeriodDao) getDeployedApplicationContext().getBean("adverseEventReportingPeriodDao");
	}

    public void testGetById() throws Exception{
    	
    	ReconciliationReport reconciliationReport  = dao.getById(-1000);
    	assertNotNull(reconciliationReport);
    	assertEquals("Admin",reconciliationReport.getReviewedBy());
    	assertEquals(1,reconciliationReport.getReconciledAdverseEvents().size());
    	assertEquals(Grade.MODERATE,reconciliationReport.getReconciledAdverseEvents().get(0).getGrade());
    	assertNotNull(reconciliationReport.getAdverseEventReportingPeriod());
    	assertEquals(-1000,reconciliationReport.getAdverseEventReportingPeriod().getId().intValue());
    }
    
   public void testSave() throws Exception{
    	
	   ReconciliationReport reconciliationReport  = new ReconciliationReport();
    	AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(-1000);
    	reconciliationReport.setAdverseEventReportingPeriod(reportingPeriod);
    	Date today = new Date();
    	reconciliationReport.setUpdatedDate(today);
    	reconciliationReport.setReviewedBy("User1");
    	
    	dao.save(reconciliationReport);
    	
    	ReconciliationReport savedReconciliationReport  = dao.getById(reconciliationReport.getId());
    	
    	assertNotNull(savedReconciliationReport);
    	assertNotNull(savedReconciliationReport.getId());
    	assertEquals("User1",reconciliationReport.getReviewedBy());
    	assertEquals(today,reconciliationReport.getUpdatedDate());
    }
   
   
   public void testGetReconciledAdverseEventById() throws Exception{
	   ReconciledAdverseEvent reconciledAdverseEvent  = reconciledAdverseEventDao.getById(-1000);
   	   assertNotNull(reconciledAdverseEvent);
   }
    
}
