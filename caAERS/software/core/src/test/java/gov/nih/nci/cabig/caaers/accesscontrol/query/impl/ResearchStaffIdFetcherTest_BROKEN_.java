/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;

import java.util.List;

public class ResearchStaffIdFetcherTest_BROKEN_ extends CaaersDaoTestCase {

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

	}
}
