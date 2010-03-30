package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.*;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventReportSerializerTest extends AbstractTestCase {
	AdverseEventReportSerializer serializer;
	
	protected void setUp() throws Exception {
		super.setUp();
		serializer = new AdverseEventReportSerializer();
	}
	
	/**
	 * Tests the serialize of a full blown report
	 * @throws Exception
	 */
	public void testFullblownReportSerailization() throws Exception{
		String xmlFileName = "expedited_report_caaers_complete.xml";
		ExpeditedAdverseEventReport aeReport = generateExpeditedReport(xmlFileName);
		String serailzedXML = serializer.serialize(aeReport, null);
		assertNotNull(serailzedXML);
	}
	
	
	/**
	 * Runs the serialize in parallel... (30 threads), fails the test when there is an exception. 
	 * @throws Exception
	 */
	public void testSerializeExpeditedAdverseEventReport() throws Exception{
		String xmlFile = "expedited-report-caaers.xml";
		Study study = Fixtures.createStudy("test");
		study.setId(44);
		ExpeditedAdverseEventReport aeReport = generateExpeditedReport(xmlFile);
		
		//assignments
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(1, "02/02/2008", "03/03/2008");
		reportingPeriod.addAeReport(aeReport);
		//treatmentassignments
		aeReport.getTreatmentInformation().getTreatmentAssignment().setStudy(study);

        Report r = Fixtures.createReport( "test");
        r.setId(3);
        r.getReportDefinition().setReportType(ReportType.NOTIFICATION);
        aeReport.getReports().add(r);
		
		Thread currentThread = Thread.currentThread();
		for(int i = 0; i < 30; i++ ){
			
			if(currentThread.isInterrupted()) fail("The report serailzation failed, in child thread");
			
			DelayedSerializer delayedSeralizer = new DelayedSerializer("DS-" + i, 200, serializer, aeReport, currentThread);
			
			if(i > 25)	delayedSeralizer.worker.join(); //make sure this thread waits for the others to complete
		}
		
	}
	/**
	 * Recreates the Expedited Report from a sample Xml.
	 * @return
	 * @throws Exception
	 */
	public ExpeditedAdverseEventReport generateExpeditedReport(String xmlFile) throws Exception {
		Mapping mapping = new Mapping();
		mapping.loadMapping(ClassLoader.getSystemResource(serializer.getMappingFile()));
		
		Unmarshaller unMarshaller = new Unmarshaller(ExpeditedAdverseEventReport.class);
		unMarshaller.setValidation(false);
		unMarshaller.setMapping(mapping);
		Reader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(xmlFile));
		Object o = unMarshaller.unmarshal(reader);
		return (ExpeditedAdverseEventReport) o;
	}

	
	/**
	 * This class invokes the serialize method, after a specified sleeptime. 
	 * @author Biju Joseph
	 *
	 */
	public class DelayedSerializer  implements Runnable {
		private Thread parentThread;
		public Thread worker;
		private int sleep;
		private AdverseEventReportSerializer serializer;
		private ExpeditedAdverseEventReport aeReport;

		private DelayedSerializer(String name, int sleep, AdverseEventReportSerializer serializer, ExpeditedAdverseEventReport aeReport, Thread parentThread) {
			this.sleep = sleep;
			this.serializer = serializer;
			this.aeReport = aeReport;
			this.parentThread = parentThread;
			
			worker = new Thread(this, name);
			worker.start();
		}

		public void run() {
			try {
				SecurityTestUtils.switchToSuperuser();
				Thread.sleep(sleep);
                List<Report> reports = aeReport.getReports();
                Report r =  reports.get(0);
				String xml = serializer.serialize(aeReport,r);
			}catch(Exception e) {
				parentThread.interrupt(); //interrupt the parent thread so that we can stop testing. 
				e.printStackTrace();
		    }
		}
	}

    public void testSerializerWithMandatoryFields() throws Exception {

        String xmlFileName = "expedited_report_caaers_complete.xml";
        ExpeditedAdverseEventReport aeReport = generateExpeditedReport(xmlFileName);

        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field.one[].name", RequirednessIndicator.MANDATORY));
        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field[].two.name", RequirednessIndicator.MANDATORY));
        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field.three.name", RequirednessIndicator.NA));
        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field.four.name[]", RequirednessIndicator.MANDATORY));
        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field.five.name", RequirednessIndicator.MANDATORY));

        aeReport.getReports().get(0).getReportDefinition().setHeader("THIS IS HEADER");
        aeReport.getReports().get(0).getReportDefinition().setFooter("THIS IS FOOTER");

        aeReport.getReports().get(0).getReportDefinition().setReportType(ReportType.REPORT);
        aeReport.getReports().get(0).getReportDefinition().setDescription("Some Description");
        aeReport.getReports().get(0).setAeReport(aeReport);

        Fixtures.updateMandatoryFields(aeReport.getReports().get(0).getReportDefinition(),aeReport.getReports().get(0));

        String xml = serializer.serialize(aeReport, aeReport.getReports().get(0));
        System.out.println(xml);
        
        assertTrue(xml.indexOf("<applicableField>") >= 0);
        assertTrue(xml.indexOf("</applicableField>") >= 0);
        assertTrue(xml.indexOf("field.one.name") >= 0);
        assertTrue(xml.indexOf("field.two.name") >= 0);
        assertTrue(xml.indexOf("field.three.name") == -1);
        assertTrue(xml.indexOf("field.four.name") >= 0);
        assertTrue(xml.indexOf("field.five.name") >= 0);

    }
}
