package gov.nih.nci.cabig.caaers.web.search;

import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.search.AdverseEventController;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * @author Krikor Krumlian
 */
public class AdverseEventControllerTest extends WebTestCase {
    private AdverseEventController controller = new AdverseEventController();

    protected void setUp() throws Exception {
        super.setUp();
        controller.setListValues(new ListValues());
    }
   
    public void testViewOnGoodSubmit() throws Exception {

        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("search/ae_search", mv.getViewName());
    }
}
