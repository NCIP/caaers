package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

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
		generator.setAeReportSerializer(mockSerializer);
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

}
