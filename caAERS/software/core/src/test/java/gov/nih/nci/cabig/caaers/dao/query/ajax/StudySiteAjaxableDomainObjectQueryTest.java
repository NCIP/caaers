package gov.nih.nci.cabig.caaers.dao.query.ajax;

import junit.framework.TestCase;

public class StudySiteAjaxableDomainObjectQueryTest extends TestCase {
	
	public void testFilterByStudy() {
		StudySiteAjaxableDomainObjectQuery ssQuery = new StudySiteAjaxableDomainObjectQuery();
		ssQuery.filterByStudy(5);
		String queryString = ssQuery.getQueryString();
		assertEquals("select site.id, site.organization.name from " +
				"StudySite site WHERE study.id = :STUDY_ID order by " +
				"site.organization.name", queryString);
	}

}
