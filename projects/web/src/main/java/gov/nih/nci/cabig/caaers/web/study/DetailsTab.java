package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.Map;
import java.util.List;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class DetailsTab extends StudyTab {
    public DetailsTab() {
        super("Study Details", "Details", "study/study_details");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        addConfigMapToRefdata(refdata, "diseaseCodeRefData");
        addConfigMapToRefdata(refdata, "monitorCodeRefData");
        addConfigMapToRefdata(refdata, "phaseCodeRefData");
        addConfigMapToRefdata(refdata, "sponsorCodeRefData");
        addConfigMapToRefdata(refdata, "statusRefData");
        addConfigMapToRefdata(refdata, "typeRefData");
        refdata.put("multiInstitutionIndicator", getConfigurationProperty().getMap().get("yesNo"));
        refdata.put("randomizedIndicator",getConfigurationProperty().getMap().get("yesNo"));
        refdata.put("blindedIndicator", getConfigurationProperty().getMap().get("yesNo"));
        refdata.put("nciIdentifier", getConfigurationProperty().getMap().get("yesNo"));
        return refdata;
    }

    @Override
    public void validate(Study command, Errors errors) {
        boolean longTitle = command.getLongTitle() == null || "".equals(command.getLongTitle());
        if (longTitle) errors.rejectValue("longTitle", "REQUIRED", "Missing Long Title");
    }

    @Override
    public boolean isAllowDirtyForward() {
        return false;
    }
}
