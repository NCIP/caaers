package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportRepositoryImplIntegrationTest  extends DaoNoSecurityTestCase<OrganizationDao> {
	
	ReportRepositoryImpl impl;
	ReportDao reportDao;
	protected void setUp() throws Exception {
		super.setUp();
		impl = (ReportRepositoryImpl)getDeployedApplicationContext().getBean("reportRepository");
		reportDao = (ReportDao) getDeployedApplicationContext().getBean("reportDao");
	}

	public void testUnAmendReport() {
		{
			Report report = reportDao.getById(-223);
			assertNotNull(report);
			assertEquals(ReportStatus.AMENDED, report.getStatus());
			assertNotNull(report.getAmendedOn());
			assertNotNull(report.getSubmittedOn());
			
			impl.unAmendReport(report);
		}
		interruptSession();
		{
			Report report = reportDao.getById(-223);
			assertNotNull(report);
			assertEquals(ReportStatus.COMPLETED, report.getStatus());
			assertNull(report.getAmendedOn());
			assertNotNull(report.getSubmittedOn());
		}
		
		
		
	}

}
