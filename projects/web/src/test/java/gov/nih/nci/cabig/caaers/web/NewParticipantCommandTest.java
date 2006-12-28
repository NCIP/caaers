package gov.nih.nci.cabig.caaers.web;

import junit.framework.TestCase;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.web.NewParticipantCommand;

import java.util.Date;

/**
 * @author Krikor Krumlian
 */
public class NewParticipantCommandTest extends TestCase {
    private NewParticipantCommand command = new NewParticipantCommand();

    public void testCreateParticipant() throws Exception {
        command.setInstituitionalPatientNumber("sipn001");
        command.setInstitution("i001");
        command.setStudyParticipantName("Steve");
    	command.setFirstName("Steve");
        command.setLastName("Cheeks");
        command.setGender("Male");
        command.setEthnicity("ethnicity");
        command.setRace("race");
        command.setDateOfBirth(new Date());
       
        Participant participant = command.createParticipant();
        assertEquals("Cheeks", participant.getLastName());
        assertEquals("ethnicity", participant.getEthnicity());
        assertEquals("should not have any assigments", 0, participant.getAssignments().size());
    }
}
