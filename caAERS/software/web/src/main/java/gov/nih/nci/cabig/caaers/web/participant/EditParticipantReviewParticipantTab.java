package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.web.fields.*;

import java.util.Map;

public class EditParticipantReviewParticipantTab<T extends ParticipantInputCommand> extends TabWithFields<T> {

    public EditParticipantReviewParticipantTab() {
        super("Participant Review", "Review", "par/par_confirmation");
        addFieldDecorators(new SecurityObjectIdFieldDecorator(Participant.class), new ReadonlyFieldDecorator());
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