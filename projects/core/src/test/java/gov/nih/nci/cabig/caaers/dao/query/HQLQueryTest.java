package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class HQLQueryTest extends TestCase {

	public void testGetQueryString() {
		HQLQuery query = new HQLQuery("abcd");
		assertEquals("abcd", query.getQueryString());
	}

}
