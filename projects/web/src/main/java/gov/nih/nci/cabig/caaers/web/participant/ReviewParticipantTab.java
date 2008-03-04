package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Map;

public class ReviewParticipantTab extends Tab<NewParticipantCommand> {

    public ReviewParticipantTab() {
        super("Review and Submit", "Review", "par/par_confirmation");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        return refdata;
    }

}
