package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Map;

/**
 * 
 * @author Biju Joseph
 * 
 */
public class ReviewAssignmentTab extends TabWithFields<AssignParticipantStudyCommand> {

    public ReviewAssignmentTab() {
        super("Review", "Review", "par/reg_review_submit");
//        super("Review and Submit", "Review and Submit", "par/par_confirmation");
    }

    public Map<String, InputFieldGroup> createFieldGroups(AssignParticipantStudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }
}