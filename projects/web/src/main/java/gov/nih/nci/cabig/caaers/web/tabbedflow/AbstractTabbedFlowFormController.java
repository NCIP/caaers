package gov.nih.nci.cabig.caaers.web.tabbedflow;

import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractTabbedFlowFormController extends AbstractWizardFormController {
    private Flow flow;

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    protected Map<?, ?> referenceData(HttpServletRequest request, int page) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        Tab current = getFlow().getTab(page);
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
}
