package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Map;

public class ReviewParticipantTab extends TabWithFields<ParticipantInputCommand> {

    public ReviewParticipantTab() {
        super("Review and Submit", "Review", "par/par_confirmation");
    }

    public Map<String, InputFieldGroup> createFieldGroups(ParticipantInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    @Override
    public Map<String, Object> referenceData(ParticipantInputCommand command) {
        return super.referenceData(command);
    }

}
