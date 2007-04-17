package gov.nih.nci.cabig.caaers.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;

/**
 * @author Rhett Sutphin
 */
public class SampleController extends AbstractTabbedFlowFormController<Object> {
    private Log log = LogFactory.getLog(getClass());

    public SampleController() {
        setFlow(new Flow<Object>("Sample"));
        getFlow().addTab(new Tab("Sample page", "Samp 1", "sample"));
        getFlow().addTab(new Tab("Sample page", "Samp 2", "sample"));
        getFlow().addTab(new Tab("Sample page", "Samp 3", "sample"));
        getFlow().addTab(new Tab("Sample page", "Samp 4", "sample"));
        getFlow().addTab(new Tab("Sample page", "Samp 5", "sample"));
        setCommandClass(Object.class);
    }

    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        log.info("Sample controller Finished");
        return new ModelAndView(new RedirectView("/pages/ae/sample", true));
    }

    private static class Tab extends gov.nih.nci.cabig.ctms.web.tabs.Tab<Object> {
        public Tab(String longTitle, String shortTitle, String viewName) {
            super(longTitle, shortTitle, viewName);
        }
    }
}
