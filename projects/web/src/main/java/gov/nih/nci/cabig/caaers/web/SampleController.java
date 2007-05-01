package gov.nih.nci.cabig.caaers.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;

import java.util.Map;
import java.util.LinkedHashMap;

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
    protected Map<?, ?> referenceData(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
        Map refdata = super.referenceData(request, command, errors, page);
        Map<String, String> summary = new LinkedHashMap<String, String>();
        summary.put("Participant", "Curabitur Tellus");
        summary.put("Study", "Nunc vel odio vivamus mattis libero a dolor in at nibh donec mattis fermentum ligula");
        refdata.put("summary", summary);
        return refdata;
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        log.info("Sample controller finished");
        return new ModelAndView(new RedirectView("/pages/ae/sample", true));
    }

    private static class Tab extends gov.nih.nci.cabig.ctms.web.tabs.Tab<Object> {
        public Tab(String longTitle, String shortTitle, String viewName) {
            super(longTitle, shortTitle, viewName);
        }
    }
}
