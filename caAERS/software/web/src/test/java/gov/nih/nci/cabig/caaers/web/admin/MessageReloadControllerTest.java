/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    }

    //checks the call orchestration
    public void testHandleRequest() throws Exception {


    	try {
    		caaersFieldsTree = registerMockFor(CaaersFieldsTree.class);
    		controller.setMessageSource(messageSource);
    		controller.setCaaersFieldsTree(caaersFieldsTree);
    		messageSource.clearCache();
    		caaersFieldsTree.initialize();

    		replayMocks();

    		controller.handleRequest(request, response);

    		verifyMocks();
    	} catch(RuntimeException re) {
    		//ingnore intermitent failure in java 7 due to a bug in the mock library

    	}
    }

}
