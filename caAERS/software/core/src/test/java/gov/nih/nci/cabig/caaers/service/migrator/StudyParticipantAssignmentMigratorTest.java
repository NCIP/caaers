package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;

import org.easymock.classextension.EasyMock;


public class StudyParticipantAssignmentMigratorTest extends AbstractNoSecurityTestCase {
	private Participant xstreamParticipant;
	Participant participant;
	DomainObjectImportOutcome<Participant> outcome = new DomainObjectImportOutcome<Participant>();
	private StudySiteDao studySiteDao;
	private OrganizationDao organizationDao;
	private StudyDao studyDao;
	private StudyRepository studyRepository;
	private OrganizationRepository organizationRepository;
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
		 organization = Fixtures.createOrganization("org name",null);
		 organizationAssignedIdentifier = Fixtures.createOrganizationAssignedIdentifier("org value", organization);
		 study = Fixtures.createStudy("short study");
		 study.setId(1);
		 
		 participant = new Participant();
		 studySiteDao = registerDaoMockFor(StudySiteDao.class);
		 organizationDao = registerDaoMockFor(OrganizationDao.class);
		 studyDao = registerDaoMockFor(StudyDao.class);
		 studyRepository = registerMockFor(StudyRepository.class); 
		 organizationRepository = registerMockFor(OrganizationRepository.class);
		 migrator = new StudyParticipantAssignmentMigrator();
		 migrator.setStudySiteDao(studySiteDao);
		 migrator.setOrganizationDao(organizationDao);
		 migrator.setOrganizationRepository(organizationRepository);
		 migrator.setStudyDao(studyDao);
		 migrator.setStudyRepository(studyRepository);
		 
	}
	 
	public void testMigrate() {

        // migrate assignments when study has  identifiers
        study.addIdentifier(organizationAssignedIdentifier);
        StudyParticipantAssignment studyParticipantAssignment = Fixtures.assignParticipant(xstreamParticipant, study, organization);

        StudySite studySite = new StudySite();
        studySite.setId(123);
        EasyMock.expect(organizationDao.getByName(organization.getName())).andReturn(organization);
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
        EasyMock.expect(organizationDao.getByName(organization.getName())).andReturn(organization);
        EasyMock.expect(studySiteDao.matchByStudyAndOrg(organization.getName(), organizationAssignedIdentifier.getValue(),
                organizationAssignedIdentifier.getType())).andReturn(null);
        EasyMock.expect(studyDao.getByIdentifier(organizationAssignedIdentifier)).andReturn(study);
        //studyDao.updateStudyForServiceUseOnly(study);
        studyRepository.save(study);
        replayMocks();

        migrator.migrate(xstreamParticipant, participant, outcome);
        verifyMocks();
        assertTrue(outcome.getMessages().isEmpty());
	}
	
}
