package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.web.CaaersFieldConfigurationManager;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManagerFactoryBean;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CaptureAdverseEventController Tester.
 *
 * @author Biju Joseph
 * @since <pre>01/26/2010</pre>
 * 
 */
public class CaptureAdverseEventControllerTest extends WebTestCase {
    

	BindException errors;

    ParticipantDao participantDao;
    StudyDao studyDao;
    StudyParticipantAssignmentDao assignmentDao;
    TreatmentAssignmentDao treatmentAssignmentDao;
    CtcTermDao ctcTermDao;
    CtcCategoryDao ctcCategoryDao;
    LowLevelTermDao lowLevelTermDao;
    AdverseEventDao adverseEventDao;
    AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
    EvaluationService evaluationService;
    ReportDefinitionDao reportDefinitionDao;
    ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    ReportRepository reportRepository;
	
    RenderDecisionManagerFactoryBean renderDecisionManagerFactoryBean;
    CaaersFieldConfigurationManager caaersFieldConfigurationManager;
	
    Configuration configuration;

    CaptureAdverseEventController controller;
    CaptureAdverseEventInputCommand command;

    AdverseEventReportingPeriod adverseEventReportingPeriod;
    ExpeditedAdverseEventReport aeReport;
    Report report;
    ReportDefinition reportDefinition;
    StudyParticipantAssignment assignment;

    ReviewAndReportResult result;

    public void setUp() throws Exception {
        super.setUp();


        adverseEventReportingPeriod = Fixtures.createReportingPeriod();
        adverseEventReportingPeriod.setId(new Integer(1));
        aeReport = Fixtures.createSavableExpeditedReport();
        aeReport.setId(new Integer(1)) ;

        reportDefinition = Fixtures.createReportDefinition("test");
        reportDefinition.setId(new Integer(1));

        report = Fixtures.createReport("Test");
        report.setStatus(ReportStatus.INPROCESS);
        report.setId(new Integer(1));
        report.setReportDefinition(reportDefinition);
        
        aeReport.addReport(report);
        adverseEventReportingPeriod.addAeReport(aeReport);
        assignment = Fixtures.createAssignment();

        assignment.getStudySite().setId(new Integer(1));
        assignment.getStudySite().getStudy().setId(new Integer(1));
        assignment.getParticipant().setId(new Integer(1));
        adverseEventReportingPeriod.setAssignment(assignment);



        participantDao = registerDaoMockFor(ParticipantDao.class);
        studyDao = registerDaoMockFor(StudyDao.class);
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        treatmentAssignmentDao = registerDaoMockFor(TreatmentAssignmentDao.class);
        ctcTermDao = registerDaoMockFor(CtcTermDao.class);
        ctcCategoryDao = registerDaoMockFor(CtcCategoryDao.class);
        lowLevelTermDao = registerDaoMockFor(LowLevelTermDao.class);
        adverseEventDao = registerDaoMockFor(AdverseEventDao.class);
        adverseEventReportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
        evaluationService = registerMockFor(EvaluationService.class);
        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
        expeditedAdverseEventReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
        reportRepository = registerMockFor(ReportRepository.class);
        renderDecisionManagerFactoryBean = registerMockFor(RenderDecisionManagerFactoryBean.class);
        caaersFieldConfigurationManager = registerMockFor(CaaersFieldConfigurationManager.class);
        configuration = registerMockFor(Configuration.class);

        controller = new CaptureAdverseEventController();
        controller.setParticipantDao(participantDao);
        controller.setStudyDao(studyDao);
        controller.setAssignmentDao(assignmentDao);
        controller.setTreatmentAssignmentDao(treatmentAssignmentDao);
        controller.setCtcTermDao(ctcTermDao);
        controller.setCtcCategoryDao(ctcCategoryDao);
        controller.setLowLevelTermDao(lowLevelTermDao);
        controller.setAdverseEventDao(adverseEventDao);
        controller.setAdverseEventReportingPeriodDao(adverseEventReportingPeriodDao);
        controller.setEvaluationService(evaluationService);
        controller.setReportDefinitionDao(reportDefinitionDao);
        controller.setExpeditedAdverseEventReportDao(expeditedAdverseEventReportDao);
        controller.setReportRepository(reportRepository);
        controller.setRenderDecisionManagerFactoryBean(renderDecisionManagerFactoryBean);
        controller.setCaaersFieldConfigurationManager(caaersFieldConfigurationManager);
        controller.setConfiguration(configuration);







        
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    //makes sure that the control is takes to target1, when the only action is withdraw.
    public void testProcessFinish_TakesToAdverseEventsPageIfOnlyActionIsWithdraw() throws Exception{

        Map<Integer, List<ReportTableRow>> applicableReportTableMap = new HashMap<Integer, List<ReportTableRow>>();

        result = new ReviewAndReportResult();
        result.setAeReportId(new Integer(1));
        result.getWithdrawList().add(reportDefinition);

        adverseEventReportingPeriodDao.save(adverseEventReportingPeriod);
        expeditedAdverseEventReportDao.lock(aeReport);
        reportRepository.processReports(aeReport, null, result.getReportsToUnAmendList(), result.getReportsToWithdraw(), null);

        replayMocks();



        command = new CaptureAdverseEventInputCommand(adverseEventReportingPeriodDao,assignmentDao, evaluationService, reportDefinitionDao, studyDao, expeditedAdverseEventReportDao);
        command.setAdverseEventReportingPeriod(adverseEventReportingPeriod);
        command.setStudy(assignment.getStudySite().getStudy());
        command.setParticipant(assignment.getParticipant());

        applicableReportTableMap.put(aeReport.getId(), new ArrayList<ReportTableRow>());
        applicableReportTableMap.put(new Integer(0), new ArrayList<ReportTableRow>());
        command.setApplicableReportTableMap(applicableReportTableMap);
        session.setAttribute("reviewResult", result);
        command.setReviewResult(result);
        
        command.refreshAeReportIdIndex();
        errors = new BindException(command, "command");

        ModelAndView modelAndView = controller.processFinish(request, response, command, errors);



        assertNotNull(modelAndView);
        assertEquals("1" , modelAndView.getModel().get("_target1"));
        assertEquals("0" , modelAndView.getModel().get("_page"));
        verifyMocks();



    }
}