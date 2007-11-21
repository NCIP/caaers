package gov.nih.nci.cabig.caaers.grid;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.AxisClient;
import org.apache.axis.configuration.FileProvider;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.ccts.grid.Registration;
import gov.nih.nci.ccts.grid.Study;
import gov.nih.nci.ccts.grid.client.StudyConsumerClient;
import gov.nih.nci.ccts.grid.common.StudyConsumerI;
import junit.framework.TestCase;

public class CaaersStudyConsumerTest extends TestCase {
	CaaersStudyConsumer studyConsumer;
    private String studyResourceName;
    private String serviceUrl;
    private String configLoction;
    
    
    
	protected void setUp() throws Exception {
	 super.setUp();
	 this.configLoction = "C:/devtools/workspace/REF-StudyRegistration/src/gov/nih/nci/ccts/grid/client/client-config.wsdd";	
     this.studyResourceName = "C:/devtools/workspace//REF-StudyRegistration/test/resources/SampleRegistrationMessage.xml";
      /*System.getProperty("caaers.test.sampleStudyFile",
        "C:/devtools/workspace/REF-StudyRegistration/test/resources/SampleStudyMessage.xml");*/
     this.serviceUrl = "http://localhost:8080/wsrf/services/cagrid/StudyConsumer"; 
    	 /*System.getProperty("caaers.test.serviceUrl",
           	"http://localhost:8080/wsrf/services/cagrid/StudyConsumer");*/
	 studyConsumer = new CaaersStudyConsumer();
	// studyConsumer.setStudyDao((StudyDao)studyConsumer.getBeanFactory().getBean("studyDao"));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCommitRemote() throws Exception {
		try {
			StudyConsumerClient studyClient = new StudyConsumerClient(serviceUrl);
			Study study = obtainStudyDTO();
			studyClient.commit(study);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void testRollbackRemote() throws Exception{
		try {
			StudyConsumerClient studyClient = new StudyConsumerClient(serviceUrl);
			Study study = obtainStudyDTO();
			studyClient.rollback(study);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void testCreateStudyRemote() throws Exception{
		try {
			StudyConsumerClient studyClient = new StudyConsumerClient(serviceUrl);
			Study study = obtainStudyDTO();
			studyClient.createStudy(study);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void testFindCoordinatingCenterIdentifier() {
		fail("Not yet implemented");
	}

	public void testPopulateStudyDetails() {
		fail("Not yet implemented");
	}

	public void testPopulateIdentifiers() {
		fail("Not yet implemented");
	}

	public void testPopulateStudyOrganizations() {
		fail("Not yet implemented");
	}

	public void testPopulateInvestigators() {
		fail("Not yet implemented");
	}

	public void testFetchOrganization() {
		fail("Not yet implemented");
	}

	public void testFetchStudy() {
     System.out.println(studyConsumer.fetchStudy("nci001", OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE));
	}
	
	public Study obtainStudyDTO() throws Exception{
		Study studyDTO = null;
		try {
		Reader reader = new FileReader(studyResourceName);
		FileInputStream fis = new FileInputStream(configLoction);
			studyDTO = (Study) Utils.deserializeObject(reader, Study.class,fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
        return studyDTO;
	}
	
	
}
