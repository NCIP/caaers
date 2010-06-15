package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;

import java.util.List;

public class ResearchStaffIdFetcherTest extends CaaersDaoTestCase {

	private CaaersResearchStaffIdFetcherImpl researchStaffIdFetcher = null;


	protected void setUp() throws Exception {
		super.setUp();
		researchStaffIdFetcher = 
			(CaaersResearchStaffIdFetcherImpl) getApplicationContext().getBean("researchStaffIdFetcher");

	}
	
	public void testFetch() {
		List<Integer> list = researchStaffIdFetcher.fetch("test-user");
		assertEquals(1,list.size());
		
		list = researchStaffIdFetcher.fetch("test-user2");
		assertEquals(3,list.size());
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
	}
}
