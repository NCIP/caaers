/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.*;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.encoding.ser.ArrayDeserializer;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 
 * @author Biju Joseph
 * @author Ion C. Olaru
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
	public void testFullblownReportSerailization() throws Exception {
		String xmlFileName = "expedited_report_caaers_complete.xml";
		ExpeditedAdverseEventReport aeReport = generateExpeditedReport(xmlFileName);
		String serializedXML = serializer.serialize(aeReport, null);
		assertNotNull(serializedXML);
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

        for (Report report : aeReport.getReports()) {
            report.setMandatoryFields(new ArrayList<ReportMandatoryField>());
        }

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

        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field.one.name", RequirednessIndicator.MANDATORY));
        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field.two.name", RequirednessIndicator.MANDATORY));
        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field.three.name", RequirednessIndicator.NA));
        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field.four.name", RequirednessIndicator.MANDATORY));
        aeReport.getReports().get(0).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("field.five.name", RequirednessIndicator.MANDATORY));

        aeReport.getReports().get(0).getReportDefinition().setHeader("THIS IS HEADER");
        aeReport.getReports().get(0).getReportDefinition().setFooter("THIS IS FOOTER");

        MedicalDevice md = new MedicalDevice();
        md.setStudyDevice(new StudyDevice());
        md.getStudyDevice().setDevice(new Device());
        md.setBrandName("Brand name");
        md.setCommonName("Common name");
        md.setCommonName("Common name");
        md.setManufacturerName("Manufacturer name");
        md.setManufacturerCity("Manufacturer city");
        md.setManufacturerState("Manufacturer state");
        aeReport.addMedicalDevice(md);

        AdverseEvent ae = new AdverseEvent();
        aeReport.getAdverseEvents().get(0).setEventLocation("SOME LOCATION");
        TimeValue tv = new TimeValue();
        tv.setHour(22);
        tv.setMinute(40);
        aeReport.getAdverseEvents().get(0).setEventApproximateTime(tv);

        ae = new AdverseEvent();
        ae.setAdverseEventMeddraLowLevelTerm(new AdverseEventMeddraLowLevelTerm());
        ae.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(new LowLevelTerm());
        ae.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm().setMeddraCode("888");
        ae.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm().setMeddraTerm("T888");
        ae.setDetailsForOther("Verbatim");
        aeReport.getAdverseEvents().add(ae);

        ConfigProperty cp = new ConfigProperty();
        cp.setCode("RT_FDA");
        cp.setDescription("FDA Report");
        cp.setName("FDA Report");
        cp.setConfigType(ConfigPropertyType.REPORT_GROUP);
        aeReport.getReports().get(0).getReportDefinition().setGroup(cp);

        ReportDeliveryDefinition rdd1 = new ReportDeliveryDefinition();
        rdd1.setId(1001);
        rdd1.setEndPoint("EndPoint 1001");
        rdd1.setEntityName("EntityName 1001");
        rdd1.setEndPointType("EndPointType 1001");
        aeReport.getReports().get(0).getReportDefinition().addReportDeliveryDefinition(rdd1);

        ReportDeliveryDefinition rdd2 = new ReportDeliveryDefinition();
        rdd2.setId(1002);
        rdd2.setEndPoint("EndPoint 1002");
        rdd2.setEntityName("EntityName 1002");
        rdd2.setEndPointType("EndPointType 1002");
        aeReport.getReports().get(0).getReportDefinition().addReportDeliveryDefinition(rdd2);

        aeReport.getReports().get(0).getReportDefinition().setDuration(10);
        aeReport.getReports().get(0).getReportDefinition().setTimeScaleUnitType(TimeScaleUnit.DAY);

        ReportDelivery rd1 = new ReportDelivery();
        rd1.setDeliveryStatus(DeliveryStatus.DELIVERED);
        rd1.setEndPoint("Some endpoint");
        rd1.setReportDeliveryDefinition(rdd1);
        aeReport.getReports().get(0).addReportDelivery(rd1);

        ReportDelivery rd2 = new ReportDelivery();
        rd2.setDeliveryStatus(DeliveryStatus.SCHEDULED);
        rd2.setEndPoint("Some endpoint 2");
        aeReport.getReports().get(0).addReportDelivery(rd2);
        rd2.setReportDeliveryDefinition(rdd2);

        aeReport.getReports().get(0).getReportDefinition().setReportType(ReportType.REPORT);
        aeReport.getReports().get(0).getReportDefinition().setDescription("Some Description");
        aeReport.getReports().get(0).setAeReport(aeReport);

        Fixtures.updateMandatoryFields(aeReport.getReports().get(0).getReportDefinition(),aeReport.getReports().get(0));

        String xml = serializer.serialize(aeReport, aeReport.getReports().get(0));
        
        assertTrue(xml.indexOf("<applicableField>") >= 0);
        assertTrue(xml.indexOf("</applicableField>") >= 0);
        assertTrue(xml.indexOf("field.one.name") >= 0);
        assertTrue(xml.indexOf("field.two.name") >= 0);
        assertTrue(xml.indexOf("field.three.name") == -1);
        assertTrue(xml.indexOf("field.four.name") >= 0);
        assertTrue(xml.indexOf("field.five.name") >= 0);
        // System.out.println(xml);

    }
}
