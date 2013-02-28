/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class AdverseEventSelectTab extends TabWithFields<AdverseEventReconciliationCommand> {

    public AdverseEventSelectTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(AdverseEventReconciliationCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }


    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, AdverseEventReconciliationCommand command) {
        Map<String, Object> refData = super.referenceData(request, command);
        Map<Integer,Boolean> rejectedExternalAeMap  = new LinkedHashMap<Integer, Boolean>();
        for(AdverseEventDTO e : command.getRejectedExternalAeList()) {
            rejectedExternalAeMap.put(e.getId(), true);
        }
        refData.put("rejectedExternalAeMap", rejectedExternalAeMap);
        Map<Integer,Boolean> rejectedInternalAeMap  = new LinkedHashMap<Integer, Boolean>();
        for(AdverseEventDTO e : command.getRejectedInternalAeList()) {
            rejectedInternalAeMap.put(e.getId(), true);
        }
        refData.put("rejectedInternalAeMap", rejectedInternalAeMap);
        return refData;
    }


    /**
     * Invoked after successful binding and validation.
     */
    @Override
    public void postProcess(HttpServletRequest request, AdverseEventReconciliationCommand command, Errors errors) {
        if(errors.hasErrors()) return;

        command.processExternalAeRejections();
        command.processInternalAeRejections();
        command.seralizeMapping();
    }
}
