package gov.nih.nci.cabig.caaers.web.par;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_PARTICIPANT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.web.participant.NewParticipantCommand;
import junit.framework.TestCase;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases( { CREATE_PARTICIPANT })
public class NewParticipantCommandTest extends TestCase {
    private NewParticipantCommand command = new NewParticipantCommand();

    public void testCreateParticipant() throws Exception {
        Participant participant = new Participant();
        // participant.setInstituitionalPatientNumber("sipn001");
        participant.setInstitution("i001");
        participant.setFirstName("Steve");
        participant.setLastName("Cheeks");
        participant.setGender("Male");
        participant.setEthnicity("ethnicity");
        participant.setRace("race");
        participant.setDateOfBirth(new DateValue());

        command.setParticipant(participant);
        Participant newParticipant = command.getParticipant();
        assertEquals("Cheeks", newParticipant.getLastName());
        assertEquals("ethnicity", newParticipant.getEthnicity());
        assertEquals("should not have any assigments", 0, newParticipant.getAssignments().size());
    }
}
