/**
 *
 */
package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class StudyServiceTest extends CaaersDbNoSecurityTestCase {

    private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean("participantDao");

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

                StudyService svc = (StudyService) getApplicationContext().getBean("studyServiceAPI");
                StudyParticipantAssignment assignment = svc.assignParticipant(study, participant, organization, "gridRegistration");
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

    private static Study createStudy(final String shortTitle) {
        Study s = new LocalStudy();
        s.setShortTitle(shortTitle);
        s.setLongTitle(shortTitle);

        OrganizationAssignedIdentifier i = new OrganizationAssignedIdentifier();
        i.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
        i.setValue("ABC-01");
        s.addIdentifier(i);

        Organization o = new LocalOrganization();
        o.setNciInstituteCode("CTEP");
        StudyFundingSponsor so = new StudyFundingSponsor();
        so.setOrganization(o);
        so.setStudy(s);

        i.setOrganization(o);
        s.addStudyFundingSponsor(so);

        return s;
    }

    public void testSearchAdEERSStudyInCaAERS() {
        StudyService svc = (StudyService) getApplicationContext().getBean("studyServiceAPI");

        List<Study> studies = new ArrayList<Study>();
        studies.add(createStudy("Short Title - 01"));
        studies.add(createStudy("Short Title - 02"));
        studies.get(0).getIdentifiers().get(0).setValue("ABC-01");
        studies.get(0).setId(90);
        studies.get(1).getIdentifiers().get(0).setValue("ABC-99");

        List<Study> studiesCaAERS = new ArrayList<Study>();
        studiesCaAERS.add(createStudy("Short Title caAERS - 01"));
        studiesCaAERS.add(createStudy("Short Title caAERS - 02"));
        studiesCaAERS.get(0).setId(90);
        studiesCaAERS.get(0).getIdentifiers().get(0).setValue("ABC-01");
        studiesCaAERS.get(0).setId(90);

        studiesCaAERS.get(1).getIdentifiers().get(0).setValue("ABC-990");

        svc.searchAdEERSStudiesInCaAERS(studies, studiesCaAERS);

        assertEquals("UPDATED", studies.get(1).getStatus());

    }

}
