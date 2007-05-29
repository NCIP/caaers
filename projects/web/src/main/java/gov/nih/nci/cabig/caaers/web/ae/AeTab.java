package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

/**
 * @author Rhett Sutphin
 */
public abstract class AeTab<C extends AdverseEventInputCommandInterface> extends TabWithFields<C> {
    public AeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
}
