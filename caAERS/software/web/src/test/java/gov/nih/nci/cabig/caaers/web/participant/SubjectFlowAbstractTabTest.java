package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import java.util.ArrayList;

/**
 * @author Ion C. Olaru
 */
public abstract class SubjectFlowAbstractTabTest extends WebTestCase {

    private static final Log log = LogFactory.getLog(SubjectFlowAbstractTabTest.class);
    protected TabWithFields tab;
    protected Errors errors;
    protected ParticipantInputCommand command;
    protected StudyParticipantAssignment assignment;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tab = createTab();
        command = createCommand();
        errors = new BindException(command, "command");
    }

    protected ParticipantInputCommand createCommand() {
        ParticipantInputCommand command = new ParticipantInputCommand();
        command.setAssignment(new StudyParticipantAssignment());
        command.getAssignment().setParticipant(new Participant());
        command.getAssignment().setStudySite(new StudySite());
        command.getAssignment().getStudySite().setStudy(new LocalStudy());
        command.getAssignment().getStudySite().setOrganization(new LocalOrganization());
        command.getAssignment().setPreExistingConditions(new ArrayList<StudyParticipantPreExistingCondition>());
        command.getAssignment().setConcomitantMedications(new ArrayList<StudyParticipantConcomitantMedication>());
        command.getAssignment().setPriorTherapies(new ArrayList<StudyParticipantPriorTherapy>());
        return command;
    }

    protected abstract TabWithFields createTab();

    public TabWithFields getTab() {
        return tab;
    }

    public Errors getErrors() {
        return errors;
    }

    public StudyParticipantAssignment getAssignment() {
        return command.getAssignment();
    }

    public void testTab() {
        assertNull(command);
    }
}