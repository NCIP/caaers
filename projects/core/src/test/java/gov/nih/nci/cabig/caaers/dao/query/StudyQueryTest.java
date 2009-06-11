package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class StudyQueryTest extends TestCase{

	public void testQueryConstructor() throws Exception {
		StudyQuery studyQuery = new StudyQuery();

        assertEquals("wrong parsing for constructor",
                        "select s from Study s", studyQuery
                                        .getQueryString());

    }
	
	public void testAllJoinsForStudy() {
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.join("short");
		assertEquals("wrong parsing for constructor",
                "select s from Study s join short", studyQuery
                                .getQueryString());
		
		//select s from Study s join short join s.identifiers as identifier join s.studyOrganizations as ss join ss.studyParticipantAssignments as spa join spa.participant as p join p.identifiers as pIdentifier
		studyQuery = new StudyQuery();
		studyQuery.joinIdentifier();
		assertEquals("wrong parsing for constructor",
                "select s from Study s join s.identifiers as identifier", studyQuery
                                .getQueryString());
		
		studyQuery = new StudyQuery();
		studyQuery.joinStudyOrganization();
		assertEquals("wrong parsing for constructor",
                "select s from Study s join s.studyOrganizations as ss", studyQuery
                                .getQueryString());
		
		studyQuery = new StudyQuery();
		studyQuery.joinStudyParticipantAssignment();
		assertEquals("wrong parsing for constructor",
                "select s from Study s join s.studyOrganizations as ss join ss.studyParticipantAssignments as spa", studyQuery
                                .getQueryString());
		
		studyQuery = new StudyQuery();
		studyQuery.joinParticipantIdentifier();
		assertEquals("wrong parsing for constructor",
                "select s from Study s join s.studyOrganizations as ss join ss.studyParticipantAssignments as spa join spa.participant as p join p.identifiers as pIdentifier", studyQuery
                                .getQueryString());
        
		
	}
	
	
	public void testFilterByNonAdministrativelyComplete(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByNonAdministrativelyComplete();
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE s.status <> 'Administratively Complete'", studyQuery
                                .getQueryString());
		
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 0);
        
		
	}
	
	public void testFilterByIdentifierValue(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByIdentifierValue("idvalue");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE lower(s.identifiers.value) LIKE :identifier", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "identifier"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("identifier"),
        "%idvalue%");
	}
	
	
	public void testFilterByIdentifierValueExactMatch(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByIdentifierValueExactMatch("idvalue");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE lower(s.identifiers.value) LIKE :identifier", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "identifier"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("identifier"),
        "idvalue");
	}
	
	
	
	public void testFilterByIdentifierType(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByIdentifierType("type");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE s.identifiers.type LIKE :idType", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "idType"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("idType"),
        "type");
	}
	
	public void testFilterByShortTitle(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByShortTitle("short");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE lower(s.shortTitle) LIKE :shortTitle", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "shortTitle"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("shortTitle"),
        "%short%");
	}
	
	
	public void testFilterByParticipantFirstName(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantFirstName("fName");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE lower(p.firstName) LIKE :pfName", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "pfName"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("pfName"),
        "%fname%");
	}
	
	public void testFilterByParticipantLastName(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantLastName("lName");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE lower(p.lastName) LIKE :plName", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "plName"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("plName"),
        "%lname%");
	}
	
	
	public void testFilterByParticipantEthnicity(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantEthnicity("Ethnicity");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE lower(p.ethnicity) LIKE :pEthenicity", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "pEthenicity"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("pEthenicity"),
        "%ethnicity%");
	}
	
	public void testFilterByParticipantGender(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantGender("Gender");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE lower(p.gender) LIKE :pGender", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "pGender"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("pGender"),
        "gender");
	}
	
	public void testFilterByParticipantDateOfBirth(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantDateOfBirth("01/01/1970");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE p.dateOfBirth = :pDOB", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "pDOB"));
	}
	
	public void testFilterByParticipantIdentifierValue(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByParticipantIdentifierValue("value");
		assertEquals("wrong parsing for constructor",
                "select s from Study s WHERE lower(pIdentifier.value) LIKE :pIdVal", studyQuery
                                .getQueryString());
        
		assertEquals("wrong number of parameters", studyQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", studyQuery.getParameterMap().containsKey(
        "pIdVal"));
		assertEquals("wrong parameter value", studyQuery.getParameterMap().get("pIdVal"),
        "%value%");
	}
	
	
	public void testFilterBySponsorNameExactMatch(){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.joinStudyOrganization();
		studyQuery.filterByFundingSponsorNameExactMatch("test");
		assertEquals("select s from Study s join s.studyOrganizations as ss WHERE ss.class = 'SFS' AND ss.organization.name = :sponsorName", studyQuery.getQueryString());
	}
}
