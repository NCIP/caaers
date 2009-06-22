package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.caaers.web.ListValues;

import java.util.Map;

public class ParticipantStudyTab extends gov.nih.nci.cabig.ctms.web.tabs.Tab<NewParticipantCommand> {
    private ListValues listValues;

    public ParticipantStudyTab(final String longTitle, final String shortTitle, final String viewName, ListValues listValues) {
        super(longTitle, shortTitle, viewName);
        this.listValues = listValues;
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("searchType", listValues.getStudySearchType());
        return refdata;
    }

  
}
