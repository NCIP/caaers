package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.UnsatisfiedProperty;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

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
        tree = new ExpeditedReportTree(null);
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
        mandatory.addNode("participantHistory.baselinePerformanceStatus");
        assertTrue(mandatory.isMandatory("participantHistory.baselinePerformanceStatus"));
    }

    public void testIsMandatoryForNonMandatorySimpleField() throws Exception {
        assertFalse(mandatory.isMandatory("participantHistory.baselinePerformanceStatus"));
    }

    public void testIsMandatoryForMandatoryListProperty() throws Exception {
        mandatory.addNode(("treatmentInformation.courseAgents[].dose.amount"));
        assertTrue(mandatory.isMandatory("treatmentInformation.courseAgents[0].dose.amount"));
        assertTrue(mandatory.isMandatory("treatmentInformation.courseAgents[3].dose.amount"));
        assertFalse(mandatory.isMandatory("treatmentInformation.courseAgents[3].dose.unit"));
    }

    public void testIsMandatoryForBothPartsOfCodedOrOther() throws Exception {
        mandatory.addNode(("saeReportPreExistingConditions[].preExistingCondition"));
        assertTrue(mandatory.isMandatory("saeReportPreExistingConditions[0].preExistingCondition"));
        assertTrue(mandatory.isMandatory("saeReportPreExistingConditions[0].other"));
    }

    public void testAddRealPropertyPath(){
        mandatory.addRealPropertyPath("participantHistory.baselinePerformanceStatus");
        assertTrue(mandatory.getRealPropertyPaths().contains("participantHistory.baselinePerformanceStatus"));
        mandatory.addRealPropertyPath("saeReportPreExistingConditions[0].preExistingCondition");
        assertTrue(mandatory.getRealPropertyPaths().contains("saeReportPreExistingConditions[0].preExistingCondition"));
        assertFalse(mandatory.getRealPropertyPaths().contains("saeReportPreExistingConditions[1].preExistingCondition"));
    }

    public void testIsMandatory(){
           mandatory.addNode("participantHistory.baselinePerformanceStatus");
           mandatory.addRealPropertyPath("saeReportPreExistingConditions[0].preExistingCondition");
           assertTrue(mandatory.isMandatory(tree.find("participantHistory.baselinePerformanceStatus")));
           assertTrue(mandatory.isMandatory("participantHistory.baselinePerformanceStatus"));
           assertTrue(mandatory.isMandatory(tree.find("saeReportPreExistingConditions[0].preExistingCondition")));
           assertTrue(mandatory.isMandatory("saeReportPreExistingConditions[0].preExistingCondition"));
           assertFalse(mandatory.isMandatory("saeReportPreExistingConditions[1].preExistingCondition"));
    }

    public void testIsAnyMandatory(){
           mandatory.addNode("participantHistory.baselinePerformanceStatus");
           mandatory.addRealPropertyPath("saeReportPreExistingConditions[0].preExistingCondition");
           assertTrue(mandatory.isAnyMandatory(tree));
           assertFalse(mandatory.isAnyMandatory(tree.find("adverseEvents")));
           assertFalse(mandatory.isAnyMandatory(tree.find("radiationInterventions")));
           assertTrue(mandatory.isAnyMandatory(tree.find("participantHistory")));
           assertTrue(mandatory.isAnyMandatory(tree.find("saeReportPreExistingConditions")));
    }


    public void testGetUnsatisfied(){
        ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
        assertTrue(mandatory.getUnsatisfied(tree.find("participantHistory"), aeReport).isEmpty());

        mandatory.addNode("participantHistory.baselinePerformanceStatus");
        assertFalse(mandatory.getUnsatisfied(tree.find("participantHistory"), aeReport).isEmpty());
        assertTrue(mandatory.getUnsatisfied(tree.find("adverseEvents"), aeReport).isEmpty());

        mandatory.addRealPropertyPath("saeReportPreExistingConditions[0].preExistingCondition");
        assertFalse(mandatory.getUnsatisfied(tree.find("saeReportPreExistingConditions"), aeReport).isEmpty());

        //add a preexisting condition
        PreExistingCondition preCond = new PreExistingCondition();
        preCond.setText("hello");
        aeReport.getSaeReportPreExistingConditions().get(0).setPreExistingCondition(preCond);
        aeReport.getSaeReportPreExistingConditions().add(new SAEReportPreExistingCondition());// empty pre-cond

        assertTrue(mandatory.getUnsatisfied(tree.find("saeReportPreExistingConditions[0].preExistingCondition"), aeReport).isEmpty());
        mandatory.addRealPropertyPath("saeReportPreExistingConditions[1].preExistingCondition");
        List<UnsatisfiedProperty> unsatisfiedPropertyList =    mandatory.getUnsatisfied(tree.find("saeReportPreExistingConditions[0].preExistingCondition"), aeReport);
        System.out.println(unsatisfiedPropertyList);
        assertTrue(mandatory.getUnsatisfied(tree.find("saeReportPreExistingConditions[1].preExistingCondition"), aeReport).isEmpty());
        List<UnsatisfiedProperty> errors = mandatory.getUnsatisfied(tree.find("saeReportPreExistingConditions[1].preExistingCondition"), aeReport);
        assertEquals(0, errors.size());
    }


    public void testIsMandatoryCompositeField(){
        //adverseEvents[1].eventApproximateTime.hourString

        ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
        mandatory.addRealPropertyPath("adverseEvents[0].eventApproximateTime.hourString");
        AdverseEvent ae = new AdverseEvent();
        ae.setEventApproximateTime(new TimeValue());
        aeReport.addAdverseEvent(ae);
        TreeNode n = tree.find("adverseEvents[0].eventApproximateTime.hourString") ;
        assertNotNull(n);
        assertTrue(mandatory.isMandatory("adverseEvents[0].eventApproximateTime.hourString"));

    }

    
}
