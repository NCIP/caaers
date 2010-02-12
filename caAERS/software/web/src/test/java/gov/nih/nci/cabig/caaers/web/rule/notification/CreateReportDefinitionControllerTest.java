package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.search.AdverseEventController;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 */
public class CreateReportDefinitionControllerTest extends WebTestCase {

    private CreateReportDefinitionController controller = new CreateReportDefinitionController();
    
    public void testFlow() throws Exception {
        assertEquals("Create Report Definition", controller.getFlowName());
        assertNotNull(controller.getFlowFactory());
    }

}