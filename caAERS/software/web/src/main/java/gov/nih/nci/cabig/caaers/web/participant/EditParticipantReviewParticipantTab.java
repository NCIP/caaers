package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.Map;

public class EditParticipantReviewParticipantTab<T extends ParticipantInputCommand> extends TabWithFields<T> {

    public EditParticipantReviewParticipantTab() {
        super("Participant Review", "Review", "par/par_confirmation");
    }
    @Override
    public Map<String, InputFieldGroup> createFieldGroups(T command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    @Override
    public Map<String, Object> referenceData(T command) {
        return super.referenceData(command);
    }

}