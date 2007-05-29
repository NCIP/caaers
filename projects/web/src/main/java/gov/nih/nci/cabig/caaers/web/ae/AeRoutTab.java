package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

/**
 * @author Krikor Krumlian
 */
public abstract class AeRoutTab<C extends RoutineAdverseEventInputCommand> extends TabWithFields<C> {
    public AeRoutTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
}
