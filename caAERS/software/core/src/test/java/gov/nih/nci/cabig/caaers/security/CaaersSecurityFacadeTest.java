package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;

import java.util.List;

public class CaaersSecurityFacadeTest extends CaaersDaoTestCase{
	
	private CaaersSecurityFacade caaersSecurityFacade ; 
	public void setUp() throws Exception {
		super.setUp();
		caaersSecurityFacade = (CaaersSecurityFacade)getApplicationContext().getBean("caaersSecurityFacade");
	}
	
	public void atestGetAccessibleOrganizationIds() {
		List<Integer> list = caaersSecurityFacade.getAccessibleOrganizationIds("1");
		assertEquals(2,list.size());
	}

	public void atestGetAccessibleOrganizationIdsUserWithAllOrgs() {
		List<Integer> list = caaersSecurityFacade.getAccessibleOrganizationIds("-7");
		assertEquals(3,list.size());
	}
	
	public void atestGetAccessibleStudyIds() {
		List<Integer> list = caaersSecurityFacade.getAccessibleStudyIds("1");
		assertEquals(2,list.size());
	}
	public void testGetAccessibleStudyIdsUserWithAllStudies() {
		List<Integer> list = caaersSecurityFacade.getAccessibleStudyIds("-7");
		assertEquals(0,list.size());
		
		list = caaersSecurityFacade.getAccessibleStudyIds("-8");
		assertEquals(1,list.size());
	}
}
