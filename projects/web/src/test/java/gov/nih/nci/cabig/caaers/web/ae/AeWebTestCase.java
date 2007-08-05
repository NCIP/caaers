package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedReportPerson;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public abstract class AeWebTestCase extends WebTestCase {
    protected StudyParticipantAssignment assignment;

    protected StudyParticipantAssignmentDao assignmentDao;
    protected CreateExpeditedAdverseEventCommand command;
    protected ExpeditedAdverseEventReportDao reportDao;
    private ReportDefinitionDao reportDefinitionDao;
    private StudyDao studyDao;
    private ParticipantDao participantDao;
    protected Errors errors;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignment = Fixtures.createAssignment();
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
        reportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
        studyDao = registerMockFor(StudyDao.class);
        participantDao = registerMockFor(ParticipantDao.class);

        command = createCommand();

        errors = new BindException(command, "command");
    }

    protected abstract CreateExpeditedAdverseEventCommand createCommand();

    protected final CreateExpeditedAdverseEventCommand createRealCommand() {
        return new CreateExpeditedAdverseEventCommand(assignmentDao, reportDao, reportDefinitionDao, studyDao, participantDao, nowFactory);
    }

    protected final CreateExpeditedAdverseEventCommand createMockCommand() {
        return new CreateExpeditedAdverseEventCommand(assignmentDao, reportDao, reportDefinitionDao, studyDao, participantDao, nowFactory) {
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
        event.getAdverseEventCtcTerm().setCtcTerm(new CtcTerm());

        // ReporterTab
        c.getAeReport().setReporter(new Reporter());
        c.getAeReport().getReporter().setFirstName("Dan");
        c.getAeReport().getReporter().setLastName("McReporter");
        c.getAeReport().getReporter().getContactMechanisms().put(ExpeditedReportPerson.EMAIL, "dan@example.com");
        c.getAeReport().setPhysician(new Physician());
        c.getAeReport().getPhysician().setFirstName("Jim");
        c.getAeReport().getPhysician().setLastName("O'Physician");
        c.getAeReport().getPhysician().getContactMechanisms().put(ExpeditedReportPerson.EMAIL, "docjim@example.com");

        return c;
    }

    public Errors getErrors() {
        return errors;
    }
}
