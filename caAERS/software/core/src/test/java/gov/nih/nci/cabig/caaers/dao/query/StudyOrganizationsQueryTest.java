package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class StudyOrganizationsQueryTest extends TestCase {
	
	public void testFilterByOrganizationId(){
		StudyOrganizationsQuery query = new StudyOrganizationsQuery();
		query.filterByOrganizationId(10);
		assertEquals("select so from StudyOrganization so WHERE  so.retiredIndicator <> true  AND  so.organization.id = :orgId", query.getQueryString());
		assertTrue(query.getParameterMap().containsKey("orgId"));
	}
}