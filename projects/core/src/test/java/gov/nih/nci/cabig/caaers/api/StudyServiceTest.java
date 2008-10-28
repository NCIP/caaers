/**
 *
 */
package gov.nih.nci.cabig.caaers.api;

import java.util.Date;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class StudyServiceTest extends CaaersDbNoSecurityTestCase {

    private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean(
                    "organizationDao");

    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean(
                    "participantDao");

    public String getTestDataFileName() {
        String fileName = "testdata/StudyServiceTest.xml";
        return fileName;
    }

    public void testCreateAssignNewParticipant() {

        try {
            int studySiteId = -3001;
            String siteGridId = "gridSite";
            Integer participantId = null;
            {
                Organization organization = organizationDao.getByGridId(siteGridId);
                StudySite studySite = organization.getStudySites().get(0);
                assertEquals("Wrong study site found in test setup", studySiteId, studySite.getId()
                                .intValue());
                Study study = studySite.getStudy();

                Participant participant = new Participant();
                participant.setFirstName("Mighty");
                participant.setMiddleName("A");
                participant.setLastName("Mouse");
                participant.setInstitution("Some institution");
                participant.setInstitutionalPatientNumber("Some patient number");
                participant.setDateOfBirth(new DateValue());
                participant.setGridId("gridParticipant");

                StudyService svc = (StudyService) getApplicationContext()
                                .getBean("studyServiceAPI");
                StudyParticipantAssignment assignment = svc.assignParticipant(study, participant,
                                organization, "gridRegistration");
                assertNotNull("Assignment is null", assignment);
                assertNotNull("Assignment not flushed", assignment.getId());
                assertNotNull("Assignment gridId is null", assignment.getGridId());
                participantId = assignment.getParticipant().getId();
            }
            interruptSession();

            Participant loaded = participantDao.getById(participantId.intValue());
            assertNotNull("Participant reloading failed", loaded);
            StudyParticipantAssignment newAssignment = null;
            for (StudyParticipantAssignment a : loaded.getAssignments()) {
                if (a.getStudySite().getId().intValue() == studySiteId) {
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
