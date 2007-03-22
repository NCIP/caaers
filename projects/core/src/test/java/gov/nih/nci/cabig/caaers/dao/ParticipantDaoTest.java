package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

import java.util.Date;
import java.util.List;

/**
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
public class ParticipantDaoTest extends DaoTestCase<ParticipantDao>{
    private SiteDao siteDao = (SiteDao) getApplicationContext().getBean("siteDao");

    public void testGetById() throws Exception {
        Participant participant = getDao().getById(-100);
        assertNotNull("Participant not found", participant);
        assertEquals("Wrong last name", "Scott", participant.getLastName());
        assertEquals("Wrong first name", "Dilbert", participant.getFirstName());
        assertEquals("Wrong number of identifiers", 1, participant.getIdentifiers().size());
    }
    
/*  // TODO: make this test pass
    public void testGetIsReadOnly() throws Exception {
        {
            Participant participant = getDao().getById(-100);
            assertEquals("Wrong number of identifiers initially", 1, participant.getIdentifiers().size());
            participant.setIdentifiers(null);
        }

        interruptSession();

        {
            Participant participant = getDao().getById(-100);
            assertEquals("Identifiers incorrectly purged", 1, participant.getIdentifiers().size());
        }
    }
*/

    public void testSaveAssignment() throws Exception {
        {
            Site site = siteDao.getById(-1001);
            StudySite studySite = site.getStudySites().get(0);
            assertEquals("Wrong study site found in test setup", -3001, (int) studySite.getId());
            Participant participant = getDao().getById(-100);
            assertEquals("Participant should already have one assignment", 1, participant.getAssignments().size());

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
        //assertSameDay("Wrong start date", new Date(), newAssignment.getDateOfEnrollment());
    }
    
    
    public void testSaveNewParticipant() throws Exception {
        Integer savedId;
        {
            Participant participant = new Participant();
            participant.setFirstName("Jeff");
            participant.setLastName("Someone");
            participant.setGender("Male");
            participant.setDateOfBirth(new Date());
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

    public void testGetBySubnameMatchesFirstName() throws Exception {
        List<Participant> matches = getDao().getBySubnames(new String[] { "icha" });
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", -101, (int) matches.get(0).getId());
    }

    public void testGetBySubnameMatchesLastName() throws Exception {
        List<Participant> matches = getDao().getBySubnames(new String[] { "cot" });
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", -100, (int) matches.get(0).getId());
    }

    public void testGetBySubnameMatchesInstitutionalId() throws Exception {
        List<Participant> matches = getDao().getBySubnames(new String[] { "P002" });
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", -101, (int) matches.get(0).getId());
    }

    public void testGetBySubnameMatchesIntersectionOfMultiple() throws Exception {
        List<Participant> matches;

        matches = getDao().getBySubnames(new String[] { "Jor", "P001" });
        assertEquals("Should be no matches", 0, matches.size());

        matches = getDao().getBySubnames(new String[] { "Jor", "P002" });
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", -101, (int) matches.get(0).getId());
    }
}
