package gov.nih.nci.cabig.caaers.dao;

import java.util.Date;

import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.dao.StudyDao;


/**
 * 
 * @author Krikor Krumlian
 * */
public class ParticipantDaoTest extends DaoTestCase<ParticipantDao>{
	private SiteDao siteDao = (SiteDao) getApplicationContext().getBean("siteDao");
    private StudyDao studyDao = (StudyDao) getApplicationContext().getBean("studyDao");
	/*
    public void testGet() throws Exception {
        Study loaded = (Participant)getDao().getById(-1);
        assertNotNull("Study not found", loaded);
        assertEquals("Short Title", loaded.getShortTitle());
    }
    */

    public void testGetById() throws Exception {
        Participant participant = getDao().getById(-100);
        assertNotNull("Participant not found", participant);
        assertEquals("Wrong last name", "Scott", participant.getLastName());
    }
    
    
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
}
