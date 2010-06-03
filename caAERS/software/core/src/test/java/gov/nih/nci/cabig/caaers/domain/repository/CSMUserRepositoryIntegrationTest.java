package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.util.Date;

public class CSMUserRepositoryIntegrationTest extends CaaersTestCase{
	
	private CSMUserRepositoryImpl csmRepo;

	protected void setUp() throws Exception {
		super.setUp();
		csmRepo = (CSMUserRepositoryImpl) getDeployedApplicationContext().getBean("csmUserRepository");
	}
	
	public void testGetUser(){
		gov.nih.nci.security.authorization.domainobjects.User user = csmRepo.getCSMUserByName("SYSTEM");
		assertNotNull(user);
	}
	
	public void testModiyUser(){
		gov.nih.nci.security.authorization.domainobjects.User user = csmRepo.getCSMUserByName("SYSTEM");
		user.setUpdateDate(new Date());
		user.setStartDate(new Date());
		csmRepo.saveCSMUser(user);
		assertNotNull(user.getUpdateDate());
	}

	
}
