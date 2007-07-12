package gov.nih.nci.cabig.caaers.web.search;

import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.search.RoutineReportController;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * @author Krikor Krumlian
 */
public class RoutineReportControllerTest extends WebTestCase {
    private RoutineReportController controller = new RoutineReportController();

    protected void setUp() throws Exception {
        super.setUp();
        controller.setListValues(new ListValues());
    }
   
    public void testViewOnGoodSubmit() throws Exception {

        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("search/routine_report_search", mv.getViewName());
    }
}
