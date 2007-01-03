package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.security.exceptions.CSException;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
public class LoginControllerTest extends WebTestCase {

    private LoginController controller;
    private LoginCommand command;

    protected void setUp() throws Exception {
        super.setUp();

        command = registerMockFor(LoginCommand.class, LoginCommand.class.getMethod("login", String.class));
        controller = new LoginController() {
        	   
            protected Object formBackingObject(HttpServletRequest request) throws Exception {
                return command;
            }
        };
    }

    public void testViewOnGet() throws Exception {
        request.setMethod("GET");	
        ModelAndView view = controller.handleRequest(request, response);
        assertEquals("login", view.getViewName());
    }
    
    public void testPostWithValidCredentialsAndNoRequestedUrl() throws Exception {
        expectLogin(true);
        replayMocks();
        ModelAndView actual = controller.handleRequest(request, response);
        verifyMocks();
        assertTrue("Default view not a redirect", actual.getView() instanceof RedirectView);
        RedirectView actualView = (RedirectView) actual.getView();
        assertEquals("/pages/mainMenu", actualView.getUrl());
    }
    
    public void testPostWithBadCredentials() throws Exception {
        expectLogin(false);

        replayMocks();
        ModelAndView actual = controller.handleRequest(request, response);
        verifyMocks();

        assertTrue("failed indicator missing", (Boolean) actual.getModel().get("failed"));
        assertEquals("login", actual.getViewName());
    }
    
    private void expectLogin(boolean success) throws CSException {
    	EasyMock.expect(command.login(request.getRemoteAddr())).andReturn(success);
    }
}