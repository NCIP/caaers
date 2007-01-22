/**
 * 
 */
package gov.nih.nci.cabig.caaers.api;

import java.util.Date;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class StudyServiceTest extends CaaersDbTestCase {

    private SiteDao siteDao = (SiteDao) getApplicationContext().getBean("siteDao");
    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean("participantDao");

    public String getTestDataFileName() {
        String fileName = "../dao/testdata/ParticipantDaoTest.xml";
        return fileName;
    }

    public void testCreateAssignNewParticipant() {

        try {
            int siteId = -1001;
            int studySiteId = -3001;
            Integer participantId = null;
            {
                Site site = siteDao.getById(siteId);
                StudySite studySite = site.getStudySites().get(0);
                assertEquals("Wrong study site found in test setup", studySiteId, (int) studySite.getId());
                Study study = studySite.getStudy();

                Participant participant = new Participant();
                participant.setFirstName("Mighty");
                participant.setMiddleName("A");
                participant.setLastName("Mouse");
                participant.setInstitution("Some institution");
                participant.setInstitutionalPatientNumber("Some patient number");
                participant.setDateOfBirth(new Date());

                StudyService svc = (StudyService) getApplicationContext()
                                .getBean("studyServiceAPI");
                StudyParticipantAssignment assignment = svc.assignParticipant(study, participant,
                                site);
                assertNotNull("Assignment is null", assignment);
                assertNotNull("Assignment gridId is null", assignment.getGridId());
                participantId = assignment.getParticipant().getId();
            }
            interruptSession();
            
            Participant loaded = participantDao.getById(participantId.intValue());
            assertNotNull("Participant reloading failed", loaded);
            StudyParticipantAssignment newAssignment = null;
            for(StudyParticipantAssignment a : loaded.getAssignments()){
                if(a.getStudySite().getId().intValue() == studySiteId){
                    newAssignment = a;
                    break;
                }
            }
            assertNotNull("New assignment is null", newAssignment);
            assertNotNull("newAssignment ID is null", newAssignment.getId());

        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error running test: " + ex.getMessage());
        }

    }

}
