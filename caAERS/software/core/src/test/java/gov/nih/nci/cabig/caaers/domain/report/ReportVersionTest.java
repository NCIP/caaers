package gov.nih.nci.cabig.caaers.domain.report;

import junit.framework.TestCase;

/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportVersionTest extends TestCase {
	
	ReportVersion rv;
	protected void setUp() throws Exception {
		super.setUp();
		rv = new ReportVersion();
	}

	public void testGetXmlContent_WhenNoContents() {
		assertNull(rv.getXmlContent());
	}
	
	public void testGetXmlContent_WnenNoXmlContent(){
		rv.addReportContent(new ReportContent("abc", "xyz".getBytes()));
		assertNull(rv.getXmlContent());
	}
	public void testGetXmlContent() {
		rv.addReportContent(new ReportContent("abc", "xyz".getBytes()));
		rv.addReportContent(new ReportContent("text/xml", "xyz".getBytes()));
		assertNotNull(rv.getXmlContent());
		assertEquals("xyz", new String(rv.getXmlContent().getContent()));
	}
	
	public void testIncrementReportVersionId(){
		rv.setReportVersionId("2");
		rv.incrementReportVersion();
		assertEquals("3", rv.getReportVersionId());
	}
	
	public void testIncrementReportVersionId_FirstTime(){
		rv.incrementReportVersion();
		assertEquals("0", rv.getReportVersionId());
	}
	
	public void testIncrementAmendmentNumber(){
		assertNull(rv.getAmendmentNumber());
		rv.incrementAmendmentNumber();
		assertEquals(new Integer(0), rv.getAmendmentNumber());
	}
	public void testIncrementAmendmentNumber_NotNull(){
		rv.setAmendmentNumber(5);
		rv.incrementAmendmentNumber();
		assertEquals(new Integer(6), rv.getAmendmentNumber());
	}
}
