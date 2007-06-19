package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;

/**
 * @author Rhett Sutphin
 */
public abstract class AeWebTestCase extends WebTestCase {
    protected StudyParticipantAssignment assignment;

    protected StudyParticipantAssignmentDao assignmentDao;
    protected CreateAdverseEventCommand command;
    private ExpeditedAdverseEventReportDao reportDao;
    private RuleExecutionService rulesExecutionService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignment = Fixtures.createAssignment();
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        reportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
        rulesExecutionService = registerMockFor(RuleExecutionService.class);
        command = createCommand();
    }

    protected abstract CreateAdverseEventCommand createCommand();

    protected final CreateAdverseEventCommand createRealCommand() {
        return new CreateAdverseEventCommand(assignmentDao, reportDao, rulesExecutionService, nowFactory);
    }

    protected final CreateAdverseEventCommand createMockCommand() {
        return new CreateAdverseEventCommand(assignmentDao, reportDao, rulesExecutionService, nowFactory) {
            @Override
            public StudyParticipantAssignment getAssignment() {
                return assignment;
            }
        };
    }
}
