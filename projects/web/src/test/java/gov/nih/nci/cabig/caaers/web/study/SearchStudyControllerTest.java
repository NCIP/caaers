package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kulasekaran
 */
public class SearchStudyControllerTest extends WebTestCase {
	
	private SearchStudyController controller = new SearchStudyController();
    private StudyService studyService;	    
    
    protected void setUp() throws Exception {
        super.setUp();
        studyService = registerMockFor(StudyService.class);               
        controller.setStudyService(studyService);
       // controller.setListValues(new ListValues());
    }
    
    public void testViewOnGet() throws Exception {
        request.setMethod("GET");        
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("study/study_search", mv.getViewName());
    }

    public void testViewOnAddSearch() throws Exception {    	
        request.setParameter("_selected", "-1");
        controller.handleRequest(request, response);
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("study/study_search", mv.getViewName());
    }   
   
}
