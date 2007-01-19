package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
public class BeginTab extends AeTab {
    public BeginTab() {
        super("Select participant and study", "Begin", "ae/selectAssignment");
    }

    public void validate(CreateAdverseEventCommand command, Errors errors) {
        boolean noStudy = command.getStudy() == null;
        boolean noParticipant = command.getParticipant() == null;
        if (noStudy) errors.rejectValue("study", "REQUIRED", "Missing study");
        if (noParticipant) errors.rejectValue("participant", "REQUIRED", "Missing participant");
        if (!(noStudy || noParticipant) && command.getAssignment() == null) {
            errors.reject("NO_ASSIGNMENT", "The selected ");
        }
    }

    public boolean isAllowDirtyForward() {
        return false;
    }
}
