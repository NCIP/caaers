package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

public class ReportVersionRepositoryTest extends CaaersTestCase{
	
	ReportVersionRepository reportVersionRepository;
	public void setUp() throws Exception{
		super.setUp();
		reportVersionRepository = (ReportVersionRepository)getDeployedApplicationContext().getBean("reportVersionRepository");
	}
	
	public void testUpdateInProcessReports() {
		reportVersionRepository.updateInProcessReports();
	}

}
