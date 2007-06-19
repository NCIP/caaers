package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

/**
 * @author Krikor Krumlian
 */
public abstract class AeRoutTab extends TabWithFields<RoutineAdverseEventInputCommand> {
    public AeRoutTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
}
