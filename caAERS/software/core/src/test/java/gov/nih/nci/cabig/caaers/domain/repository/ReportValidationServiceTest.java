package gov.nih.nci.cabig.caaers.domain.repository;

import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.ATTRIBUTION_SECTION;

import java.util.Collections;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;

/**
 *  
 * @author Sameer Sawant
 */


public class ReportValidationServiceTest extends AbstractNoSecurityTestCase {
	
	private ReportValidationServiceImpl reportValidationService;
	private ExpeditedReportTree expeditedReportTree;
	private ExpeditedAdverseEventReport expeditedData;
	
	private static final String TERM = "Auralmonagem";
	private static final Attribution[] SUFFICIENT_ATTRIBUTIONS = new Attribution[]{
        Attribution.POSSIBLE, Attribution.PROBABLE, Attribution.DEFINITE};

	private static final Attribution[] INSUFFICENT_ATTRIBUTIONS = new Attribution[]{
        Attribution.UNLIKELY, Attribution.UNRELATED};
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        expeditedReportTree = new ExpeditedReportTree();
        reportValidationService = new ReportValidationServiceImpl();
        
        reportValidationService.setExpeditedReportTree(expeditedReportTree);
        
        expeditedData = new ExpeditedAdverseEventReport();
        CtcTerm ctcTerm = new CtcTerm();
        ctcTerm.setTerm(TERM);
        expeditedData.getAdverseEvents().get(0).getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
	}
	
	private ReportSubmittability validateForAttribution() {
        return reportValidationService.validate(createAttributionMandatoryReport(), Collections
                .<ExpeditedReportSection>emptySet());
    }
	
	private Report createAttributionMandatoryReport() {
        Report report = new Report();
        report.setReportDefinition(new ReportDefinition());
        report.setAeReport(expeditedData);
        report.getReportDefinition().setAttributionRequired(true);
        // TODO:
        // report.setAttributionMandatory(true);
        return report;
    }
	
	private void assertNoAttributionsAreSufficent(AdverseEventAttribution attr) {
        for (Attribution attribution : Attribution.values()) {
            attr.setAttribution(attribution);
            ReportSubmittability actual = validateForAttribution();
            assertInsuffientAttributionMessage(attribution + " should not be sufficient", actual);
        }
    }

    private void assertSufficientAttributionsAreSufficent(AdverseEventAttribution attr) {
        for (Attribution attribution : SUFFICIENT_ATTRIBUTIONS) {
            attr.setAttribution(attribution);
            ReportSubmittability actual = validateForAttribution();
            assertTrue(attribution + " should be sufficent", actual.isSubmittable());
        }

        for (Attribution attribution : INSUFFICENT_ATTRIBUTIONS) {
            attr.setAttribution(attribution);
            ReportSubmittability actual = validateForAttribution();
            assertInsuffientAttributionMessage(attribution + " should not be sufficent", actual);
        }
    }
    
    private void assertInsuffientAttributionMessage(String assertionMessage,
            ReportSubmittability container) {
    		assertTrue(assertionMessage + ": No attribution section messages", container.getMessages()
    				.containsKey(ATTRIBUTION_SECTION));
    		assertTrue(assertionMessage + ": No attribution section messages", container.getMessages()
    				.get(ATTRIBUTION_SECTION).size() > 0);
    		assertEquals(
    				assertionMessage + ": Wrong message",
    				"The adverse event "
    				+ TERM
    				+ " is not attributed to a cause. An attribution of possible or higher must be selected for at least one of the causes.",
    				container.getMessages().get(ATTRIBUTION_SECTION).get(0).getText());
    }

    public void testIsSubmittable() throws Exception{
    	//TODO Add logic to test isSubmittable method
    }
}