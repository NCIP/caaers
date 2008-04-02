package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * @author Kulasekaran
 */
public class CreateResearchStaffControllerTest extends WebTestCase {

    private CreateResearchStaffController controller = new CreateResearchStaffController();

    private ResearchStaffRepository researchStaffRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        researchStaffRepository = registerMockFor(ResearchStaffRepository.class);

        //controller.setResearchStaffRepository(researchStaffRepository);
        // controller.setConfigurationProperty(new ConfigProperty());
        // controller.setListValues(new ListValues());
    }

    public void testViewOnGet() throws Exception {
        assert true;
        // request.setMethod("GET");
        // ModelAndView mv = controller.handleRequest(request, response);
        // assertEquals("admin/research_staff_details", mv.getViewName());
    }
}
