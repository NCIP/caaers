package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Map;

/**
 * @author Kulasekaran
 */
public class MedicalInfoTab extends AeTab {
    public MedicalInfoTab() {
        super("Medical info", "Medical", "ae/medical");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        return new InputFieldGroupMap();
    }

    @Override
    public boolean isAllowDirtyForward() {
        return false;
    }
}
