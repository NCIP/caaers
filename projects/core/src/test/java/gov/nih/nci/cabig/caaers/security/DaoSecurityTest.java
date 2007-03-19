/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import java.util.Date;

import org.acegisecurity.AccessDeniedException;
import org.springframework.test.AbstractTransactionalSpringContextTests;

/**
 * @author joshua
 * 
 */
public class DaoSecurityTest extends AbstractTransactionalSpringContextTests {

	public String[] getConfigLocations() {
		return new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml" };
	}

	/**
	 * 
	 */
	public DaoSecurityTest() {

	}

	/**
	 * @param arg0
	 */
	public DaoSecurityTest(String testName) {
		super(testName);
	}

	public void testStudySave() {

		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });

		StudyDao dao = (StudyDao) getApplicationContext().getBean("studyDao");

		Study study = new Study();
		study.setShortTitle("short title");
		study.setLongTitle("long title");
		study.setMultiInstitutionIndicator(Boolean.FALSE);
		study.setPrimarySponsorCode("SCODE_101");

		try {
			dao.save(study);
			fail("Should have failed to save study");
		} catch (AccessDeniedException ex) {
			// TODO: figure out why this is throwing AccessDeniedException that
			// is not wrapped in RuntimeException
			assertTrue(true);
		} catch (RuntimeException ex) {
			assertTrue(ex.getCause() instanceof AccessDeniedException);
		}

		SecurityTestUtils.switchUser("study_cd1",
				new String[] { "ROLE_caaers_study_cd" });
		try {
			dao.save(study);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have failed to save study: " + ex.getMessage());
		}

		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });
	}

	public void testStudyUpdate() {

		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });

		StudyDao dao = (StudyDao) getApplicationContext().getBean("studyDao");

		Study study = new Study();
		study.setShortTitle("short title");
		study.setLongTitle("long title");
		study.setMultiInstitutionIndicator(Boolean.FALSE);
		study.setPrimarySponsorCode("SCODE_101");
		SecurityTestUtils.switchUser("study_cd1",
				new String[] { "ROLE_caaers_study_cd" });
		try {
			dao.save(study);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have failed to save study: " + ex.getMessage());
		}
		Integer id = study.getId();
		assertNotNull("Study id is null", id);
		this.endTransaction();
		this.startNewTransaction();
		// SecurityTestUtils.switchUser("user_1", new String[] { "ignored" });
		study = new Study();
		study.setId(id);
		study.setShortTitle("some other title");
		study.setLongTitle("long title");
		study.setMultiInstitutionIndicator(Boolean.FALSE);
		study.setPrimarySponsorCode("SCODE_101");
		// try {
		// dao.merge(study);
		// fail("Should have failed to merge study");
		// } catch (Exception ex) {
		// assertTrue(true);
		// }
		// SecurityTestUtils.switchUser("study_cd1", new String[] {
		// "ROLE_caaers_study_cd" });
		try {
			dao.merge(study);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have failed to merge study: " + ex.getMessage());
		}

		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });

	}

	public void testParticipantSave() {

		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });

		ParticipantDao dao = (ParticipantDao) getApplicationContext().getBean(
				"participantDao");

		Participant participant = new Participant();
		participant.setFirstName("Jeff");
		participant.setLastName("Someone");
		participant.setGender("Male");
		participant.setDateOfBirth(new Date());
		participant.setEthnicity("ethnicity");
		participant.setRace("race");

		try {
			dao.save(participant);
			fail("Should have failed to save participant");
		} catch (AccessDeniedException ex) {
			assertTrue(true);
		} catch (RuntimeException ex) {
			assertTrue(ex.getCause() instanceof AccessDeniedException);
		}

		SecurityTestUtils.switchUser("participant_cd1",
				new String[] { "ROLE_caaers_participant_cd" });
		try {
			dao.save(participant);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have failed to save participant: "
					+ ex.getMessage());
		}
		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });
	}

	public void testParticipantUpdate() {

		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });		
		
		ParticipantDao dao = (ParticipantDao) getApplicationContext().getBean(
				"participantDao");

		Participant participant = new Participant();
		participant.setFirstName("Jeff");
		participant.setLastName("Someone");
		participant.setGender("Male");
		participant.setDateOfBirth(new Date());
		participant.setEthnicity("ethnicity");
		participant.setRace("race");

		SecurityTestUtils.switchUser("participant_cd1",
				new String[] { "ROLE_caaers_participant_cd" });
		try {
			dao.save(participant);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have failed to save study: " + ex.getMessage());
		}
		Integer id = participant.getId();
		assertNotNull("Participant id is null", id);

		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });

		participant = dao.getById(id);
		assertNotNull("participant " + id + " is null", participant);
		participant.setFirstName("Joe");
		try {
			dao.save(participant);
			fail("Should have failed to update participant");
		} catch (AccessDeniedException ex) {
			assertTrue(true);
		} catch (RuntimeException ex) {
			assertTrue(ex.getCause() instanceof AccessDeniedException);
		}
		SecurityTestUtils.switchUser("participant_cd1",
				new String[] { "ROLE_caaers_participant_cd" });
		try {
			dao.save(participant);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have failed to update participant: "
					+ ex.getMessage());
		}
		
		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });

	}

	public void testStudyParticipantAssignment() {

		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });

		StudyParticipantAssignment a = new StudyParticipantAssignment();
		try {
			a.setParticipant(new Participant());
			fail("Should have prevented setting participant");
		} catch (AccessDeniedException ex) {
			assertTrue(true);
		} catch (RuntimeException ex) {
			assertTrue(ex.getCause() instanceof AccessDeniedException);
		}

		SecurityTestUtils.switchUser("participant_cd1",
				new String[] { "ROLE_caaers_participant_cd" });
		try {
			a.setParticipant(new Participant());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have prevented setting participant");
		}
		SecurityTestUtils.switchUser("user_1",
				new String[] { "ROLE_that_does_not_exist" });

	}

}
