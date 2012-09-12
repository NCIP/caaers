package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_ROUTINE_REPORT;
import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEvent;

/**
 * @author Ramakrishna Gundala
 */
@CaaersUseCases({CREATE_EXPEDITED_REPORT, CREATE_ROUTINE_REPORT})
public class ExternalAdverseEventDaoTest extends CaaersDbNoSecurityTestCase {
	
	private ExternalAdverseEventDao dao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = (ExternalAdverseEventDao) getDeployedApplicationContext().getBean("externalAdverseEventDao");
	}

    public void testGetById() throws Exception{
    	
    	ExternalAdverseEvent externalAdverseEvent  = dao.getById(-1);
    	assertNotNull(externalAdverseEvent);
    }
    
}
