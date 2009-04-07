package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import junit.framework.TestCase;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class CreateOrganizationControllerTest.
 * 
 * @author Saurabh Agrawal
 */
public class CreateOrganizationControllerTest extends TestCase {
    private CreateOrganizationController controller;

    private OrganizationDao organizationDao;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    private ServletRequestDataBinder binder;

    private Organization organization;

    private WebControllerValidator validator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        organizationDao = EasyMock.createMock(OrganizationDao.class);
        validator = EasyMock.createMock(WebControllerValidator.class);
        controller = new CreateOrganizationController();
        controller.setWebControllerValidator(validator);
        controller.setOrganizationDao(organizationDao);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        organization = new LocalOrganization();

    }

    public void testViewOnGoodSubmit() throws Exception {
        try {
            controller.handleRequest(request, response);
            fail("Should have throught the RequestNotSupportedException");
        } catch (Exception e) {
        }
        request.setMethod("POST");
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("admin/organization_details", mv.getViewName());

        binder = new ServletRequestDataBinder(organization, "organization");

        request.setParameter("name", "test name");
        request.setParameter("descriptionText", "test desc");
        binder.bind(request);
        assertEquals("test name", organization.getName());
        assertEquals("test desc", organization.getDescriptionText());

        mv = controller.handleRequest(request, response);
        assertEquals("admin/organization_details", mv.getViewName());

        request.setParameter("nciInstituteCode", "abc");
        binder.bind(request);
        assertEquals("abc", organization.getNciInstituteCode());

        mv = controller.handleRequest(request, response);
        // organization was added..
        assertEquals("admin/organization_details", mv.getViewName());

    }

    public void testViewOnSubmit() throws Exception {
        try {
            controller.handleRequest(request, response);
            fail("Should have throught the RequestNotSupportedException");
        } catch (Exception e) {
        }
        request.setMethod("POST");
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("admin/organization_details", mv.getViewName());

        binder = new ServletRequestDataBinder(organization, "organization");

        request.setParameter("name", "test name");
        request.setParameter("descriptionTest", "test desc");
        binder.bind(request);
        assertEquals("test name", organization.getName());
    }

}
