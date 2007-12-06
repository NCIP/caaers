package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.ccts.grid.Study;
import gov.nih.nci.ccts.grid.client.StudyConsumerClient;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;

import junit.framework.TestCase;

public class CaaersStudyConsumerTest extends TestCase {
	CaaersStudyConsumer studyConsumer;
    private String studyResourceName;
    private String serviceUrl;
    private String configLoction;
    
    
    
	protected void setUp() throws Exception {
	 super.setUp();
	 this.configLoction = "C:/devtools/workspace/REF-StudyRegistration/src/gov/nih/nci/ccts/grid/client/client-config.wsdd";	
     this.studyResourceName = "C:/devtools/workspace//REF-StudyRegistration/test/resources/SampleStudyMessage.xml";
     //this.serviceUrl = "http://10.10.10.2:8015/wsrf/services/cagrid/StudyConsumer";
     //this.serviceUrl = "http://localhost:8080/wsrf/services/cagrid/StudyConsumer";
     this.serviceUrl = "http://cbvapp-d1017.nci.nih.gov:18080/wsrf/services/cagrid/StudyConsumer";  
     studyConsumer = new CaaersStudyConsumer();
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
	
	public void testCreateDeleteInLoopRemote() throws Exception {
		int cnt = 0;
		for(int i = 0; i < 15 ; i++){
			try {
				StudyConsumerClient studyClient = new StudyConsumerClient(serviceUrl);
				Study study = obtainStudyDTO();
				String idValue = study.getIdentifier(0).getValue();
				study.getIdentifier(0).setValue(idValue + i);
				studyClient.createStudy(study);
				studyClient.rollback(study);
				
				cnt++;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}	
		}
		assertEquals("Count should be 14", 14, cnt); 
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
			studyDTO = (Study) gov.nih.nci.cagrid.common.Utils.deserializeObject(reader, Study.class,fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
        return studyDTO;
	}
	
	
}
