package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Tab;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.BindException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author Rhett Sutphin
 */
public class SampleController extends AbstractTabbedFlowFormController {
    private Log log = LogFactory.getLog(getClass());

    public SampleController() {
        setFlow(new Flow("Sample", Arrays.asList(
            new Tab(0, "Sample page", "Samp 1", "sample"),
            new Tab(1, "Sample page", "Samp 2", "sample"),
            new Tab(2, "Sample page", "Samp 3", "sample"),
            new Tab(3, "Sample page", "Samp 4", "sample"),
            new Tab(4, "Sample page", "Samp 5", "sample")
        )));
        setCommandClass(Object.class);
    }

    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        log.info("Sample controller Finished");
        return new ModelAndView(new RedirectView("/pages/ae/sample", true));
    }
}
