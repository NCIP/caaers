package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.report.Report;

import org.apache.commons.io.IOUtils;
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
        Report r = Fixtures.createReport("test");
		EasyMock.expect(mockSerializer.serialize(aeReport, r)).andReturn(retValue);
		replayMocks();
		String caAERSXML = generator.generateCaaersXml(aeReport, r);
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
	public void testWithdrawXml() throws Exception {
		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
		aeReport.setId(1);
		Study study = new LocalStudy() ;
		study.setShortTitle("test study");
		Identifier identifier= new Identifier();
		identifier.setPrimaryIndicator(true);
		identifier.setValue("001");
		identifier.setType("Protocol Authority Identifier");
		study.addIdentifier(identifier);
		Identifier identifier2= new Identifier();
		identifier2.setPrimaryIndicator(false);
		identifier2.setValue("002");
		identifier2.setType("Coordinating Center Identifier");	
		study.addIdentifier(identifier2);
		StudyParticipantAssignment spa = new StudyParticipantAssignment();
		StudySite studySite = new StudySite();
		studySite.setStudy(study);
		spa.setStudySite(studySite);
		
		AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
		reportingPeriod.setAssignment(spa);
		aeReport.setReportingPeriod(reportingPeriod);
		

		Report r = Fixtures.createReport("abc");
		r.setId(1);
        r.setReportDefinition(Fixtures.createReportDefinition("RD"));
        r.getReportDefinition().setReportFormatType(ReportFormatType.CUSTOM_REPORT);
        aeReport.addReport(r);
        
        AdeersReportGenerator aeGenerator = (AdeersReportGenerator)getDeployedApplicationContext().getBean("adeersReportGenerator");
        String xml = aeGenerator.generateCaaersWithdrawXml(aeReport, r);
        
		String data =  IOUtils.toString(getClass().getResourceAsStream("withdraw_aereport.xml"));

        assertEquals(data.trim(),xml.trim());
		
		
	}

}
