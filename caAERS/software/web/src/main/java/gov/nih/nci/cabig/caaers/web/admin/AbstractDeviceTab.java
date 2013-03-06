/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListServiceImpl;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Ion C. Olaru
 *
 */
public abstract class AbstractDeviceTab extends TabWithFields<DeviceCommand> {

    protected static final Log log = LogFactory.getLog(AbstractDeviceTab.class);
    private DeviceDao deviceDao;

    public AbstractDeviceTab(String lName, String sName, String vName) {
        super(lName, sName, vName);
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, DeviceCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        return refdata;
    }

    @Override
    public void postProcess(HttpServletRequest request, DeviceCommand command, Errors errors) {
        super.postProcess(request, command, errors);
        request.setAttribute("flashMessage", getMessage("MSG_DEVICE.saved", null, Locale.getDefault()));
        if (request.getParameter(AbstractAjaxFacade.AJAX_REQUEST) != null) return;
    }

    @Override
    public void onBind(HttpServletRequest request, DeviceCommand command, Errors errors) {
        super.onBind(request, command, errors);
    }

    @Override
    protected void validate(final DeviceCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
    }

    //
    @Override
    public Map<String, InputFieldGroup> createFieldGroups(DeviceCommand c) {
        return new InputFieldGroupMap();
    }

}
