package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class EditParticipantCommand extends ParticipantInputCommand {

    public EditParticipantCommand(Participant participant) {
        super(participant);
    }

    @Override
    public List<StudyParticipantAssignment> getAssignments() {
        return getParticipant().getAssignments(); 
    }
}