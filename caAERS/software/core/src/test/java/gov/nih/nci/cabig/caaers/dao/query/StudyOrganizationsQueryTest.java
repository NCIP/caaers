/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class StudyOrganizationsQueryTest extends TestCase {
	
	public void testFilterByOrganizationId(){
		StudyOrganizationsQuery query = new StudyOrganizationsQuery();
		query.filterByOrganizationId(10);
		assertEquals("select so from StudyOrganization so WHERE  so.retiredIndicator <> true  AND  so.organization.id = :orgId", query.getQueryString());
		assertTrue(query.getParameterMap().containsKey("orgId"));
		assertEquals(10, query.getParameterMap().get("orgId"));
	}

	public void testFilterByStudyId(){
		StudyOrganizationsQuery query = new StudyOrganizationsQuery();
		query.filterByStudyId(3);
		assertEquals("select so from StudyOrganization so WHERE  so.retiredIndicator <> true  AND  so.study.id = :studyId", query.getQueryString());
		assertTrue(query.getParameterMap().containsKey("studyId"));
	}

    public void testFilterByOrganizationNameOrNciCode() {
        StudyOrganizationsQuery query = new StudyOrganizationsQuery();
        query.filterByOrganizationNameOrNciCode("filterText");
        assertEquals("select so from StudyOrganization so WHERE (lower(so.organization.name) LIKE :TEXT or lower(so.organization.nciInstituteCode) LIKE :TEXT)", query.getQueryString());
        assertTrue(query.getParameterMap().containsKey("TEXT"));
    }
}
