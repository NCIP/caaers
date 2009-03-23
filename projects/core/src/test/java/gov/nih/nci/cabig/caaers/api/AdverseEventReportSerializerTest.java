package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.TestCase;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventReportSerializerTest extends TestCase {
	AdverseEventReportSerializer serializer;
	
	protected void setUp() throws Exception {
		super.setUp();
		serializer = new AdverseEventReportSerializer();
	}
	
	/**
	 * Runs the serialize in parallel... (30 threads), fails the test when there is an exception. 
	 * @throws Exception
	 */
	public void testSerializeExpeditedAdverseEventReport() throws Exception{
		Study study = Fixtures.createStudy("test");
		study.setId(44);
		ExpeditedAdverseEventReport aeReport = generateExpeditedReport();
		
		//assignments
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(1, "02/02/2008", "03/03/2008");
		reportingPeriod.addAeReport(aeReport);
		//treatmentassignments
		aeReport.getTreatmentInformation().getTreatmentAssignment().setStudy(study);
		
		
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
	public ExpeditedAdverseEventReport generateExpeditedReport() throws Exception{
		
		String xmlFile = "expedited-report-caaers.xml";
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
				Thread.sleep(sleep);
				String xml = serializer.serialize(aeReport);
				//System.out.println(worker.getName() + " ::: " + xml);
			}catch(Exception e) {
				parentThread.interrupt(); //interrupt the parent thread so that we can stop testing. 
				e.printStackTrace();
		    }
		}
	}


}
