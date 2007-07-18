package gov.nih.nci.cabig.caaers.web.study;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kulasekaran
 */
@CaaersUseCases({ CREATE_STUDY })
public class SearchStudyControllerTest extends WebTestCase {
	
	private SearchStudyController controller = new SearchStudyController();
    private StudyService studyService;	    
    
    protected void setUp() throws Exception {
        super.setUp();
        studyService = registerMockFor(StudyService.class);               
        controller.setStudyService(studyService);
        controller.setConfigurationProperty(new ConfigProperty());
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
