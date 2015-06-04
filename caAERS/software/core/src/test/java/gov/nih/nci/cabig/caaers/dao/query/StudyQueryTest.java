/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class StudyQueryTest extends TestCase{

	public void testQueryConstructor() throws Exception {
		StudyQuery studyQuery = new StudyQuery();

        assertEquals("wrong parsing for constructor",
                        "select  distinct s from Study s", studyQuery
                                        .getQueryString());

    }
	
	public void testAllJoinsForStudy() {
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.join("short");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s join short", studyQuery
                                .getQueryString());
		
		//select s from Study s join short join s.identifiers as identifier join s.studyOrganizations as ss join ss.studyParticipantAssignments as spa join spa.participant as p join p.identifiers as pIdentifier
		studyQuery = new StudyQuery();
		studyQuery.joinIdentifier();
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s join s.identifiers as identifier", studyQuery
                                .getQueryString());
		
		studyQuery = new StudyQuery();
		studyQuery.joinStudyOrganization();
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s join s.studyOrganizations as ss", studyQuery
                                .getQueryString());
		
		studyQuery = new StudyQuery();
		studyQuery.joinStudyParticipantAssignment();
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s join s.studyOrganizations as ss join ss.studyParticipantAssignments as spa", studyQuery
                                .getQueryString());
		
		studyQuery = new StudyQuery();
		studyQuery.joinParticipantIdentifier();
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s join s.studyOrganizations as ss join ss.studyParticipantAssignments as spa join spa.participant as p join p.identifiers as pIdentifier", studyQuery
                                .getQueryString());
        
		
	}
	
	public void testFilterStudiesWithMatchingText(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterStudiesWithMatchingText("testStudy");
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
	}
	
	public void testFilterByIdentifierValue(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByIdentifierValue("idvalue");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s join s.identifiers as identifier  WHERE lower(identifier.value) LIKE :param0", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "param0"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("param0"),
        "%idvalue%");
	}
	
	
	public void testFilterByIdentifierValueExactMatch(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByIdentifierValueExactMatch("idvalue");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s join s.identifiers as identifier  WHERE lower(identifier.value) LIKE :param0", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "param0"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("param0"),
        "idvalue");
	}
	
	
	
	public void testFilterByIdentifierType(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByIdentifierType("type");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s join s.identifiers as identifier  WHERE identifier.type LIKE :param0", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "param0"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("param0"),
        "type");
	}
	
	public void testFilterByShortTitle(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByShortTitle("short");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s  WHERE lower(s.shortTitle) LIKE :param0", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "param0"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("param0"),
        "%short%");
	}
	
	
	public void testFilterByParticipantFirstName(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantFirstName("fName","like");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s  WHERE lower(p.firstName) like :param0", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "param0"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("param0"),
        "%fname%");
	}
	
	public void testFilterByParticipantLastName(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantLastName("lName","like");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s  WHERE lower(p.lastName) like :param0", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "param0"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("param0"),
        "%lname%");
	}
	
	
	public void testFilterByParticipantEthnicity(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantEthnicity("Ethnicity","like");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s  WHERE lower(p.ethnicity) like :param0", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "param0"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("param0"),
        "ethnicity");
	}
	
	public void testFilterByParticipantGender(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantGender("Gender","like");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s  WHERE lower(p.gender) like :param0", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "param0"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("param0"),
        "gender");
	}
	
	public void testFilterByParticipantDateOfBirth(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantDateOfBirth("01/01/1970");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s  WHERE p.dateOfBirth = :pDOB", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "pDOB"));
	}
	
	public void testFilterByParticipantIdentifierValue(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantIdentifierValue("value");
		assertEquals("wrong parsing for constructor",
                "select  distinct s from Study s  WHERE lower(pIdentifier.value) LIKE :param0", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "param0"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("param0"),
        "%value%");
	}
	
	
	public void testFilterBySponsorNameExactMatch(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.joinStudyOrganization();
		studyQuery.filterBySponsorOrganizationId(-1001);
		assertEquals("select  distinct s from Study s join s.studyOrganizations as ss  WHERE ss.class = 'SFS' AND ss.organization.id = :param0", studyQuery.getQueryString());
	}
	
	public void testFilterStudiesMatchingText(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterStudiesMatchingText("5876");
		String[] organizationCodes = {"MN026"};
		studyQuery.filterStudiesByOrganizations(organizationCodes);
		String expectedQuery = "select  distinct s from Study s left join fetch s.identifiers as identifier join s.studyOrganizations as ss  WHERE (lower(s.shortTitle) LIKE :param0 or lower(s.longTitle) LIKE :param0 or lower(identifier.value) LIKE :param0) AND ss.organization.nciInstituteCode in (:param1)";
		assertEquals(expectedQuery, studyQuery.getQueryString());
	}
	
    public void testFilterByShortTitleAndIdentifiers() {
        StudyQuery q = new StudyQuery();
        q.filterByShortTitleOrIdentifiers("abc");
        assertEquals("select  distinct s from Study s left join s.identifiers as identifier  WHERE lower(s.shortTitle) LIKE :param0 OR lower(identifier.value) LIKE :param1", q.getQueryString());
    }

}
