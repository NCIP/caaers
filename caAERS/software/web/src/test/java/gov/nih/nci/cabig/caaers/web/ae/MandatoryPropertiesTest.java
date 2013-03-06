/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
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
        assertFalse(mandatory.getUnsatisfied(tree.find("saeReportPreExistingConditions[0].preExistingCondition"), aeReport).isEmpty());
        assertFalse(mandatory.getUnsatisfied(tree.find("saeReportPreExistingConditions[1].preExistingCondition"), aeReport).isEmpty());
        List<UnsatisfiedProperty> errors = mandatory.getUnsatisfied(tree.find("saeReportPreExistingConditions[1].preExistingCondition"), aeReport);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getBeanPropertyName(), "saeReportPreExistingConditions[1].preExistingCondition");
    }

    
}
