package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.rule.notification.CreateReportDefinitionController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Ion C. Olaru
 */

public class MessageReloadControllerTest extends WebTestCase {

    private MessageReloadController controller = new MessageReloadController();

    public void testMessage() {
        assertNull(controller.getMessageSource());
    }

}