package gov.nih.nci.cabig.caaers.rules.objectgraph;

import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;

import java.util.Date;

import org.springframework.beans.NotReadablePropertyException;

import junit.framework.TestCase;

public class NullSafeFieldExtractorTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExtractField() {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getResponseDescription().setPresentStatus(null);
		Object o = NullSafeFieldExtractor.extractField(aeReport, "responseDescription.presentStatus.displayName");
		System.out.println(o);
	}
	public void testExtractFieldNullInput() {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getResponseDescription().setPresentStatus(null);
		Object o = NullSafeFieldExtractor.extractField(null, "responseDescription.presentStatus.displayName");
		System.out.println(o);
	}
	
	public void testExtractFieldInvalidProperty() {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getResponseDescription().setPresentStatus(null);
		try {
			Object o = NullSafeFieldExtractor.extractField(aeReport, "xresponseDescription.presentStatus.displayName");
		} catch ( NotReadablePropertyException e) {
			
		}
	}
	
	public ExpeditedAdverseEventReport createAEReport(){
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		// update event description
		AdverseEventResponseDescription aeResponseDesc = new AdverseEventResponseDescription();
		aeResponseDesc.setEventDescription("This is a sample description");
		aeResponseDesc.setRecoveryDate(new Date());
		aeResponseDesc.setDateRemovedFromProtocol(new Date());
		aeResponseDesc.setPresentStatus(PostAdverseEventStatus.DEAD);
		aeReport.setResponseDescription(aeResponseDesc);
		return aeReport;
	}

}
