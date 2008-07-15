package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.study.StudyTab;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventConfirmTab extends TabWithFields<CaptureAdverseEventInputCommand>{
	public AdverseEventConfirmTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
	
	@Override
    public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand command) {
        return new InputFieldGroupMap();
    }
}
