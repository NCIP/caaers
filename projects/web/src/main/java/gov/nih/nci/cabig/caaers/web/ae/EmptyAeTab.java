package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;
import java.util.Collections;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class EmptyAeTab extends AeTab {
    public EmptyAeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    @Override
    protected Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        return Collections.emptyMap();
    }
}
