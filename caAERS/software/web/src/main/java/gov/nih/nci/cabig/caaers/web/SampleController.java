package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Rhett Sutphin
 */
public class SampleController extends AbstractTabbedFlowFormController<Object> {
    private Log log = LogFactory.getLog(getClass());

    public SampleController() {
        setFlow(new Flow<Object>("Sample"));
        getFlow().addTab(new Tab("Sample tab", "Begin", "sample"));
        getFlow().addTab(new Tab("Adverse Events", "AEs", "sample"));
        getFlow().addTab(new Tab("Vital statistics", "Vitals", "sample"));
        getFlow().addTab(new Tab("Treatment & course information", "Course", "sample"));
        getFlow().addTab(new Tab("Labs", "Labs", "sample"));
        setCommandClass(Object.class);
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
                    Object command, BindException errors) throws Exception {
        log.info("Sample controller finished");
        return new ModelAndView(new RedirectView("/pages/ae/sample", true));
    }

    private static class Tab extends gov.nih.nci.cabig.ctms.web.tabs.Tab<Object> {
        public Tab(String longTitle, String shortTitle, String viewName) {
            super(longTitle, shortTitle, viewName);
        }
    }
}
