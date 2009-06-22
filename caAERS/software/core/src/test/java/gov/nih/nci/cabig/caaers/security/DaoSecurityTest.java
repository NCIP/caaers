/**
 *
 */
package gov.nih.nci.cabig.caaers.security;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.ctms.audit.DataAuditInfo;

import java.util.Calendar;

/**
 * @author joshua
 */
public class DaoSecurityTest extends CaaersDbTestCase {

    private static final DataAuditInfo INFO = new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo(
                    "dun", "127.1.2.7", DateUtils.createDate(2004, Calendar.NOVEMBER, 2),
                    "/studycalendar/zippo");
    
    public void testS(){
    	return;
    }
 
    /*
    public void setup(){
    	DataAuditInfo.setLocal(INFO);
    }
    
    public void tearDown(){
    	
    }

    private Ctc getCtc() {
        CtcDao ctcDao = (CtcDao) CaaersContextLoader.getApplicationContext().getBean("ctcDao");
        Ctc ctc = ctcDao.getCtcaeV3();
        return ctc;
    }

    // Temporarily commented
    public void testStudySave() {
        // TODO: Uncomment this testcase, after checking with Joshua
        // assert true;
        // SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");
        // StudyDao dao = (StudyDao)
        // CaaersContextLoader.getApplicationContext().getBean("studyDao");
        // Study study = new Study();
        // study.setShortTitle("short title");
        // study.setLongTitle("long title");
        // study.setMultiInstitutionIndicator(Boolean.FALSE);
        // //study.setPrimarySponsorCode("SCODE_101"); --
        // // we should set the primaryfundinsponsor (Object) //study.setCtcVersion(getCtc());
        // study.setTerminology(Fixtures.createCtcV3Terminology(study));
        // try {
        // dao.save(study);
        // fail("Should have failed to save study");
        // }
        // catch (AccessDeniedException ex) {
        // assertTrue(true);
        // } catch (RuntimeException ex) {
        // Throwable rootCause =
        // SecurityTestUtils.getRootCause(ex);
        // assertTrue("Expecting AccessDeniedException, got: " + rootCause, rootCause instanceof
        // AccessDeniedException);
        // }
        // SecurityTestUtils.switchToSuperuser();
        // dao.save(study);
        //
        // SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

    }

    public void testStudyUpdate() {

        SecurityTestUtils.switchToSuperuser();
        StudyDao dao = (StudyDao) CaaersContextLoader.getApplicationContext().getBean("studyDao");

        Study study = new Study();
        study.setShortTitle("short title");
        study.setLongTitle("long title");
        study.setMultiInstitutionIndicator(Boolean.FALSE);
        study.setAdeersReporting(Boolean.TRUE);
        // study.setPrimarySponsorCode("SCODE_101");
        // study.setCtcVersion(getCtc());
        study.setAeTerminology(Fixtures.createCtcV3Terminology(study));
        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
        dao.save(study);

        Integer id = study.getId();
        assertNotNull("Study id is null", id);

        SecurityTestUtils.switchUser("user_1", new String[] { "ignored" });
        study = new Study();
        study.setId(id);
        study.setShortTitle("some other title");
        study.setLongTitle("long title");
        study.setAdeersReporting(Boolean.TRUE);
        study.setMultiInstitutionIndicator(Boolean.FALSE);
        study.setAeTerminology(Fixtures.createCtcV3Terminology(study));
        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
        // study.setPrimarySponsorCode("SCODE_101");
        try {
            dao.merge(study);
        } catch (AccessDeniedException ex) {
            assertTrue(true);
        }
        SecurityTestUtils.switchUser("study_cd1", new String[] { "ROLE_caaers_study_cd" });
        dao.merge(study);

        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

    }

    public void testParticipantSave() {

        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

        ParticipantDao dao = (ParticipantDao) CaaersContextLoader.getApplicationContext().getBean("participantDao");

        Participant participant = new Participant();
        participant.setFirstName("Jeff");
        participant.setLastName("Someone");
        participant.setGender("Male");
        participant.setDateOfBirth(new DateValue());
        participant.setEthnicity("ethnicity");
        participant.setRace("race");

        try {
            dao.save(participant);
            fail("Should have failed to save participant");
        } catch (AccessDeniedException ex) {
            assertTrue(true);
        } catch (RuntimeException ex) {
            Throwable rootCause = SecurityTestUtils.getRootCause(ex);
            assertTrue("Expecting AccessDeniedException, got: " + rootCause,
                            rootCause instanceof AccessDeniedException);
        }

        SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");
        try {
            dao.save(participant);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Should not have failed to save participant: " + ex.getMessage());
        }
        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");
    }

    public void testParticipantUpdate() {

        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

        ParticipantDao dao = (ParticipantDao) CaaersContextLoader.getApplicationContext().getBean("participantDao");

        Participant participant = new Participant();
        participant.setFirstName("Jeff");
        participant.setLastName("Someone");
        participant.setGender("Male");
        participant.setDateOfBirth(new DateValue());
        participant.setEthnicity("ethnicity");
        participant.setRace("race");

        SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");
        try {
            dao.save(participant);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Should not have failed to save study: " + ex.getMessage());
        }
        Integer id = participant.getId();
        assertNotNull("Participant id is null", id);

        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

        Participant dbParticipant = dao.getById(id);
        assertNotNull("participant " + id + " is null", dbParticipant);
        dbParticipant.setFirstName("Joe");
        try {
            dao.save(dbParticipant);
            fail("Should have failed to update participant");
        } catch (AccessDeniedException ex) {
            assertTrue(true);
        } catch (RuntimeException ex) {
            Throwable rootCause = SecurityTestUtils.getRootCause(ex);
            assertTrue("Expecting AccessDeniedException, got: " + rootCause,
                            rootCause instanceof AccessDeniedException);
        }
        SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");
        try {
            dao.save(dbParticipant);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Should not have failed to update participant: " + ex.getMessage());
        }

        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

    }

    public void testStudyParticipantAssignment() {

        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

        StudyParticipantAssignment a = new StudyParticipantAssignment();
        try {
            a.setParticipant(new Participant());
            fail("Should have prevented setting participant");
        } catch (AccessDeniedException ex) {
            assertTrue(true);
        } catch (RuntimeException ex) {
            Throwable rootCause = SecurityTestUtils.getRootCause(ex);
            assertTrue("Expecting AccessDeniedException, got: " + rootCause,
                            rootCause instanceof AccessDeniedException);
        }

        SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");
        try {
            a.setParticipant(new Participant());
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Should not have prevented setting participant");
        }
        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

    }

    public void testAuthorizationSwitch() {
        AuthorizationSwitch authzSwitch = (AuthorizationSwitch) CaaersContextLoader.getApplicationContext().getBean(
                        "authorizationSwitch");
        authzSwitch.setOn(false);
        StudyParticipantAssignment a = new StudyParticipantAssignment();
        try {
            a.setParticipant(new Participant());
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Should not have prevented setting participant");
        }
        authzSwitch.setOn(true);
        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");
        try {
            a.setParticipant(new Participant());
            fail("Should have prevented setting participant");
        } catch (AccessDeniedException ex) {
            assertTrue(true);
        } catch (RuntimeException ex) {
            Throwable rootCause = SecurityTestUtils.getRootCause(ex);
            assertTrue("Expecting AccessDeniedException, got: " + rootCause,
                            rootCause instanceof AccessDeniedException);
        }
    }
*/
}
