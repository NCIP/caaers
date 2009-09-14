package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParticipantSynchronizerIntegrationTest extends AbstractNoSecurityTestCase {
	
	Participant dbParticipant,xmlParticipant;
	StudyParticipantAssignmentSynchronizer studyParticipantAssignmentSynchronizer;
	IdentifierSynchronizer<Participant> identifierSynchronizer;
	ParticipantSynchronizer participantSynchronizer;
	DomainObjectImportOutcome<Participant> outcome;
	
	StudyParticipantAssignment studyParticipantAssignment1,studyParticipantAssignment2,studyParticipantAssignment1a,
							   studyParticipantAssignment2a,studyParticipantAssignment3a;
	StudySite studySite1,studySite2,studySite1a,studySite2a;
	Study study1,study2,study1a,study2a;
	Identifier identifier1,identifier1a,identifier2a ;
	Organization organization1,organization1a,organization2a;
	OrganizationAssignedIdentifier orgIdentifier1,orgIdentifier1a,orgIdentifier2a;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studyParticipantAssignmentSynchronizer = new StudyParticipantAssignmentSynchronizer();
		identifierSynchronizer = new IdentifierSynchronizer<Participant>();
		List<Migrator<Participant>> syncronizers = new ArrayList<Migrator<Participant>>();
		syncronizers.add(identifierSynchronizer);
		syncronizers.add(studyParticipantAssignmentSynchronizer);
		participantSynchronizer = new ParticipantSynchronizer();
		participantSynchronizer.setChildren(syncronizers);
		outcome = new DomainObjectImportOutcome<Participant>();
	}
	
	public void testSync(){
		study1 = Fixtures.createStudy("abc");
		identifier1 = new Identifier();
		identifier1.setType("type");
		identifier1.setValue("value");
		study1.addIdentifier(identifier1);
		organization1 = Fixtures.createOrganization("organization");
		studySite1 = new StudySite();
		studySite1.setStudy(study1);
		studySite1.setOrganization(organization1);
		studySite1.setId(1);
		orgIdentifier1 = Fixtures.createOrganizationAssignedIdentifier("1", organization1);
		orgIdentifier1.setId(1);
		dbParticipant = Fixtures.createParticipant("first", "last");
		dbParticipant.setId(1);
		dbParticipant.addIdentifier(orgIdentifier1);
		studyParticipantAssignment1 = new StudyParticipantAssignment();
		studyParticipantAssignment1.setStudySite(studySite1);
		studyParticipantAssignment1.setId(1);
		dbParticipant.addAssignment(studyParticipantAssignment1);
		
		List<StudyParticipantAssignment> assignmentList = new ArrayList<StudyParticipantAssignment>();
		study1a = Fixtures.createStudy("abc");
		identifier1a = new Identifier();
		identifier1a.setType("type");
		identifier1a.setValue("value");
		study1a.addIdentifier(identifier1a);
		organization1a = Fixtures.createOrganization("organization");
		studySite1a = new StudySite();
		studySite1a.setStudy(study1a);
		studySite1a.setOrganization(organization1a);
		orgIdentifier1a = Fixtures.createOrganizationAssignedIdentifier("1", organization1);
		orgIdentifier2a = Fixtures.createOrganizationAssignedIdentifier("2", organization2a);
		xmlParticipant = Fixtures.createParticipant("first", "last");
		xmlParticipant.addIdentifier(orgIdentifier1a);
		xmlParticipant.addIdentifier(orgIdentifier2a);
		studyParticipantAssignment1a = new StudyParticipantAssignment();
		studyParticipantAssignment1a.setStudySite(studySite1a);
		assignmentList.add(studyParticipantAssignment1a);
		
		study2a = Fixtures.createStudy("abc2");
		identifier2a = new Identifier();
		identifier2a.setType("type1");
		identifier2a.setValue("value1");
		study2a.addIdentifier(identifier2a);
		organization2a = Fixtures.createOrganization("organization2");
		studySite2a = new StudySite();
		studySite2a.setStudy(study2a);
		studySite2a.setOrganization(organization2a);
		studyParticipantAssignment2a = new StudyParticipantAssignment();
		studyParticipantAssignment2a.setStudySite(studySite2a);
		assignmentList.add(studyParticipantAssignment2a);
		xmlParticipant.setAssignments(assignmentList);
		
		xmlParticipant.setMiddleName("MiddleName");
		xmlParticipant.setMaidenName("MaidenName");
		xmlParticipant.setGender("Male");
		xmlParticipant.setEthnicity("Ethnicity");
		xmlParticipant.setRace("Asian");
		xmlParticipant.setDateOfBirth(new DateValue(11,11,1965));
		
		assertEquals(1, dbParticipant.getIdentifiers().size());
		assertEquals(2, xmlParticipant.getIdentifiers().size());
		
		assertEquals(1, dbParticipant.getAssignments().size());
		assertEquals(2, xmlParticipant.getAssignments().size());
		
		participantSynchronizer.migrate(dbParticipant, xmlParticipant, outcome);
		
		assertEquals("MiddleName", dbParticipant.getMiddleName());
		assertEquals("MaidenName", dbParticipant.getMaidenName());
		assertEquals("Male", dbParticipant.getGender());
		assertEquals("Ethnicity", dbParticipant.getEthnicity());
		assertEquals("Asian", dbParticipant.getRace());
		assertEquals(new DateValue(11,11,1965), dbParticipant.getDateOfBirth());
		assertEquals(2, dbParticipant.getIdentifiers().size());
		assertEquals(2, dbParticipant.getAssignments().size());
		
	}

}
