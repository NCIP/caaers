package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import org.easymock.classextension.EasyMock;

/**
 * 
 * @author Biju Joseph
 *
 */
public class AdeersReportGeneratorTest extends CaaersTestCase {
	private AdeersReportGenerator generator;
	private AdverseEventReportSerializer mockSerializer;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		generator = new AdeersReportGenerator();
		mockSerializer = registerMockFor(AdverseEventReportSerializer.class);
		generator.setAdverseEventReportSerializer(mockSerializer);
	}

	public void testGenerateCaaersXml() throws Exception {
		String retValue = "hello biju";
		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
		EasyMock.expect(mockSerializer.serialize(aeReport)).andReturn(retValue);
		replayMocks();
		String caAERSXML = generator.generateCaaersXml(aeReport);
		assertEquals(retValue, caAERSXML);
		verifyMocks();
	}

	public void testGenerateExternalReports() throws Exception {
		String retValue = "String";
		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        Report r = Fixtures.createReport("abc");
        r.setReportDefinition(Fixtures.createReportDefinition("RD"));
        r.getReportDefinition().setReportFormatType(ReportFormatType.CUSTOM_REPORT);
        String[] s = generator.generateExternalReports(r, "", 77);
        assertEquals(System.getProperty("java.io.tmpdir") + "/CustomReport-77.pdf", s[0]);
	}

}
