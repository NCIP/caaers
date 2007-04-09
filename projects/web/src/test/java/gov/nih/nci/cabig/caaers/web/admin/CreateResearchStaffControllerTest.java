package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kulasekaran
 */
public class CreateResearchStaffControllerTest extends WebTestCase {
	
	private CreateResearchStaffController controller = new CreateResearchStaffController();
    private ResearchStaffDao researchStaffDao;
    	
    protected void setUp() throws Exception {
        super.setUp();
        researchStaffDao = registerDaoMockFor(ResearchStaffDao.class);        
        
        controller.setResearchStaffDao(researchStaffDao);
		controller.setConfigurationProperty(new ConfigProperty());
		controller.setListValues(new ListValues());
    }
    
    public void testViewOnGet() throws Exception {
        request.setMethod("GET");
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("admin/research_staff_details", mv.getViewName());
    }
}
