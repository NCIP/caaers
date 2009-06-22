package gov.nih.nci.cabig.caaers.security;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;

import java.sql.Timestamp;
import java.util.Calendar;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 */
public class AdverseEventReportDaoSecurityTest extends CaaersDbNoSecurityTestCase {
    private static final Log logger = LogFactory.getLog(AdverseEventReportDaoSecurityTest.class);

    CtcTermDao ctcTermDao;

    AdverseEventReportingPeriodDao reportingPeriodDao;
    ExpeditedAdverseEventReportDao adverseEventReportDao;

    @Override
    public String getTestDataFileName() {
        return "../dao/testdata/ExpeditedAdverseEventReportDaoTest.xml";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");
        ctcTermDao = (CtcTermDao) getApplicationContext().getBean("ctcTermDao");
        reportingPeriodDao = (AdverseEventReportingPeriodDao) getApplicationContext().getBean("adverseEventReportingPeriodDao");
        adverseEventReportDao = (ExpeditedAdverseEventReportDao) getApplicationContext().getBean(
                        "expeditedAdverseEventReportDao");
    }

    @Override
    protected void tearDown() throws Exception {
        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");
        super.tearDown();
    }

    public void testAdverseEventSave() {
        SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");

        ExpeditedAdverseEventReport newReport = createReport();

        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("################## auth = " + auth.getName() + " #####################");
        try {
            adverseEventReportDao.save(newReport);
            //fail("Should have failed to save report");
        } catch (AccessDeniedException ex) {
            // expected
            // TODO: what is actually expected here? AccessDeniedException or a wrapped exception?
        } catch (RuntimeException ex) {
            Throwable rootCause = SecurityTestUtils.getRootCause(ex);
            assertTrue("Expecting AccessDeniedException, got: " + rootCause,
                            rootCause instanceof AccessDeniedException);
        }

        SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");
        adverseEventReportDao.save(newReport);
        Integer id = newReport.getId();
        assertNotNull("Report not saved", id);

        interruptSession();

        assertNotNull("Saved report not reloadable", adverseEventReportDao.getById(id));
    }

    public void testAdverseEventUpdate() {
        SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");

        Integer id;
        {
            ExpeditedAdverseEventReport newReport = createReport();

            adverseEventReportDao.save(newReport);

            id = newReport.getId();
            assertNotNull("Report id is null", id);
        }

        interruptSession();

        SecurityTestUtils.switchUser("user_1", "ROLE_that_does_not_exist");

        ExpeditedAdverseEventReport report = adverseEventReportDao.getById(id);
        assertNotNull("report " + id + " is null", report);

        // TODO: not sure this is the right way to handle this (i.e., may be hiding a bug) -
        // RMS20070702
        // (since UPDATE doesn't cascade to reporter/physician, have to load everything before
        // disconnecting the session)
        report.getReporter().getContactMechanisms().size();
        report.getPhysician().getContactMechanisms().size();

        report.getAdverseEvents().get(0).setComments("Yadda");
        try {
            adverseEventReportDao.save(report);
            //fail("Should have failed to update report");
        } catch (AccessDeniedException ex) {
            // expected
            // TODO: what is actually expected here? AccessDeniedException or a wrapped exception?
        } catch (RuntimeException ex) {
            Throwable rootCause = SecurityTestUtils.getRootCause(ex);
            assertTrue("Expecting AccessDeniedException, got: " + rootCause,
                            rootCause instanceof AccessDeniedException);
        }
        SecurityTestUtils.switchUser("participant_cd1", "ROLE_caaers_participant_cd");

        // Since there was an exception in a transactional DAO method, we have to get a new
        // hibernate session
        interruptSession();

        adverseEventReportDao.save(report);

        interruptSession();

        assertEquals("Report not updated", "Yadda", adverseEventReportDao.getById(id)
                        .getAdverseEvents().get(0).getComments());
    }

    private ExpeditedAdverseEventReport createReport() {
        CtcTerm term = ctcTermDao.getById(3012);
        AdverseEvent newEvent = new AdverseEvent();
        newEvent.setGrade(Grade.MILD);
        newEvent.getAdverseEventCtcTerm().setCtcTerm(term);
        newEvent.setExpected(Boolean.FALSE);
        newEvent.setHospitalization(Hospitalization.NO);
        newEvent.setStartDate((new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25)
                        .getTime() + 600000)));
        ExpeditedAdverseEventReport newReport = Fixtures.createSavableExpeditedReport();
        newReport.addAdverseEvent(newEvent);
        newReport.setReportingPeriod(reportingPeriodDao.getById(-14));

        return newReport;
    }
}
