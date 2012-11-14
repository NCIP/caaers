package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

import java.util.List;

public class InvestigatorIdFetcherTest extends CaaersDaoTestCase {

	private CaaersInvestigatorIdFetcherImpl investigatorIdFetcher = null;


	protected void setUp() throws Exception {
		super.setUp();
		investigatorIdFetcher = 
			(CaaersInvestigatorIdFetcherImpl) getApplicationContext().getBean("investigatorIdFetcher");

	}
	
	public void testFetch() {
		 List<IndexEntry> list  = ( List<IndexEntry> )investigatorIdFetcher.fetch("test-user");
		assertEquals(2,list.size());
		
		list = ( List<IndexEntry> )investigatorIdFetcher.fetch("test-user2");
		assertEquals(2,list.size());
	}
}
