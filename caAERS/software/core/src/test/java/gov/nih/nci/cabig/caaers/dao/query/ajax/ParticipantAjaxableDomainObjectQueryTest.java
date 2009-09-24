package gov.nih.nci.cabig.caaers.dao.query.ajax;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class ParticipantAjaxableDomainObjectQueryTest extends TestCase {
	public void testTest() throws Exception {
		
	}

    public void testQueryConstructor() throws Exception {
        AbstractAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        
        String qry = 
        	
    		"Select participant.id,participant.firstName,participant.lastName"+
    		",participant.gender,participant.race,participant.ethnicity " +
    		",identifier.value,identifier.primaryIndicator " +
    		",study.shortTitle as st , study.id as studyId"+
    		",sIdentifier.value, sIdentifier.primaryIndicator "+
    		",studyOrgs.organization.name,studyOrgs.id,studyOrgs.class, studyOrgs.organization.nciInstituteCode, siteResearchStaff.researchStaff.id " +
    		",ss.organization.id as assignedSiteId, ss.organization.name as assignedSite , ss.organization.nciInstituteCode as assignedSiteCode " +
            "from Participant participant "+
            "left join participant.identifiers as identifier "+
            "left join participant.assignments as spa join spa.studySite as ss "+
            "join ss.study as study "+
            "join study.identifiers as sIdentifier "+
            "join study.studyOrganizations as studyOrgs "+
            "left join studyOrgs.studyPersonnelsInternal as stper " +
            "left join stper.siteResearchStaff as siteResearchStaff " +
            "order by participant.firstName ";
        
        assertEquals("wrong parsing for constructor",qry.trim(), query.getQueryString().trim());

    }

    public void testFilterByStudyId() throws Exception {
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterByStudy(1);        
        
        String qry =     		"Select participant.id,participant.firstName,participant.lastName"+
		",participant.gender,participant.race,participant.ethnicity " +
		",identifier.value,identifier.primaryIndicator " +
		",study.shortTitle as st , study.id as studyId"+
		",sIdentifier.value, sIdentifier.primaryIndicator "+
		",studyOrgs.organization.name,studyOrgs.id,studyOrgs.class, studyOrgs.organization.nciInstituteCode, siteResearchStaff.researchStaff.id " +
		",ss.organization.id as assignedSiteId, ss.organization.name as assignedSite , ss.organization.nciInstituteCode as assignedSiteCode " +
        "from Participant participant "+
        "left join participant.identifiers as identifier "+
        "left join participant.assignments as spa join spa.studySite as ss "+
        "join ss.study as study "+
        "join study.identifiers as sIdentifier "+
        "join study.studyOrganizations as studyOrgs "+
        "left join studyOrgs.studyPersonnelsInternal as stper " +
        "left join stper.siteResearchStaff as siteResearchStaff WHERE study.id =:studyId " +
        "order by participant.firstName ";
        
        assertEquals(qry.trim(), query.getQueryString().trim());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 1);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "studyId"));
        assertEquals("wrong parameter value", query.getParameterMap().get("studyId"),
                Integer.valueOf(1));


    }


    public void testParticipantsWithMatchingText() throws Exception {
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterParticipantsWithMatchingText("a");

        String qry =     		"Select participant.id,participant.firstName,participant.lastName"+
		",participant.gender,participant.race,participant.ethnicity " +
		",identifier.value,identifier.primaryIndicator " +
		",study.shortTitle as st , study.id as studyId"+
		",sIdentifier.value, sIdentifier.primaryIndicator "+
		",studyOrgs.organization.name,studyOrgs.id,studyOrgs.class, studyOrgs.organization.nciInstituteCode, siteResearchStaff.researchStaff.id " +
		",ss.organization.id as assignedSiteId, ss.organization.name as assignedSite , ss.organization.nciInstituteCode as assignedSiteCode " +
        "from Participant participant "+
        "left join participant.identifiers as identifier "+
        "left join participant.assignments as spa join spa.studySite as ss "+
        "join ss.study as study "+
        "join study.identifiers as sIdentifier "+
        "join study.studyOrganizations as studyOrgs "+
        "left join studyOrgs.studyPersonnelsInternal as stper " +
        "left join stper.siteResearchStaff as siteResearchStaff WHERE " +
        "(lower(participant.firstName) LIKE :firstName or lower(participant.lastName) LIKE :lastName or  lower(identifier.type) LIKE :type or lower(identifier.value) LIKE :identifierValue" +
        " or lower(spa.studySubjectIdentifier) LIKE :studySubjectIdentifier) "+
        "order by participant.firstName ";
        
        assertEquals(qry.trim(),
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 5);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "lastName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("lastName"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "type"));
        assertEquals("wrong parameter value", query.getParameterMap().get("type"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "identifierValue"));
        assertEquals("wrong parameter value", query.getParameterMap().get("identifierValue"),
                "%a%");
        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "firstName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("firstName"),
                "%a%");


    }

    public void testFilterByBothParticipantAndText() throws Exception {
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterParticipantsWithMatchingText("a");
        query.filterByStudy(1);
        
        String qry =     		"Select participant.id,participant.firstName,participant.lastName"+
		",participant.gender,participant.race,participant.ethnicity " +
		",identifier.value,identifier.primaryIndicator " +
		",study.shortTitle as st , study.id as studyId"+
		",sIdentifier.value, sIdentifier.primaryIndicator "+
		",studyOrgs.organization.name,studyOrgs.id,studyOrgs.class, studyOrgs.organization.nciInstituteCode, siteResearchStaff.researchStaff.id " +
		",ss.organization.id as assignedSiteId, ss.organization.name as assignedSite , ss.organization.nciInstituteCode as assignedSiteCode " +
        "from Participant participant "+
        "left join participant.identifiers as identifier "+
        "left join participant.assignments as spa join spa.studySite as ss "+
        "join ss.study as study "+
        "join study.identifiers as sIdentifier "+
        "join study.studyOrganizations as studyOrgs "+
        "left join studyOrgs.studyPersonnelsInternal as stper " +
        "left join stper.siteResearchStaff as siteResearchStaff WHERE " +
        "(lower(participant.firstName) LIKE :firstName or lower(participant.lastName) LIKE :lastName or  lower(identifier.type) LIKE :type or lower(identifier.value) LIKE :identifierValue " +
        "or lower(spa.studySubjectIdentifier) LIKE :studySubjectIdentifier) AND study.id =:studyId "+
        "order by participant.firstName ";
        
        
        assertEquals(qry.trim(),
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 6);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "lastName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("lastName"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "type"));
        assertEquals("wrong parameter value", query.getParameterMap().get("type"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "identifierValue"));
        assertEquals("wrong parameter value", query.getParameterMap().get("identifierValue"),
                "%a%");
        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "firstName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("firstName"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "studyId"));
        assertEquals("wrong parameter value", query.getParameterMap().get("studyId"),
                Integer.valueOf(1));
    }
    
    public void testFilterByPrimaryIdentifiers(){
    	ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
    	query.filterByPrimaryIdentifiers();
    	
    	String qry = 
        	
    		"Select participant.id,participant.firstName,participant.lastName"+
    		",participant.gender,participant.race,participant.ethnicity " +
    		",identifier.value,identifier.primaryIndicator " +
    		",study.shortTitle as st , study.id as studyId"+
    		",sIdentifier.value, sIdentifier.primaryIndicator "+
    		",studyOrgs.organization.name,studyOrgs.id,studyOrgs.class, studyOrgs.organization.nciInstituteCode, siteResearchStaff.researchStaff.id " +
    		",ss.organization.id as assignedSiteId, ss.organization.name as assignedSite , ss.organization.nciInstituteCode as assignedSiteCode " +
            "from Participant participant "+
            "left join participant.identifiers as identifier "+
            "left join participant.assignments as spa join spa.studySite as ss "+
            "join ss.study as study "+
            "join study.identifiers as sIdentifier "+
            "join study.studyOrganizations as studyOrgs "+
            "left join studyOrgs.studyPersonnelsInternal as stper " +
            "left join stper.siteResearchStaff as siteResearchStaff " +
            "WHERE identifier.primaryIndicator is true and sIdentifier.primaryIndicator is true "+
            "order by participant.firstName ";
    	
    	assertEquals(qry.trim(),
                query.getQueryString());
    	
    }

    
    public void testFilterByStudyIdentifierValue(){
    	ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
    	query.filterByStudyIdentifierValue("idvalue");
    	
    	String qry = 
        	
    		"Select participant.id,participant.firstName,participant.lastName"+
    		",participant.gender,participant.race,participant.ethnicity " +
    		",identifier.value,identifier.primaryIndicator " +
    		",study.shortTitle as st , study.id as studyId"+
    		",sIdentifier.value, sIdentifier.primaryIndicator "+
    		",studyOrgs.organization.name,studyOrgs.id,studyOrgs.class, studyOrgs.organization.nciInstituteCode, siteResearchStaff.researchStaff.id " +
    		",ss.organization.id as assignedSiteId, ss.organization.name as assignedSite , ss.organization.nciInstituteCode as assignedSiteCode " +
            "from Participant participant "+
            "left join participant.identifiers as identifier "+
            "left join participant.assignments as spa join spa.studySite as ss "+
            "join ss.study as study "+
            "join study.identifiers as sIdentifier "+
            "join study.studyOrganizations as studyOrgs "+
            "left join studyOrgs.studyPersonnelsInternal as stper " +
            "left join stper.siteResearchStaff as siteResearchStaff " +
            "WHERE lower(sIdentifier.value) LIKE :studyIdentifierValue "+
            "order by participant.firstName ";
    	
    	assertEquals(qry.trim(),
                query.getQueryString());
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "studyIdentifierValue"));
    	
    	assertEquals("wrong parameter value", query.getParameterMap().get("studyIdentifierValue"),
        "%idvalue%");
    	
    }
    
    public void testFilterByStudyShortTitle(){
    	ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
    	query.filterByStudyShortTitle("shorttitle");
    	
    	String qry = 
        	
    		"Select participant.id,participant.firstName,participant.lastName"+
    		",participant.gender,participant.race,participant.ethnicity " +
    		",identifier.value,identifier.primaryIndicator " +
    		",study.shortTitle as st , study.id as studyId"+
    		",sIdentifier.value, sIdentifier.primaryIndicator "+
    		",studyOrgs.organization.name,studyOrgs.id,studyOrgs.class, studyOrgs.organization.nciInstituteCode, siteResearchStaff.researchStaff.id " +
    		",ss.organization.id as assignedSiteId, ss.organization.name as assignedSite , ss.organization.nciInstituteCode as assignedSiteCode " +
            "from Participant participant "+
            "left join participant.identifiers as identifier "+
            "left join participant.assignments as spa join spa.studySite as ss "+
            "join ss.study as study "+
            "join study.identifiers as sIdentifier "+
            "join study.studyOrganizations as studyOrgs "+
            "left join studyOrgs.studyPersonnelsInternal as stper " +
            "left join stper.siteResearchStaff as siteResearchStaff " +
            "WHERE lower(study.shortTitle) LIKE :shortTitle "+
            "order by participant.firstName ";
    	
    	assertEquals(qry.trim(),
                query.getQueryString());
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "shortTitle"));
    	
    	assertEquals("wrong parameter value", query.getParameterMap().get("shortTitle"),
        "%shorttitle%");
    }
    
    public void testFilterStudiesWithShortTitle(){
    	
    	ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
    	query.filterStudiesWithShortTitle("shorttitle");
    	
    	String qry = 
        	
    		"Select participant.id,participant.firstName,participant.lastName"+
    		",participant.gender,participant.race,participant.ethnicity " +
    		",identifier.value,identifier.primaryIndicator " +
    		",study.shortTitle as st , study.id as studyId"+
    		",sIdentifier.value, sIdentifier.primaryIndicator "+
    		",studyOrgs.organization.name,studyOrgs.id,studyOrgs.class, studyOrgs.organization.nciInstituteCode, siteResearchStaff.researchStaff.id " +
    		",ss.organization.id as assignedSiteId, ss.organization.name as assignedSite , ss.organization.nciInstituteCode as assignedSiteCode " +
            "from Participant participant "+
            "left join participant.identifiers as identifier "+
            "left join participant.assignments as spa join spa.studySite as ss "+
            "join ss.study as study "+
            "join study.identifiers as sIdentifier "+
            "join study.studyOrganizations as studyOrgs "+
            "left join studyOrgs.studyPersonnelsInternal as stper " +
            "left join stper.siteResearchStaff as siteResearchStaff " +
            "WHERE (lower(study.shortTitle) LIKE :shortTitle ) "+
            "order by participant.firstName ";
    	
    	assertEquals(qry.trim(),
                query.getQueryString());
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "shortTitle"));
    	
    	assertEquals("wrong parameter value", query.getParameterMap().get("shortTitle"),
        "%shorttitle%");
    	
    }
    
    public void testFilterParticipants(){
    	
    	ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
    	
    	Map<String,String> searchMap = new HashMap<String,String>();
    	searchMap.put("participantIdentifier", "participantIdentifier");
    	searchMap.put("participantFirstName", "FirstName");
    	searchMap.put("participantLastName", "LastName");
    	searchMap.put("participantEthnicity", "Ethnicity");
    	searchMap.put("participantGender", "Gender");
    	searchMap.put("participantDateOfBirth", "01/01/1980");
    	
    	try {
			query.filterParticipants(searchMap);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String qry = 
        	
    		"Select participant.id,participant.firstName,participant.lastName"+
    		",participant.gender,participant.race,participant.ethnicity " +
    		",identifier.value,identifier.primaryIndicator " +
    		",study.shortTitle as st , study.id as studyId"+
    		",sIdentifier.value, sIdentifier.primaryIndicator "+
    		",studyOrgs.organization.name,studyOrgs.id,studyOrgs.class, studyOrgs.organization.nciInstituteCode, siteResearchStaff.researchStaff.id " +
    		",ss.organization.id as assignedSiteId, ss.organization.name as assignedSite , ss.organization.nciInstituteCode as assignedSiteCode " +
            "from Participant participant "+
            "left join participant.identifiers as identifier "+
            "left join participant.assignments as spa join spa.studySite as ss "+
            "join ss.study as study "+
            "join study.identifiers as sIdentifier "+
            "join study.studyOrganizations as studyOrgs "+
            "left join studyOrgs.studyPersonnelsInternal as stper " +
            "left join stper.siteResearchStaff as siteResearchStaff " +
            "WHERE lower(identifier.value) LIKE :identifierValue AND lower(participant.firstName) LIKE :firstName AND "+
            "lower(participant.lastName) LIKE :lastName AND participant.ethnicity = :ethnicity AND participant.gender = :race AND  "+
            "participant.dateOfBirth.year = :year AND  participant.dateOfBirth.month = :month AND  participant.dateOfBirth.day = :day "+
            "order by participant.firstName ";
    	
    	assertEquals(qry.trim(),
                query.getQueryString());
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "identifierValue"));
    	assertEquals("wrong parameter value", query.getParameterMap().get("identifierValue"),
        "%participantidentifier%");
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "firstName"));
    	assertEquals("wrong parameter value", query.getParameterMap().get("firstName"),
        "%firstname%");
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "lastName"));
    	assertEquals("wrong parameter value", query.getParameterMap().get("lastName"),
        "%lastname%");
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "ethnicity"));
    	assertEquals("wrong parameter value", query.getParameterMap().get("ethnicity"),
        "Ethnicity");
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "race"));
    	assertEquals("wrong parameter value", query.getParameterMap().get("race"),
        "Gender");
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "year"));
    	assertEquals("wrong parameter value", query.getParameterMap().get("year"),
        1980);
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "month"));
    	assertEquals("wrong parameter value", query.getParameterMap().get("month"),
        1);
    	
    	assertTrue("missing paramenter name", query.getParameterMap().containsKey(
        "day"));
    	assertEquals("wrong parameter value", query.getParameterMap().get("day"),
        1);
    	
    }
    
}