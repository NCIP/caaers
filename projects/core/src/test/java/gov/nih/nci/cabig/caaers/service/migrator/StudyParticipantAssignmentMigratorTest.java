package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import org.easymock.classextension.EasyMock;


public class StudyParticipantAssignmentMigratorTest extends AbstractNoSecurityTestCase {
	private Participant xstreamParticipant;
	Participant participant;
	DomainObjectImportOutcome<Participant> outcome = new DomainObjectImportOutcome<Participant>();
	private StudySiteDao studySiteDao;
	StudyParticipantAssignmentMigrator migrator;
	private SystemAssignedIdentifier systemAssignedIdentifier;
	private OrganizationAssignedIdentifier organizationAssignedIdentifier;
	Study study;
	Organization organization;
	
	@Override
	protected void setUp() throws Exception {
		 super.setUp();
		 
		 xstreamParticipant = Fixtures.createParticipant("first", "last");
		 systemAssignedIdentifier = Fixtures.createSystemAssignedIdentifier("value");
		 organization = Fixtures.createOrganization("org name");
		 organizationAssignedIdentifier = Fixtures.createOrganizationAssignedIdentifier("org value", organization);
		 study = Fixtures.createStudy("short study");
		 study.setId(1);
		 
		 participant = new Participant();
		 studySiteDao = registerDaoMockFor(StudySiteDao.class);
		 migrator = new StudyParticipantAssignmentMigrator();
		 migrator.setStudySiteDao(studySiteDao);
		 
	}
	 
	public void testMigrate() {

        // migrate assignments when study has  identifiers
        study.addIdentifier(organizationAssignedIdentifier);
        StudyParticipantAssignment studyParticipantAssignment = Fixtures.assignParticipant(xstreamParticipant, study, organization);

        StudySite studySite = new StudySite();
        studySite.setId(123);
        EasyMock.expect(studySiteDao.matchByStudyAndOrg(organization.getName(), organizationAssignedIdentifier.getValue(),
                organizationAssignedIdentifier.getType())).andReturn(studySite);
        replayMocks();

        migrator.migrate(xstreamParticipant, participant, outcome);
        verifyMocks();
        
        //-- assert values 
        assertEquals(1, participant.getAssignments().size());
        
        assertTrue(outcome.getMessages().isEmpty());
	}
	
	public void testMigrate_WithNoAssignment(){
		migrator.migrate(xstreamParticipant, participant, outcome);
		assertFalse(outcome.getMessages().isEmpty());
		Message msg = (Message) outcome.getMessages().get(0);
		assertEquals("incorrect error msg", "Assignments are either Empty or Not Valid", msg.getMessage());
	}
	
	public void testMigrate_WithIncorrectStudySite(){
		// migrate assignments when study has  identifiers
        study.addIdentifier(organizationAssignedIdentifier);
        StudyParticipantAssignment studyParticipantAssignment = Fixtures.assignParticipant(xstreamParticipant, study, organization);

        StudySite studySite = new StudySite();
        studySite.setId(123);
        EasyMock.expect(studySiteDao.matchByStudyAndOrg(organization.getName(), organizationAssignedIdentifier.getValue(),
                organizationAssignedIdentifier.getType())).andReturn(null);
        replayMocks();

        migrator.migrate(xstreamParticipant, participant, outcome);
        verifyMocks();
        assertFalse(outcome.getMessages().isEmpty());
		Message msg = (Message) outcome.getMessages().get(0);
		assertEquals("incorrect error msg", "The Study with Identifier \" value:org value \" is either nonexistant or does not match the provided Site", msg.getMessage());
		assertEquals(1, outcome.getMessages().size());
	}
}
