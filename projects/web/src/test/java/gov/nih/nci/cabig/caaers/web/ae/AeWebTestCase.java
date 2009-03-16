package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.Date;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public abstract class AeWebTestCase extends WebTestCase {
    protected StudyParticipantAssignment assignment;

    protected StudyParticipantAssignmentDao assignmentDao;

    protected AdverseEventReportingPeriodDao reportingPeriodDao;
    
    protected EditExpeditedAdverseEventCommand command;

    protected ExpeditedAdverseEventReportDao reportDao;

    protected ReportDefinitionDao reportDefinitionDao;

    protected StudyDao studyDao;

    protected ParticipantDao participantDao;
    
    protected ExpeditedAdverseEventReportDao expeditedReportDao;

    protected Errors errors;

    protected ExpeditedReportTree expeditedReportTree;
    protected RenderDecisionManager renderDecisionManager;
    
    protected AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignment = Fixtures.createAssignment();
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
        reportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
        studyDao = registerMockFor(StudyDao.class);
        participantDao = registerMockFor(ParticipantDao.class);
        expeditedReportTree = new ExpeditedReportTree();
        expeditedReportDao = registerMockFor(ExpeditedAdverseEventReportDao.class);
        reportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
        renderDecisionManager = registerMockFor(RenderDecisionManager.class);
        adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepositoryImpl.class);
        command = createCommand();

        errors = new BindException(command, "command");
    }

    protected abstract EditExpeditedAdverseEventCommand createCommand();

    protected final EditExpeditedAdverseEventCommand createRealCommand() {
    	return new EditExpeditedAdverseEventCommand(expeditedReportDao, reportDefinitionDao, assignmentDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager, null, adverseEventRoutingAndReviewRepository);
    }

    protected final EditExpeditedAdverseEventCommand createMockCommand() {
        return new EditExpeditedAdverseEventCommand(expeditedReportDao, reportDefinitionDao, assignmentDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager, null, adverseEventRoutingAndReviewRepository);
    }

    protected final EditExpeditedAdverseEventCommand createMinimallyValidMockCommand() {
        EditExpeditedAdverseEventCommand c = createMockCommand();
        // initialize command as minimally valid
        AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
        reportingPeriod.setAssignment(assignment);
        ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        aeReport.setReportingPeriod(reportingPeriod);
        // This has changed to handle Many-To-One relationship between ReportingPeriod and ExpeditedReport
        // TODO: fix it when use case is ready.
        reportingPeriod.addAeReport(aeReport);
        aeReport.setCreatedAt(nowFactory.getNowTimestamp());
        c.setAeReport(aeReport);
        

        // BasicsTab
        AdverseEvent event = c.getAeReport().getAdverseEvents().get(0);
        event.setGrade(Grade.MODERATE);
        event.setHospitalization(Hospitalization.NONE);
        event.getAdverseEventCtcTerm().setCtcTerm(new CtcTerm());
        event.setStartDate(NOW);
        event.setEndDate(NOW);
        
        Outcome o = new Outcome();
        o.setDate(new Date());
        o.setOutcomeType(OutcomeType.HOSPITALIZATION);
        event.addOutcome(o);
        
        c.updateOutcomes();

        // ReporterTab
        c.getAeReport().setReporter(new Reporter());
        c.getAeReport().getReporter().setFirstName("Dan");
        c.getAeReport().getReporter().setLastName("McReporter");
        c.getAeReport().getReporter().getContactMechanisms().put(ReportPerson.EMAIL,"dan@example.com");
        c.getAeReport().setPhysician(new Physician());
        c.getAeReport().getPhysician().setFirstName("Jim");
        c.getAeReport().getPhysician().setLastName("O'Physician");
        c.getAeReport().getPhysician().getContactMechanisms().put(ReportPerson.EMAIL,"docjim@example.com");

        // CheckpointTab
        c.setNextPage(5);
        return c;
    }

    public Errors getErrors() {
        return errors;
    }
}
