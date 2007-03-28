package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;
import java.util.Collections;

/**
 * @author Rhett Sutphin
 */
public class EmptyAeTab extends AeTab {
    public EmptyAeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    @Override
    protected List<InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        return Collections.emptyList();
    }
}
