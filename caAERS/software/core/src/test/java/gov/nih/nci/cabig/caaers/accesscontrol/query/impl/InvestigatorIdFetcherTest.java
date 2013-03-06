/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
		assertEquals(1,list.size());
		
		list = ( List<IndexEntry> )investigatorIdFetcher.fetch("test-user2");
		assertEquals(1,list.size());
	}
}
