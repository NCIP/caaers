package gov.nih.nci.cabig.caaers.web.study;

import edu.emory.mathcs.backport.java.util.Arrays;
import gov.nih.nci.cabig.caaers.dao.query.StudyHavingStudySiteQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudySitesQuery;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.easymock.classextension.EasyMock;

public class SearchStudyAjaxFacadeTest extends DwrFacadeTestCase{
	
	private SearchStudyAjaxFacade facade ;
	private StudyRepository studyRepository;
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
    
    
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		facade = new SearchStudyAjaxFacade();
		studyRepository = registerMockFor(StudyRepository.class);
		facade.setStudyRepository(studyRepository);
		
		studySearchableAjaxableDomainObjectRepository = registerMockFor(StudySearchableAjaxableDomainObjectRepository.class);
		facade.setStudySearchableAjaxableDomainObjectRepository(studySearchableAjaxableDomainObjectRepository);
	}
	
	
	public void testGetStudiesTable() throws Exception{
		
		List<Study> studies = new ArrayList<Study>();
		Study study = new LocalStudy();
		study.setShortTitle("shortTitle");
		study.setExternalId("externalId");
		study.setPrimaryIdentifierValue("primaryId");
		study.setPhaseCode("Phase II");
		
		OrganizationAssignedIdentifier fundingSponsorId = new OrganizationAssignedIdentifier();
		fundingSponsorId.setOrganization(new LocalOrganization());
		fundingSponsorId.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
		fundingSponsorId.setValue("fundingSponsorValue");
		study.addIdentifier(fundingSponsorId);
		
		study.setStatus("Active");
		study.setDataEntryStatus(true);
		study.setId(11);
		studies.add(study);
		
		EasyMock.expect(studyRepository.getAllStudiesByShortTitleOrIdentifiers("text")).andReturn(studies);
		replayMocks();
		List<StudyAjaxableDomainObject> ajaxStudies = facade.getStudiesTable(new HashMap<String,Object>(), "type", "text", request);
		verifyMocks();
		
		assertEquals(1,ajaxStudies.size());
		assertEquals("shortTitle",ajaxStudies.get(0).getShortTitle());
		assertEquals("fundingSponsorValue",ajaxStudies.get(0).getFundingSponsorIdentifierValue());
		assertEquals("Phase II",ajaxStudies.get(0).getPhaseCode());
		assertEquals("primaryId",ajaxStudies.get(0).getPrimaryIdentifierValue());
		
	}
	
	public void testGetStudiesForCreateParticipant() throws Exception{
		
		List<Study> studies = new ArrayList<Study>();
		Study study = new LocalStudy();
		study.setShortTitle("shortTitle");
		study.setExternalId("externalId");
		study.setPrimaryIdentifierValue("primaryId");
		study.setPhaseCode("Phase II");
		
		OrganizationAssignedIdentifier fundingSponsorId = new OrganizationAssignedIdentifier();
		fundingSponsorId.setOrganization(new LocalOrganization());
		fundingSponsorId.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
		fundingSponsorId.setValue("fundingSponsorValue");
		study.addIdentifier(fundingSponsorId);
		
		study.setStatus("Active");
		study.setDataEntryStatus(true);
		study.setId(11);
		studies.add(study);
		
		EasyMock.expect(studyRepository.find((StudyHavingStudySiteQuery) EasyMock.anyObject())).andReturn(studies);
		replayMocks();
		List<StudyAjaxableDomainObject> ajaxStudies = facade.getStudiesForCreateParticipant(new HashMap<String,Object>(), "type", "text","NC010", request);
		verifyMocks();
		
		assertEquals(1,ajaxStudies.size());
		assertEquals("shortTitle",ajaxStudies.get(0).getShortTitle());
		assertEquals("fundingSponsorValue",ajaxStudies.get(0).getFundingSponsorIdentifierValue());
		assertEquals("Phase II",ajaxStudies.get(0).getPhaseCode());
		assertEquals("primaryId",ajaxStudies.get(0).getPrimaryIdentifierValue());
		
	}

	public void testGetTableForAssignParticipants() throws Exception{

	Organization org = new LocalOrganization();
	org.setNciInstituteCode("AR060");
	
	Study study = new LocalStudy();
	study.setShortTitle("shortTitle");
	study.setExternalId("externalId");
	study.setPrimaryIdentifierValue("primaryId");
	study.setPhaseCode("Phase II");
	
	OrganizationAssignedIdentifier fundingSponsorId = new OrganizationAssignedIdentifier();
	fundingSponsorId.setOrganization(new LocalOrganization());
	fundingSponsorId.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
	fundingSponsorId.setValue("fundingSponsorValue");
	study.addIdentifier(fundingSponsorId);
	
	StudyFundingSponsor sponsor = new StudyFundingSponsor();
	sponsor.setPrimary(true);
	sponsor.setOrganization(org);
	
	study.addStudyOrganization(sponsor);
	
	study.setStatus("Active");
	study.setDataEntryStatus(true);
	study.setId(11);
	
	List<StudySite> sites = new ArrayList<StudySite>();
	StudySite site = new StudySite();
	site.setStudy(study);
	
	StudyParticipantAssignment assignment = new StudyParticipantAssignment();
	Participant participant = new Participant();
	participant.setLastName("Doe");
	assignment.setParticipant(participant);
	site.setStudyParticipantAssignments(Arrays.asList(new StudyParticipantAssignment[] {assignment}));
	site.setOrganization(org);
	
	sites.add(site);
	
	EasyMock.expect(studyRepository.search((StudySitesQuery)EasyMock.anyObject(), (String)EasyMock.anyObject(), (String)EasyMock.anyObject(), EasyMock.anyBoolean())).andReturn(sites);
	replayMocks();
	request.setParameter("organizationID", "2");
	List<StudySiteAjaxableDomainObject> results = facade.getTableForAssignParticipant(new HashMap<String,Object>(), "st", "text", request);
	verifyMocks();
	
	assertEquals(1,results.size());
	assertEquals("shortTitle",results.get(0).getStudyShortTitle());
	assertEquals("fundingSponsorValue",results.get(0).getFundingSponsorIdentifierValue());
	assertEquals("Phase II",results.get(0).getStudyPhase());
	assertEquals("AR060",results.get(0).getNciInstituteCode());
	
}

}
