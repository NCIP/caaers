package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.ASSIGN_PARTICIPANT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_PARTICIPANT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.IMPORT_PARTICIPANTS;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.LoadStatus;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.StatementCallback;

/**
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */

@CaaersUseCases({CREATE_PARTICIPANT, ASSIGN_PARTICIPANT, IMPORT_PARTICIPANTS})
public class ParticipantDaoTest extends DaoNoSecurityTestCase<ParticipantDao> {

    private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");

    public void testGetById() throws Exception {
        Participant participant = getDao().getById(-100);
        assertNotNull("Participant not found", participant);
        assertEquals("Wrong last name", "Scott", participant.getLastName());
        assertEquals("Wrong first name", "Dilbert", participant.getFirstName());
        assertEquals("Wrong number of identifiers", 2, participant.getIdentifiers().size());
    }

    public void testGetIsReadOnly() throws Exception {
        {
            Participant participant = getDao().getById(-100);
            assertEquals("Wrong number of identifiers initially", 2, participant.getIdentifiers()
                    .size());
            participant.getIdentifiers().clear();
        }

        interruptSession();

        {
            Participant participant = getDao().getById(-100);
            assertEquals("Identifiers incorrectly purged", 2, participant.getIdentifiers().size());
        }
    }

    public void testSaveAssignment() throws Exception {
        {
            Organization organization = organizationDao.getById(-1001);
            StudySite studySite = organization.getStudySites().get(0);
            assertEquals("Wrong study site found in test setup", -3001, (int) studySite.getId());
            Participant participant = getDao().getById(-100);
            assertEquals("Participant should already have one assignment", 1, participant
                    .getAssignments().size());

            StudyParticipantAssignment spa = new StudyParticipantAssignment();
            spa.setParticipant(participant);
            spa.setStudySite(studySite);
            spa.setDateOfEnrollment(new Date());

            participant.addAssignment(spa);

            getDao().save(participant);
        }

        interruptSession();

        Participant loaded = getDao().getById(-100);
        assertNotNull("Participant reloading failed", loaded);
        assertEquals("Assignment not saved", 2, loaded.getAssignments().size());
        StudyParticipantAssignment newAssignment = loaded.getAssignments().get(1);
        assertEquals("Wrong participant", -100, (int) newAssignment.getParticipant().getId());
        assertEquals("Wrong study site", -3001, (int) newAssignment.getStudySite().getId());
        // assertSameDay("Wrong start date", new Date(), newAssignment.getDateOfEnrollment());
    }

    public void testSaveNewParticipant() throws Exception {
        Integer savedId;
        {
            Participant participant = new Participant();
            participant.setFirstName("Jeff");
            participant.setLastName("Someone");
            participant.setGender("Male");
            participant.setDateOfBirth(new DateValue());
            participant.setEthnicity("ethnicity");
            participant.setRace("race");

            getDao().save(participant);
            savedId = participant.getId();
            assertNotNull("The saved participant id", savedId);
        }

        interruptSession();

        {
            Participant loaded = getDao().getById(savedId);
            assertNotNull("Could not reload participant id " + savedId, loaded);
            assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
            assertEquals("Wrong lastname", "Someone", loaded.getLastName());
            assertEquals("Wrong gender", "Male", loaded.getGender());
        }
    }

    public void testSaveNewParticipantWithLoadStatusInprogress() throws Exception {
        final Integer savedId;
        {
            Participant participant = new Participant();
            participant.setFirstName("Jeff");
            participant.setLastName("Someone");
            participant.setGender("Male");
            participant.setDateOfBirth(new DateValue());
            participant.setEthnicity("ethnicity");
            participant.setRace("race");
            participant.setLoadStatus(LoadStatus.INPROGRESS.getCode());
            getDao().save(participant);
            savedId = participant.getId();
            assertNotNull("The saved participant id", savedId);
        }

        interruptSession();
        Participant retrievedParticipant = (Participant) getJdbcTemplate().execute(
                new StatementCallback() {
                    public Object doInStatement(Statement st) throws SQLException,
                            DataAccessException {
                        ResultSet rs = st
                                .executeQuery("select * from participants where id = "
                                        + savedId.toString());
                        rs.next();
                        Participant p = new Participant();
                        p.setFirstName(rs.getString("first_name"));
                        return p;
                    }
                });

        assertEquals("The name of the retrieved should be Jeff", "Jeff", retrievedParticipant
                .getFirstName());
    }

    public void testGetBySubnameMatchesFirstName() throws Exception {
        List<Participant> matches = getDao().getBySubnames(new String[]{"icha"});
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", -101, (int) matches.get(0).getId());
    }

    public void testGetBySubnameAfterUpdatingLoadStatus() throws Exception {
        final int participantId = -99;
        Participant participant = getDao().getById(-99);
        assertNull("Participant (-99) should be null ", participant);
        getJdbcTemplate().execute(new StatementCallback() {
            public Object doInStatement(Statement st) throws SQLException, DataAccessException {
                st
                        .addBatch("update participant_assignments set load_status = 1 where participant_id = "
                                + participantId);
                st.addBatch("update participants set load_status = 1 where id = " + participantId);
                return st.executeBatch();

            }
        });
        interruptSession();
        Participant participantLoaded = getDao().getById(-99);
        assertNotNull("Now it should load (-99) participant", participantLoaded);
    }

    public void testGetBySubnameMatchesLastName() throws Exception {
        List<Participant> matches = getDao().getBySubnames(new String[]{"cot"});
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", -100, (int) matches.get(0).getId());
    }

    public void testGetBySubnameMatchesInstitutionalId() throws Exception {
        List<Participant> matches = getDao().getBySubnames(new String[]{"P002"});
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", -101, (int) matches.get(0).getId());
    }

    public void testGetBySubnameWithNullSubnamesReturnsNothing() throws Exception {
        List<Participant> actual = getDao().getBySubnames(null);
        assertEquals(0, actual.size());
    }

    public void testGetBySubnameWithNoSubnamesReturnsNothing() throws Exception {
        List<Participant> actual = getDao().getBySubnames(new String[]{});
        assertEquals(0, actual.size());
    }

    public void testGetBySubnameMatchesIntersectionOfMultiple() throws Exception {
        List<Participant> matches;

        matches = getDao().getBySubnames(new String[]{"Jor", "P001"});
        assertEquals("Should be no matches", 0, matches.size());

        matches = getDao().getBySubnames(new String[]{"Jor", "P002"});
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", -101, (int) matches.get(0).getId());
    }

    public void testSearchParticipantPropertyExistance() throws Exception {
    	Class person = Person.class;
    	assertNotNull(person.getDeclaredField("firstName"));
    	assertNotNull(person.getDeclaredField("lastName"));
        Class participant = gov.nih.nci.cabig.caaers.domain.Participant.class;
        assertNotNull(participant.getDeclaredField("gender"));
        assertNotNull(participant.getDeclaredField("ethnicity"));

        Class study = gov.nih.nci.cabig.caaers.domain.Study.class;
        assertNotNull(study.getDeclaredField("shortTitle"));

        Class identifier = gov.nih.nci.cabig.caaers.domain.Identifier.class;
        assertNotNull(identifier.getDeclaredField("value"));
    }



    public void testDeleteAssignments() {
        Participant participant = getDao().getById(-100);
        assertNotNull("Participant (-100) should not be null ", participant);

        StudyParticipantAssignment spa = participant.getAssignments().get(0);
        int oldSize = participant.getAssignments().size();

        participant.getAssignments().remove(spa);
        getDao().save(participant);
        interruptSession();
        participant = getDao().getById(-100);
        assertNotNull("Participant (-100) should be null ", participant);
        int newSize = participant.getAssignments().size();
        assertEquals("The size of the participant assignment should be one less", oldSize - 1, newSize);
    }

    public void testDeleteParticipant() {
        Participant participant = getDao().getById(-100);
        assertNotNull("Participant (-100) should not be null ", participant);
        getDao().delete(participant);
        interruptSession();
        participant = getDao().getById(-100);
        assertNull("Participant should be null", participant);
    }

    public void testGetAll() {
        List all = getDao().getAll();
        assertNotNull(all);
        assertEquals(2, all.size());
    }

    public void testGetSitePrimaryIdentifiers() {
        List all = getDao().getSitePrimaryIdentifiers(-1001);
        assertNotNull(all);
        assertEquals(2, all.size());
    }

    public void testSearchParticipant() {
        ParticipantQuery pq = new ParticipantQuery();
        pq.filterByFirstName("Dilbert");
        List all = getDao().searchParticipant(pq);
        assertNotNull(all);
        assertEquals(1, all.size());
    }
}
