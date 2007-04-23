package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class OtherCausesTab extends AeTab {
    public OtherCausesTab() {
        super("Other contributing causes", "Other causes", "ae/other");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        RepeatingFieldGroupFactory factory = new RepeatingFieldGroupFactory("otherCause",
            "aeReport.otherCauses");
        factory.addField(new DefaultTextArea("text", "Cause", true));

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addRepeatingFieldGroupFactory(factory, command.getAeReport().getOtherCauses().size());
        return map;
    }
}
