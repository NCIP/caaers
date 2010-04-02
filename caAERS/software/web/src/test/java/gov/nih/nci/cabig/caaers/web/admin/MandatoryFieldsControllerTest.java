package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.CaaersFieldDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.CaaersFieldsTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TabSection;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.rule.notification.EditReportDefinitionController;
import junit.framework.TestCase;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Ion C. Olaru
 *
 */
public class MandatoryFieldsControllerTest extends WebTestCase {

    CaaersFieldsTree tree;
    MandatoryFieldsController controller;
    MandatoryFieldsCommand command;
    CaaersFieldDefinitionDao cfdDao;
    BindException errors;

    public void setUp() throws Exception {
        super.setUp();
        cfdDao = registerDaoMockFor(CaaersFieldDefinitionDao.class);
        command = new MandatoryFieldsCommand(cfdDao);
        tree = new CaaersFieldsTree(null, null);
        controller = new MandatoryFieldsController();
        controller.setCaaersFieldsTree(tree);
        errors = new BindException(command, "command");
    }

    public void testReferenceData() throws Exception {
        command.setMandatoryFields(new ArrayList<CaaersFieldDefinition>());
        Map refData = controller.referenceData(request, command, errors);
        assertEquals(1, refData.size());
        assertEquals(2, ((Map)refData.get("fieldGroups")).size());
    }
}