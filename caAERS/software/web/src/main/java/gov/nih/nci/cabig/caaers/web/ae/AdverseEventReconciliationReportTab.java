package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class AdverseEventReconciliationReportTab extends TabWithFields<AdverseEventReconciliationCommand>{

    public AdverseEventReconciliationReportTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(AdverseEventReconciliationCommand command) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
