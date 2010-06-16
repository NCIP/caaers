package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;

import java.util.List;

public class InvestigatorIdFetcherTest extends CaaersDaoTestCase {

	private CaaersInvestigatorIdFetcherImpl investigatorIdFetcher = null;


	protected void setUp() throws Exception {
		super.setUp();
		investigatorIdFetcher = 
			(CaaersInvestigatorIdFetcherImpl) getApplicationContext().getBean("investigatorIdFetcher");

	}
	
	public void testFetch() {
		List<Integer> list = investigatorIdFetcher.fetch("test-user");
		assertEquals(1,list.size());
		
		list = investigatorIdFetcher.fetch("test-user2");
		assertEquals(3,list.size());
	}
}
