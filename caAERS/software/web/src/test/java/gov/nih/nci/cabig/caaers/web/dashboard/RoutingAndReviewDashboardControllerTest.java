package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import junit.framework.TestCase;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: Biju Joseph
 */
public class RoutingAndReviewDashboardControllerTest extends WebTestCase {
    RoutingAndReviewDashboardController controller;
    public void setUp() throws Exception {
        super.setUp();
       controller = new RoutingAndReviewDashboardController();
    }

    public void testHandleRequestInternal() throws Exception {
        ModelAndView mv = controller.handleRequestInternal(request, response);
        assertEquals("dashboard/dashboard_RoutingAndReview" , mv.getViewName());
    }
}
