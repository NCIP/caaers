/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;

import java.sql.Timestamp;
import java.util.Calendar;

import org.acegisecurity.AccessDeniedException;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class AdverseEventReportDaoSecurityTest extends CaaersDbTestCase {


	CtcTermDao ctcTermDao = (CtcTermDao)getApplicationContext().getBean("ctcTermDao");
	StudyParticipantAssignmentDao assignmentDao = (StudyParticipantAssignmentDao)getApplicationContext().getBean("studyParticipantAssignmentDao");
	AdverseEventReportDao adverseEventReportDao = (AdverseEventReportDao)getApplicationContext().getBean("adverseEventReportDao");	
	
	@Override
    public String getTestDataFileName() {
        return "../dao/testdata/AdverseEventReportDaoTest.xml";
    }

	/**
	 * 
	 */
	public AdverseEventReportDaoSecurityTest() {

	}
	
	protected void setUp() throws Exception{
		super.setUp();
		SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");
	}
	
	public void testAdverseEventSave() {

		
		
		SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");
		
		CtcTerm term = ctcTermDao.getById(3012);
        AdverseEvent newEvent = new AdverseEvent();
        newEvent.setGrade(Grade.MILD);
        newEvent.setCtcTerm(term);
        newEvent.setExpected(Boolean.FALSE);
        newEvent.setHospitalization(Hospitalization.PROLONGED_HOSPITALIZATION);

        AdverseEventReport newReport = new AdverseEventReport();
        newReport.addAdverseEvent(newEvent);
        newReport.setAssignment(assignmentDao.getById(-14));
        newReport.setDetectionDate(new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25).getTime() + 600000));

        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");
		try {
			adverseEventReportDao.save(newReport);
			fail("Should have failed to save report");
		}catch(AccessDeniedException ex){
			assertTrue(true);
		} catch (RuntimeException ex) {
			assertTrue(ex.getCause() instanceof AccessDeniedException);
		}

		SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");
		try {
			adverseEventReportDao.save(newReport);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have failed to save report: "
					+ ex.getMessage());
		}
		
	}


	public void testAdverseEventUpdate() {
		SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");
		
		CtcTerm term = ctcTermDao.getById(3012);
        AdverseEvent newEvent = new AdverseEvent();
        newEvent.setGrade(Grade.MILD);
        newEvent.setCtcTerm(term);
        newEvent.setExpected(Boolean.FALSE);
        newEvent.setHospitalization(Hospitalization.PROLONGED_HOSPITALIZATION);

        AdverseEventReport newReport = new AdverseEventReport();
        newReport.addAdverseEvent(newEvent);
        newReport.setAssignment(assignmentDao.getById(-14));
        newReport.setDetectionDate(new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25).getTime() + 600000));

		try {
			adverseEventReportDao.save(newReport);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have failed to save report: " + ex.getMessage());
		}
		Integer id = newReport.getId();
		assertNotNull("Report id is null", id);

		SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

		AdverseEventReport report = adverseEventReportDao.getById(id);
		assertNotNull("report " + id + " is null", report);
		
		report.getAdverseEvents().get(0).setComments("Yadda");
		try {
			adverseEventReportDao.save(report);
			fail("Should have failed to update report");
		}catch(AccessDeniedException ex){
			assertTrue(true);
		} catch (RuntimeException ex) {
			assertTrue(ex.getCause() instanceof AccessDeniedException);
		}
		SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");
		try {
			adverseEventReportDao.save(report);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Should not have failed to update report: "
					+ ex.getMessage());
		}

	}

}
