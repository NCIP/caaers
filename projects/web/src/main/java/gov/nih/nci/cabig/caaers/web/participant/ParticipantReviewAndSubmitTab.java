package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import java.util.Map;

public class ParticipantReviewAndSubmitTab extends
                gov.nih.nci.cabig.ctms.web.tabs.Tab<NewParticipantCommand> {

    public ParticipantReviewAndSubmitTab(final String longTitle, final String shortTitle,
                    final String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        return refdata;
    }

}
