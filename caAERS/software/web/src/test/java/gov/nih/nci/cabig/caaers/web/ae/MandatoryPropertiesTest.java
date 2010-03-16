package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import junit.framework.TestCase;

/**
 * @author Rhett Sutphin
 * @ahtor Biju Joseph
 */
public class MandatoryPropertiesTest extends TestCase {
    private MandatoryProperties mandatory;

    private ExpeditedReportTree tree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tree = new ExpeditedReportTree();
        mandatory = new MandatoryProperties(tree);
    }

    public void testAddFieldFromDefinition() throws Exception {
        ReportMandatoryFieldDefinition def = new ReportMandatoryFieldDefinition(
                        "participantHistory.baselinePerformanceStatus", RequirednessIndicator.MANDATORY);
        mandatory.addNode(def.getFieldPath());
        assertEquals(1, mandatory.getMandatoryNodes().size());
        assertSame(tree.find("participantHistory.baselinePerformanceStatus"), mandatory
                        .getMandatoryNodes().iterator().next());
    }

    public void testIsMandatoryForMandatorySimpleField() throws Exception {
        mandatory.add(tree.find("participantHistory.baselinePerformanceStatus"));
        assertTrue(mandatory.isMandatory("participantHistory.baselinePerformanceStatus"));
    }

    public void testIsMandatoryForNonMandatorySimpleField() throws Exception {
        assertFalse(mandatory.isMandatory("participantHistory.baselinePerformanceStatus"));
    }

    public void testIsMandatoryForMandatoryListProperty() throws Exception {
        mandatory.add(tree.find("treatmentInformation.courseAgents[].dose.amount"));
        assertTrue(mandatory.isMandatory("treatmentInformation.courseAgents[0].dose.amount"));
        assertTrue(mandatory.isMandatory("treatmentInformation.courseAgents[3].dose.amount"));
        assertFalse(mandatory.isMandatory("treatmentInformation.courseAgents[3].dose.unit"));
    }

    public void testIsMandatoryForBothPartsOfCodedOrOther() throws Exception {
        mandatory.add(tree.find("saeReportPreExistingConditions[].preExistingCondition"));
        assertTrue(mandatory.isMandatory("saeReportPreExistingConditions[0].preExistingCondition"));
        assertTrue(mandatory.isMandatory("saeReportPreExistingConditions[0].other"));
    }
}
