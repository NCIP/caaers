package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import org.easymock.EasyMock;

/**
 * @author Rhett Sutphin
 */
public abstract class AeWebTestCase extends WebTestCase {
    protected StudyParticipantAssignmentDao assignmentDao;
    protected CreateAdverseEventCommand command;
    private AdverseEventReportDao reportDao;
    private RuleExecutionService rulesExecutionService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        reportDao = registerDaoMockFor(AdverseEventReportDao.class);
        rulesExecutionService = registerMockFor(RuleExecutionService.class);
        command = new CreateAdverseEventCommand(assignmentDao, reportDao, rulesExecutionService);
    }

    protected void expectGetAssignment(StudyParticipantAssignment assignment) {
        EasyMock.expect(assignmentDao.getAssignment(command.getParticipant(), command.getStudy()))
            .andReturn(assignment);
    }
}
