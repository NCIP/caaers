package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Ion C. Olaru
 * 
 */
public class DeviceTab<C extends DeviceCommand> extends AbstractDeviceTab {

    protected static final Log log = LogFactory.getLog(DeviceTab.class);

    public DeviceTab(String lName, String sName, String vName) {
        super(lName, sName, vName);
    }

    @Override
    public void onBind(HttpServletRequest request, DeviceCommand command, Errors errors) {
        super.onBind(request, command, errors);
    }

    @Override
    protected void validate(final DeviceCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
    }

}