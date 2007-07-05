package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.validation.Errors;

import java.util.Map;

import gov.nih.nci.cabig.ctms.web.tabs.Tab;

/**
 * @author Rhett Sutphin
*/
public class BeginTab<T extends AdverseEventInputCommand> extends Tab<T> {
    public BeginTab() {
        super("Select participant and study", "Begin", "ae/selectAssignment");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("pageTitle", getLongTitle());
        refdata.put("bodyTitle", getLongTitle()); // TODO: this should incorporate the flow name
        refdata.put("instructions",
            "In order to create or edit an AE or SAE, you need to first select a participant and a\n" +
            "study. You may start with either one. Once you have selected one, the options\n" +
            "for the other will be automatically constrained.");
        return refdata;
    }

    @Override
    public void validate(
        T command, Errors errors
    ) {
        boolean noStudy = command.getStudy() == null;
        boolean noParticipant = command.getParticipant() == null;
        if (noStudy) errors.rejectValue("study", "REQUIRED", "Missing study");
        if (noParticipant) errors.rejectValue("participant", "REQUIRED", "Missing participant");
        if (!(noStudy || noParticipant) && command.getAssignment() == null) {
            errors.reject("NO_ASSIGNMENT", "The selected ");
        }
    }
}
