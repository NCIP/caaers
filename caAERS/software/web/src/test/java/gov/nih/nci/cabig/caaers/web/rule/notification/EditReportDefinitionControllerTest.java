package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTreeTest;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * EditReportDefinitionController Tester.
 *
 * @author Biju Joseph
 * @since <pre>03/15/2010</pre>
 * 
 */
public class EditReportDefinitionControllerTest extends TestCase {

    ExpeditedReportTree tree;
    EditReportDefinitionController controller;
    public void setUp() throws Exception {
        super.setUp();
        tree = new ExpeditedReportTree();
        controller = new EditReportDefinitionController();
        controller.setExpeditedReportTree(tree);
    }

    //removes duplicate (rd1)
    //removes orphan rd4 (path not available in tree)
    public void testReconcileMandatoryFields(){
        ReportMandatoryFieldDefinition rd1 = Fixtures.createMandatoryField("additionalInformation.autopsyReport", RequirednessIndicator.MANDATORY);
        ReportMandatoryFieldDefinition rd2 = Fixtures.createMandatoryField("additionalInformation.autopsyReport", RequirednessIndicator.MANDATORY);
        ReportMandatoryFieldDefinition rd3 = Fixtures.createMandatoryField("additionalInformation.dischargeSummary", RequirednessIndicator.MANDATORY);
        ReportMandatoryFieldDefinition rd4 = Fixtures.createMandatoryField("test3", RequirednessIndicator.MANDATORY);
        ArrayList<ReportMandatoryFieldDefinition> rdList = new ArrayList<ReportMandatoryFieldDefinition>();

        rdList.addAll(Arrays.asList(rd1, rd2, rd3, rd4));

        assertTrue(rdList.contains(rd1));
        assertTrue(rdList.contains(rd3));
        assertTrue(rdList.contains(rd4));
        assertTrue(rdList.contains(rd2));
        
        controller.reconcileMandatoryFields(rdList, tree);
        
        assertTrue(rdList.contains(rd1));
        assertTrue(rdList.contains(rd3));
        assertFalse(rdList.contains(rd4));
        assertFalse(rdList.contains(rd2));
    }

}
