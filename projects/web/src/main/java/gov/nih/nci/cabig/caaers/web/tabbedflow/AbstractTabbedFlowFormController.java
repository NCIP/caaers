package gov.nih.nci.cabig.caaers.web.tabbedflow;

import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.validation.Errors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractTabbedFlowFormController<C> extends AbstractWizardFormController {
    private final Log log = LogFactory.getLog(getClass());

    private Flow<C> flow;

    public Flow<C> getFlow() {
        return flow;
    }

    public void setFlow(Flow<C> flow) {
        this.flow = flow;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map<?, ?> referenceData(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        Tab<C> current = getFlow().getTab(page);
        refdata.put("tab", current);
        refdata.put("flow", getFlow());
        refdata.putAll(current.referenceData((C) command));
        log.debug("Returning reference data for page " + page);
        log.debug("Command is " + command);
        return refdata;
    }

    @Override
    protected int getPageCount(HttpServletRequest request, Object command) {
        return getFlow().getTabCount();
    }

    @Override
    protected String getViewName(HttpServletRequest request, Object command, int page) {
        return getFlow().getTab(page).getViewName();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void validatePage(Object oCommand, Errors errors, int page, boolean finish) {
        C command = (C) oCommand;
        Tab<C> tab = getFlow().getTab(page);

        setAllowDirtyForward(tab.isAllowDirtyForward());
        setAllowDirtyBack(tab.isAllowDirtyBack());

        tab.validate(command, errors);
    }
}
