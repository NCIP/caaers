package gov.nih.nci.cabig.caaers.web.tabbedflow;

import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractTabbedFlowFormController<C> extends AbstractWizardFormController {
    private Flow<C> flow;

    public Flow<C> getFlow() {
        return flow;
    }

    public void setFlow(Flow<C> flow) {
        this.flow = flow;
    }

    protected Map<?, ?> referenceData(HttpServletRequest request, int page) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        Tab<C> current = getFlow().getTab(page);
        refdata.put("tab", current);
        refdata.put("flow", getFlow());
        refdata.putAll(current.referenceData());
        return refdata;
    }

    protected int getPageCount(HttpServletRequest request, Object command) {
        return getFlow().getTabCount();
    }

    protected String getViewName(HttpServletRequest request, Object command, int page) {
        return getFlow().getTab(page).getViewName();
    }

    @SuppressWarnings("unchecked")
    protected void validatePage(Object oCommand, Errors errors, int page, boolean finish) {
        C command = (C) oCommand;
        Tab<C> tab = getFlow().getTab(page);

        setAllowDirtyForward(tab.isAllowDirtyForward());
        setAllowDirtyBack(tab.isAllowDirtyBack());

        tab.validate(command, errors);
    }
}
