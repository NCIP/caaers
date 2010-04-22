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
public class AgentTab<C extends AgentCommand> extends AbstractAgentTab {

    protected static final Log log = LogFactory.getLog(AgentTab.class);

    public AgentTab(String lName, String sName, String vName) {
        super(lName, sName, vName);
    }

    @Override
    public void onBind(HttpServletRequest request, AgentCommand command, Errors errors) {
        super.onBind(request, command, errors);
    }

    @Override
    protected void validate(final AgentCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
    }

}