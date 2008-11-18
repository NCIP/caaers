package gov.nih.nci.cabig.caaers.validator;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDomainObjectValidatorTest extends CaaersTestCase{
	
	Participant participant;
	List<String> errors;
	DomainObjectValidator domainObjectValidator;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		participant = Fixtures.createParticipant("John", "Doe");
		errors = new ArrayList<String>();
		domainObjectValidator = (DomainObjectValidator)getDeployedApplicationContext().getBean("domainObjectValidator");
	}
	
	public void testDuplicateOrganizationAssignedIdentifiers(){
		
		Organization org1 = Fixtures.createOrganization("NICE");
		Identifier id1 = Fixtures.createOrganizationAssignedIdentifier("NICE-1", org1);
		Identifier id2 = Fixtures.createOrganizationAssignedIdentifier("NICE-1", org1);
		participant.addIdentifier(id1);
		participant.addIdentifier(id2);
		
		errors =  domainObjectValidator.validate(participant);
		assertEquals(2, errors.size());
	}
	
	public void testDuplicateSystemAssignedIdentifiers(){
		
		Identifier id1 = Fixtures.createSystemAssignedIdentifier("NICE-SYS");
		Identifier id2 = Fixtures.createSystemAssignedIdentifier("NICE-SYS");
		participant.addIdentifier(id1);
		participant.addIdentifier(id2);

		errors =  domainObjectValidator.validate(participant);
		assertEquals(2, errors.size());
	}
	
	public void testUniqueIdentfiers(){
		Organization org1 = Fixtures.createOrganization("NICE");
		Organization org2 = Fixtures.createOrganization("PICE");
		Identifier id1 = Fixtures.createOrganizationAssignedIdentifier("NICE-1", org1);
		Identifier id2 = Fixtures.createOrganizationAssignedIdentifier("PICE-2", org1);
		Identifier id3 = Fixtures.createSystemAssignedIdentifier("NICE-SYS-1");
		Identifier id4 = Fixtures.createSystemAssignedIdentifier("NICE-SYS-2");
		participant.addIdentifier(id1);
		participant.addIdentifier(id2);
		participant.addIdentifier(id3);
		participant.addIdentifier(id4);
		
		errors =  domainObjectValidator.validate(participant);
		
		assertEquals(0,errors.size());
	}
	
	
	public void testDuplicateAssignment(){
		Study study = Fixtures.createStudy("short title");
		Organization organization = Fixtures.createOrganization("CTEP");
		Identifier orgIdentifier = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization);
		
		StudySite ss1 = new StudySite();
		ss1.setOrganization(organization);
		ss1.setStudy(study);
		study.addIdentifier(orgIdentifier);
		study.addStudyOrganization(ss1);
		
		StudySite ss2 = new StudySite();
		ss2.setOrganization(organization);
		ss2.setStudy(study);
		study.addIdentifier(orgIdentifier);
		study.addStudyOrganization(ss2);
		
		StudyParticipantAssignment sp1 = new StudyParticipantAssignment();
		sp1.setParticipant(participant);
		sp1.setStudySite(ss1);
		sp1.setId(1);
		
		StudyParticipantAssignment sp2 = new StudyParticipantAssignment();
		sp2.setParticipant(participant);
		sp2.setStudySite(ss2);
		sp2.setId(1);

		
		participant.addAssignment(sp1);
		participant.addAssignment(sp2);
		
		
		boolean eq = ss1.equals(ss2);
		boolean eq1 = sp1.equals(sp2);
		
		assertEquals(true, eq);
		assertEquals(true, eq1);
		
	}
	
	public void testDuplicateParticipants(){
		
		Participant p1 = Fixtures.createParticipant("first", "last");
		Participant p2 = Fixtures.createParticipant("JO", "Sa");
		Organization org1 = Fixtures.createOrganization("NICE");
		Identifier id1 = Fixtures.createOrganizationAssignedIdentifier("NICE-1", org1);
		Identifier id2 = Fixtures.createOrganizationAssignedIdentifier("NICE-1", org1);
		p1.addIdentifier(id1);
		p2.addIdentifier(id2);
		
		
		boolean eq = p1.equals(p2);
		assertEquals(true, p1.equals(p2));
	}
	
	public void testUniqueParticipants(){
		
		Participant p1 = Fixtures.createParticipant("first", "last");
		Participant p2 = Fixtures.createParticipant("JO", "Sa");
		Organization org1 = Fixtures.createOrganization("NICE");
		Organization org2 = Fixtures.createOrganization("PICE");
		Identifier id1 = Fixtures.createOrganizationAssignedIdentifier("NICE-1", org1);
		Identifier id2 = Fixtures.createOrganizationAssignedIdentifier("NICE-2", org2);
		p1.addIdentifier(id1);
		p2.addIdentifier(id2);
		
		assertEquals(false, p1.equals(p2));
	}

}
