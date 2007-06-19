package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import org.springframework.validation.Errors;
import org.springframework.validation.BindException;

/**
 * @author Rhett Sutphin
 */
public abstract class AeWebTestCase extends WebTestCase {
    protected StudyParticipantAssignment assignment;

    protected StudyParticipantAssignmentDao assignmentDao;
    protected CreateExpeditedAdverseEventCommand command;
    private ExpeditedAdverseEventReportDao reportDao;
    private RuleExecutionService rulesExecutionService;
    protected Errors errors;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignment = Fixtures.createAssignment();
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        reportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
        rulesExecutionService = registerMockFor(RuleExecutionService.class);

        command = createCommand();

        errors = new BindException(command, "command");
    }

    protected abstract CreateExpeditedAdverseEventCommand createCommand();

    protected final CreateExpeditedAdverseEventCommand createRealCommand() {
        return new CreateExpeditedAdverseEventCommand(assignmentDao, reportDao, rulesExecutionService, nowFactory);
    }

    protected final CreateExpeditedAdverseEventCommand createMockCommand() {
        return new CreateExpeditedAdverseEventCommand(assignmentDao, reportDao, rulesExecutionService, nowFactory) {
            @Override
            public StudyParticipantAssignment getAssignment() {
                return assignment;
            }
        };
    }

    protected final CreateExpeditedAdverseEventCommand createMinimallyValidMockCommand() {
        CreateExpeditedAdverseEventCommand c = createMockCommand();
        // initialize command as minimally valid
        // BeginTab
        c.setParticipant(assignment.getParticipant());
        c.setStudy(assignment.getStudySite().getStudy());

        // BasicsTab
        AdverseEvent event = c.getAeReport().getAdverseEvents().get(0);
        event.setGrade(Grade.MODERATE);
        event.setHospitalization(Hospitalization.NONE);
        event.setCtcTerm(new CtcTerm());

        return c;
    }

    public Errors getErrors() {
        return errors;
    }
}
