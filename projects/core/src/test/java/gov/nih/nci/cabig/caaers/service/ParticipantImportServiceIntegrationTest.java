package gov.nih.nci.cabig.caaers.service;

import static org.easymock.EasyMock.isA;
import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.cabig.caaers.service.migrator.IdentifierMigrator;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import gov.nih.nci.cabig.caaers.service.migrator.ParticipantMigrator;
import gov.nih.nci.cabig.caaers.service.migrator.StudyParticipantAssignmentMigrator;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

/**
 * @author Biju Joseph
 */
public class ParticipantImportServiceIntegrationTest extends AbstractNoSecurityTestCase {

    private ParticipantImportServiceImpl participantImportService;
    private StudySiteDao studySiteDao;

    private ParticipantRepository participantRepository;
    private Participant xstreamParticipant;

    private SystemAssignedIdentifier systemAssignedIdentifier;
    private OrganizationAssignedIdentifier organizationAssignedIdentifier;

    private Organization organization;
    private OrganizationDao organizationDao;

    private StudyParticipantAssignment studyParticipantAssignment;
    private Study study;

    protected void setUp() throws Exception {
        super.setUp();
        participantRepository = registerMockFor(ParticipantRepository.class);
        studySiteDao = registerMockFor(StudySiteDao.class);
        organizationDao = registerMockFor(OrganizationDao.class);
        participantImportService = new ParticipantImportServiceImpl();
        IdentifierMigrator<Participant> idMigrator = new IdentifierMigrator<Participant>();
        StudyParticipantAssignmentMigrator spaMigrator = new StudyParticipantAssignmentMigrator();
        List<Migrator<Participant>> migrators = new ArrayList<Migrator<Participant>>();
        migrators.add(idMigrator);
        migrators.add(spaMigrator);
        ParticipantMigrator migrator = new ParticipantMigrator(migrators);

        spaMigrator.setStudySiteDao(studySiteDao);
        participantImportService.setParticipantRepository(participantRepository);
        idMigrator.setOrganizationDao(organizationDao);
        participantImportService.setParticipantMigrator(migrator);

        xstreamParticipant = Fixtures.createParticipant("first", "last");
        systemAssignedIdentifier = Fixtures.createSystemAssignedIdentifier("value");
        organization = Fixtures.createOrganization("org name");
        organizationAssignedIdentifier = Fixtures.createOrganizationAssignedIdentifier("org value", organization);
        study = Fixtures.createStudy("short study");
        study.setId(1);


    }

    public void testImportParticipantForBasicProperties() {

        DomainObjectImportOutcome<Participant> participantDomainObjectImportOutcome = participantImportService.importParticipant(xstreamParticipant);

        validate(xstreamParticipant, participantDomainObjectImportOutcome);
        validateImportedObject(participantDomainObjectImportOutcome);


        List<DomainObjectImportOutcome.Message> messages = participantDomainObjectImportOutcome.getMessages();
        assertEquals(2, messages.size());

        assertEquals("Identifiers are either Empty or Not Valid", messages.get(0).getMessage());
        assertEquals("Assignments are either Empty or Not Valid", messages.get(1).getMessage());

    }

//    public void testImportParticipantForNonUniqueParticipant() {
//        xstreamParticipant.addIdentifier(organizationAssignedIdentifier);
//        xstreamParticipant.addIdentifier(systemAssignedIdentifier);
//
//        List<Organization> organizations = new ArrayList<Organization>();
//        organizations.add(organization);
//
//        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations);
//        EasyMock.expect(participantRepository.checkIfParticipantExistsForGivenIdentifiers(xstreamParticipant.getIdentifiers())).andReturn(true);
//        replayMocks();
//
//        DomainObjectImportOutcome<Participant> participantDomainObjectImportOutcome = participantImportService.importParticipant(xstreamParticipant);
//        verifyMocks();
//
//        validate(xstreamParticipant, participantDomainObjectImportOutcome);
//        validateImportedObject(participantDomainObjectImportOutcome);
//
//        validateImportedObject(participantDomainObjectImportOutcome);
//        List<DomainObjectImportOutcome.Message> messages = participantDomainObjectImportOutcome.getMessages();
//        assertEquals(2, messages.size());
//
//        assertEquals("Assignments are either Empty or Not Valid", messages.get(0).getMessage());
//        assertEquals("Participant identifier already exists.", messages.get(1).getMessage());
//
//
//    }


    public void testImportParticipantForMigratingIdentifiers() {
        xstreamParticipant.addIdentifier(organizationAssignedIdentifier);
        xstreamParticipant.addIdentifier(systemAssignedIdentifier);

        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations);
        //EasyMock.expect(participantRepository.checkIfParticipantExistsForGivenIdentifiers(xstreamParticipant.getIdentifiers())).andReturn(false);
        replayMocks();

        DomainObjectImportOutcome<Participant> participantDomainObjectImportOutcome = participantImportService.importParticipant(xstreamParticipant);
        verifyMocks();
        validate(xstreamParticipant, participantDomainObjectImportOutcome);
        validateImportedObject(participantDomainObjectImportOutcome);
        List<DomainObjectImportOutcome.Message> messages = participantDomainObjectImportOutcome.getMessages();
        assertEquals(1, messages.size());

        assertEquals("Assignments are either Empty or Not Valid", messages.get(0).getMessage());


    }

    public void testImportParticipantForMigratingAssignmentsIfStudyHasNoIdentifiers() {

        //first migrate assignments when assignments has not identifiers

        studyParticipantAssignment = Fixtures.assignParticipant(xstreamParticipant, study, organization);
        DomainObjectImportOutcome<Participant> participantDomainObjectImportOutcome = participantImportService.importParticipant(xstreamParticipant);
        validate(xstreamParticipant, participantDomainObjectImportOutcome);
        validateImportedObject(participantDomainObjectImportOutcome);
        List<DomainObjectImportOutcome.Message> messages = participantDomainObjectImportOutcome.getMessages();
        assertEquals(1, messages.size());

        assertEquals("Identifiers are either Empty or Not Valid", messages.get(0).getMessage());

    }

    public void testImportParticipantForNoErrors() {
        xstreamParticipant.addIdentifier(organizationAssignedIdentifier);
        xstreamParticipant.addIdentifier(systemAssignedIdentifier);

        // migrate assignments when study has  identifiers
        study.addIdentifier(organizationAssignedIdentifier);
        studyParticipantAssignment = Fixtures.assignParticipant(xstreamParticipant, study, organization);

        StudySite studySite = new StudySite();
        studySite.setId(123);
        EasyMock.expect(studySiteDao.matchByStudyAndOrg(organization.getName(), organizationAssignedIdentifier.getValue(),
                organizationAssignedIdentifier.getType())).andReturn(studySite);
        //EasyMock.expect(participantRepository.checkIfParticipantExistsForGivenIdentifiers(xstreamParticipant.getIdentifiers())).andReturn(false);
        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations);

        replayMocks();

        DomainObjectImportOutcome<Participant> participantDomainObjectImportOutcome = participantImportService.importParticipant(xstreamParticipant);
        verifyMocks();
        validate(xstreamParticipant, participantDomainObjectImportOutcome);
        assertFalse(participantDomainObjectImportOutcome.hasErrors());
        assertTrue(participantDomainObjectImportOutcome.isSavable());
        assertTrue(participantDomainObjectImportOutcome.getMessages().isEmpty());

    }

    public void testImportParticipantForMigratingAssignmentsIfStudySiteIsNotNull() {

        // migrate assignments when study has  identifiers
        study.addIdentifier(organizationAssignedIdentifier);
        studyParticipantAssignment = Fixtures.assignParticipant(xstreamParticipant, study, organization);

        StudySite studySite = new StudySite();
        studySite.setId(123);
        EasyMock.expect(studySiteDao.matchByStudyAndOrg(organization.getName(), organizationAssignedIdentifier.getValue(),
                organizationAssignedIdentifier.getType())).andReturn(studySite);
        //EasyMock.expect(participantRepository.checkIfParticipantExistsForGivenIdentifiers(xstreamParticipant.getIdentifiers())).andReturn(false);
        replayMocks();

        DomainObjectImportOutcome<Participant> participantDomainObjectImportOutcome = participantImportService.importParticipant(xstreamParticipant);
        verifyMocks();
        validate(xstreamParticipant, participantDomainObjectImportOutcome);
        validateImportedObject(participantDomainObjectImportOutcome);
        List<DomainObjectImportOutcome.Message> messages = participantDomainObjectImportOutcome.getMessages();
        assertEquals(1, messages.size());

        assertEquals("Identifiers are either Empty or Not Valid", messages.get(0).getMessage());

    }

    public void testImportParticipantForMigratingAssignmentsIfStudySiteIsNull() {

        // migrate assignments when study has  identifiers
        study.addIdentifier(organizationAssignedIdentifier);
        studyParticipantAssignment = Fixtures.assignParticipant(xstreamParticipant, study, organization);

        EasyMock.expect(studySiteDao.matchByStudyAndOrg(organization.getName(), organizationAssignedIdentifier.getValue(),
                organizationAssignedIdentifier.getType())).andReturn(null);
        //EasyMock.expect(participantRepository.checkIfParticipantExistsForGivenIdentifiers(xstreamParticipant.getIdentifiers())).andReturn(false);

        replayMocks();
        DomainObjectImportOutcome<Participant> participantDomainObjectImportOutcome = participantImportService.importParticipant(xstreamParticipant);
        verifyMocks();
        validate(xstreamParticipant, participantDomainObjectImportOutcome);
        validateImportedObject(participantDomainObjectImportOutcome);
        List<DomainObjectImportOutcome.Message> messages = participantDomainObjectImportOutcome.getMessages();
        assertEquals(2, messages.size());

        assertEquals("Identifiers are either Empty or Not Valid", messages.get(0).getMessage());
        assertEquals("The Study with Identifier \" value:org value \" is either nonexistant or does not match the provided Site", messages.get(1).getMessage());

    }

    private void validateImportedObject(final DomainObjectImportOutcome<Participant> participantDomainObjectImportOutcome) {
        assertTrue(participantDomainObjectImportOutcome.hasErrors());
        assertFalse(participantDomainObjectImportOutcome.isSavable());
    }

    private void validate(final Participant xstreamParticipant, final DomainObjectImportOutcome<Participant> participantDomainObjectImportOutcome) {
        assertNotNull(participantDomainObjectImportOutcome);
        assertNotNull(xstreamParticipant);
        final Participant participant = participantDomainObjectImportOutcome.getImportedDomainObject();

        assertEquals(xstreamParticipant.getFirstName(), participant.getFirstName());
        assertEquals(xstreamParticipant.getLastName(), participant.getLastName());
        assertEquals(xstreamParticipant.getDateOfBirth(), participant.getDateOfBirth());
        assertEquals(xstreamParticipant.getId(), participant.getId());

        assertEquals(xstreamParticipant.getGender(), participant.getGender());

        assertEquals(xstreamParticipant.getMaidenName(), participant.getMiddleName());
        assertEquals(xstreamParticipant.getMaidenName(), participant.getMaidenName());
        assertEquals(xstreamParticipant.getRace(), participant.getRace());
        assertEquals(xstreamParticipant.getEthnicity(), participant.getEthnicity());

        assertEquals(xstreamParticipant.getIdentifiers().size(), participant.getIdentifiers().size());
        for (Identifier identifier : xstreamParticipant.getIdentifiers()) {
            assertTrue(participant.getIdentifiers().contains(identifier));
        }


    }
}
