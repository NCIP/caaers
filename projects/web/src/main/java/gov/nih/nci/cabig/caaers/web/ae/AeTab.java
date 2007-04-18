package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

/**
 * @author Rhett Sutphin
 */
public abstract class AeTab extends TabWithFields<AdverseEventInputCommand> {
    public AeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
}
