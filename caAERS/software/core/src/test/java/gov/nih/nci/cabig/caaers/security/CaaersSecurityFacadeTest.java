package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;

import java.util.List;

public class CaaersSecurityFacadeTest extends CaaersDaoTestCase{
	
	private CaaersSecurityFacade caaersSecurityFacade ; 
	public void setUp() throws Exception {
		super.setUp();
		caaersSecurityFacade = (CaaersSecurityFacade)getApplicationContext().getBean("caaersSecurityFacade");
	}
	
	public void testGetAccessibleOrganizationIds() {
		List<Integer> list = caaersSecurityFacade.getAccessibleOrganizationIds("1");
		for (Integer i:list) {
			System.out.println(i);
		}
	}
	
	public void testGetAccessibleStudyIds() {
		List<Integer> list = caaersSecurityFacade.getAccessibleStudyIds("1");
		for (Integer i:list) {
			System.out.println(i);
		}
	}
}
