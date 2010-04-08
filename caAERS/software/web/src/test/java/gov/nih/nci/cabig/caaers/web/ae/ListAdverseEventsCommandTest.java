package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;

import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import static org.easymock.EasyMock.expect;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class ListAdverseEventsCommandTest extends WebTestCase {
    private ListAdverseEventsCommand command;

    private StudyDao studyDao;
    private StudyParticipantAssignmentDao assignmentDao;
    private ParticipantDao participantDao;
    private ReportValidationService reportValidationService;
    private ResearchStaffDao researchStaffDao;
    protected CSMUserRepository csmUserRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerDaoMockFor(StudyDao.class);
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        participantDao = registerDaoMockFor(ParticipantDao.class);
        researchStaffDao = registerDaoMockFor(ResearchStaffDao.class);
        reportValidationService = registerMockFor(ReportValidationService.class);
        csmUserRepository = registerMockFor(CSMUserRepository.class);
        command = new ListAdverseEventsCommand(reportValidationService, researchStaffDao);
        setupCommand();
    }
    
    private void setupCommand(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	Report report1 = Fixtures.createReport("report 1");
    	report1.setId(1);
    	Report report2 = Fixtures.createReport("report 2");
    	report2.setId(2);
    	aeReport.addReport(report1);
    	aeReport.addReport(report2);
    	command.setAssignment(Fixtures.createAssignment());
    	command.getAssignment().addReportingPeriod(Fixtures.createReportingPeriod());
    	command.getAssignment().getReportingPeriods().get(0).addAeReport(aeReport);
    }
    
    public void testUpdateSubmittability() throws Exception {
    	ReportSubmittability reportSubmittability1 = new ReportSubmittability();
    	ReportSubmittability reportSubmittability2 = new ReportSubmittability();
    	reportSubmittability2.addValidityMessage(ExpeditedReportSection.ATTRIBUTION_SECTION, "missing attribution");
    	expect(reportValidationService.isSubmittable(command.getAssignment().getAeReports().get(0).getReports().get(0))).andReturn(reportSubmittability1);
    	expect(reportValidationService.isSubmittable(command.getAssignment().getAeReports().get(0).getReports().get(1))).andReturn(reportSubmittability2);
    	replayMocks();
    	command.updateSubmittability();
    	verifyMocks();
    	assertTrue(command.getReportsSubmittable().get(1));
    	assertFalse(command.getReportsSubmittable().get(2));
    }
    
    public void testUpdateSubmittabilityBasedOnReportStatus() throws Exception{
    	command.getReportsSubmittable().clear();
    	command.getReportsSubmittable().put(1, true);
    	command.getReportsSubmittable().put(2, false);
    	command.getAssignment().getAeReports().get(0).getReports().get(0).setStatus(ReportStatus.COMPLETED);
    	command.getAssignment().getAeReports().get(0).getReports().get(1).setStatus(ReportStatus.PENDING);
    	command.updateSubmittabilityBasedOnReportStatus();
    	assertFalse(command.getReportsSubmittable().get(1));
    	assertFalse(command.getReportsSubmittable().get(2));
    }
    
    public void testExplicitStudyTrumps() throws Exception {
        Study expected = new LocalStudy();
        command.setStudy(expected);

        replayMocks();
        assertSame(expected, command.getStudy());
        verifyMocks();
    }



    public void testExplicitParticipantTrumps() throws Exception {
        Participant expected = new Participant();
        command.setParticipant(expected);

        replayMocks();
        assertSame(expected, command.getParticipant());
        verifyMocks();
    }

  
}
