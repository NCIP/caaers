package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * Test case to test convrsion of jaxb study object to domain study object and call to studymigrator with study domain object.
 * @author Monish Dombla
 *
 */
public class StudyProcessorImplTest extends CaaersTestCase {
	
	private StudyProcessorImpl studyProcessorImpl = null;
	private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private gov.nih.nci.cabig.caaers.webservice.Studies studies = null;
	private File xmlFile = null;
	private StudyDao studyDao = null;
	Identifier identifier = null;
	Organization organization = null;
	Study updatedStudy = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
		unmarshaller = jaxbContext.createUnmarshaller();
		studyProcessorImpl = (StudyProcessorImpl)getDeployedApplicationContext().getBean("studyProcessorImpl");
		studyDao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");
		createStudy();
	}
	
	@Override
    protected void tearDown() throws Exception {
		
		if(updatedStudy != null){
			studyDao.delete(updatedStudy);
		}
		
        super.tearDown();
    }
	
	
	public void testStudyUpdateOfInstanceAtt(){
		
		try {
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/StudyUpdateOfInstanceAtt.xml")[0].getFile();
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			
			List<gov.nih.nci.cabig.caaers.webservice.Study> studyList = studies.getStudy();
			
			if(studyList!=null && !studyList.isEmpty()){
				Iterator<gov.nih.nci.cabig.caaers.webservice.Study> iterator = studyList.iterator();
				while(iterator.hasNext()){
					gov.nih.nci.cabig.caaers.webservice.Study studyDto = iterator.next();
					studyProcessorImpl.updateStudy(studyDto);
				}
			}
			updatedStudy = studyDao.getByShortTitle("Study_PCS_Updated");
			updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
			
			assertNotNull(updatedStudy);
			
			assertEquals("Pancreatic Cancer Study ph 5 Updated", updatedStudy.getLongTitle());
			assertEquals("Precis_Updated", updatedStudy.getPrecis());
			assertEquals("Test Study_Updated", updatedStudy.getDescription());
			
			assertEquals("Phase III Trial", updatedStudy.getPhaseCode());
			assertEquals("Temporarily Closed to Accrual", updatedStudy.getStatus());
			assertEquals("BLIND", updatedStudy.getDesign().name());
			
			assertFalse(updatedStudy.getMultiInstitutionIndicator());
			assertFalse(updatedStudy.getAdeersReporting());
			
			assertFalse(updatedStudy.getDrugAdministrationTherapyType());
			assertFalse(updatedStudy.getRadiationTherapyType());
			assertFalse(updatedStudy.getDeviceTherapyType());
			assertFalse(updatedStudy.getSurgeryTherapyType());
			assertFalse(updatedStudy.getBehavioralTherapyType());
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	
	
	private void createStudy(){
		
		try {
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateStudyTest.xml")[0].getFile();
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			
			List<gov.nih.nci.cabig.caaers.webservice.Study> studyList = studies.getStudy();
			
			if(studyList!=null && !studyList.isEmpty()){
				Iterator<gov.nih.nci.cabig.caaers.webservice.Study> iterator = studyList.iterator();
				while(iterator.hasNext()){
					gov.nih.nci.cabig.caaers.webservice.Study studyDto = iterator.next();
					studyProcessorImpl.createStudy(studyDto);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
		
	}
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
	
}
