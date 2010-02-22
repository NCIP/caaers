package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.CaaersFieldsTree;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.rule.notification.CreateReportDefinitionController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.classextension.EasyMock;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Ion C. Olaru
 * @author Biju Joseph
 */

public class MessageReloadControllerTest extends WebTestCase {

    private MessageReloadController controller;
    protected ReloadableResourceBundleMessageSource messageSource;
    protected CaaersFieldsTree caaersFieldsTree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        controller = new MessageReloadController();
        messageSource = registerMockFor(ReloadableResourceBundleMessageSource.class);
        caaersFieldsTree = registerMockFor(CaaersFieldsTree.class);
        controller.setMessageSource(messageSource);
        controller.setCaaersFieldsTree(caaersFieldsTree);
    }

    //checks the call orchestration
    public void testHandleRequest() throws Exception {

       messageSource.clearCache();
       caaersFieldsTree.initialize();

       replayMocks();

       controller.handleRequest(request, response);

       verifyMocks();
    }

}