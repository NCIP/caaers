/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ReconciliationReport;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Biju Joseph
 */
public class AdverseEventReconcileSummaryTab  extends TabWithFields<AdverseEventReconciliationCommand> {

    public AdverseEventReconcileSummaryTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(AdverseEventReconciliationCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, AdverseEventReconciliationCommand command) {
        ReconciliationReport report = command.generateReconcilationReport();
        Map<String, Object> map =  super.referenceData(request, command);
        map.put("report", report);
        return map;
    }
}
