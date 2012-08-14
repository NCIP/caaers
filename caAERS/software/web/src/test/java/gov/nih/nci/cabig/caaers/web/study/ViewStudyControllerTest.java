package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: Biju Joseph
 */
public class ViewStudyControllerTest extends WebTestCase {
    ViewStudyController controller;

    public void setUp() throws Exception {
        super.setUp();
        controller = new ViewStudyController();
    }

    public void testHandleRequestInternal() throws Exception {
        ModelAndView mav = controller.handleRequestInternal(request, response);
        assertEquals("study/study_confirmation", mav.getViewName());
    }
}
