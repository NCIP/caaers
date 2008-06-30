package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

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
		updatedStudy = studyDao.getByShortTitle("Study_PCS");
		if(updatedStudy != null){
			updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
			studyDao.delete(updatedStudy);
		}
		
	}
	
	@Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
	
	/**
	 * Tests the update of attributes shortTitle,longTitle,percis,description,phaseCode,
	 * status,design,multiInsitutionIndicator,adeersReporting, 
	 * Also tests the update of Study Therapies.
	 * DrugAdministrationTherapyType,RadiationTherapyType,DeviceTherapyType,SurgeryTherapyType,
	 * BehavioralTherapyType
	 * 
	 */
	public void testStudyUpdateOfInstanceAtt(){
		
		createStudy("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateStudyTest.xml");
		
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
			
			if(updatedStudy != null){
				studyDao.delete(updatedStudy);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	
	
	/**
	 * theCode1 and theCode3 are 2 TreatmentAssignments for the study with shorttitle Study PSC.
	 * Tests Treatmentassignment updates. The attributes doseLevelOrder,description and comments 
	 * are updated for 2 exisitng treatmentassignments.
	 */
	public void testStudyUpdateOfTreatmentAssignmentAttr(){
		
		createStudy("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateStudyTest.xml");
		
		try {
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/StudyUpdateOfTreatmentAssignmentAttr.xml")[0].getFile();
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			
			List<gov.nih.nci.cabig.caaers.webservice.Study> studyList = studies.getStudy();
			
			if(studyList!=null && !studyList.isEmpty()){
				Iterator<gov.nih.nci.cabig.caaers.webservice.Study> iterator = studyList.iterator();
				while(iterator.hasNext()){
					gov.nih.nci.cabig.caaers.webservice.Study studyDto = iterator.next();
					studyProcessorImpl.updateStudy(studyDto);
				}
			}
			
			updatedStudy = studyDao.getByShortTitle("Study_PCS");
			updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
			
			assertNotNull(updatedStudy);
			
			assertEquals(2, updatedStudy.getTreatmentAssignments().size());
			
			for(TreatmentAssignment treatmentAssignment : updatedStudy.getTreatmentAssignments()){
				if(treatmentAssignment.getCode().equals("theCode1")){
					assertEquals(2, treatmentAssignment.getDoseLevelOrder().intValue());
					assertEquals("Description_Updated", treatmentAssignment.getDescription());
					assertEquals("Comments_Updated", treatmentAssignment.getComments());
				}
				if(treatmentAssignment.getCode().equals("theCode3")){
					assertEquals(3, treatmentAssignment.getDoseLevelOrder().intValue());
					assertEquals("Description3_Updated", treatmentAssignment.getDescription());
					assertEquals("Comments3_Updated", treatmentAssignment.getComments());
				}
			}
			
			if(updatedStudy != null){
				studyDao.delete(updatedStudy);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	
	
	/**
	 * Study with short title Study PSC has 2 TreatmentAssignments.
	 * This testcase tests the addition of a TreatmentAssignment to an existing study.
	 * theCode1 and theCode3 are the existing treatment assignments.
	 * theCode4 is the new treamentAssignment.
	 */
	public void testStudyUpdateOfTreatmentAssignmentAdd(){
		
		createStudy("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateStudyTest.xml");
		
		try {
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/StudyUpdateOfTreatmentAssignmentAdd.xml")[0].getFile();
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			
			List<gov.nih.nci.cabig.caaers.webservice.Study> studyList = studies.getStudy();
			
			if(studyList!=null && !studyList.isEmpty()){
				Iterator<gov.nih.nci.cabig.caaers.webservice.Study> iterator = studyList.iterator();
				while(iterator.hasNext()){
					gov.nih.nci.cabig.caaers.webservice.Study studyDto = iterator.next();
					studyProcessorImpl.updateStudy(studyDto);
				}
			}
			
			updatedStudy = studyDao.getByShortTitle("Study_PCS");
			updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
			
			assertNotNull(updatedStudy);
			
			assertEquals(3, updatedStudy.getTreatmentAssignments().size());
			
			for(TreatmentAssignment treatmentAssignment : updatedStudy.getTreatmentAssignments()){
				if(treatmentAssignment.getCode().equals("theCode1")){
					assertEquals(1, treatmentAssignment.getDoseLevelOrder().intValue());
					assertEquals("description1", treatmentAssignment.getDescription());
					assertEquals("Comments1", treatmentAssignment.getComments());
				}
				if(treatmentAssignment.getCode().equals("theCode3")){
					assertEquals(2, treatmentAssignment.getDoseLevelOrder().intValue());
					assertEquals("description3", treatmentAssignment.getDescription());
					assertEquals("Comments3", treatmentAssignment.getComments());
				}
				if(treatmentAssignment.getCode().equals("theCode4")){
					assertEquals(4, treatmentAssignment.getDoseLevelOrder().intValue());
					assertEquals("description4", treatmentAssignment.getDescription());
					assertEquals("Comments4", treatmentAssignment.getComments());
				}
			}
			
			if(updatedStudy != null){
				studyDao.delete(updatedStudy);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	
	
	/**
	 * Study with short title Study PSC has 2 TreatmentAssignments.
	 * This testcase tests the deletion of a TreatmentAssignment from existing study.
	 * theCode1 and theCode3 are the existing treatment assignments.
	 * theCode3 is the treamentAssignment removed/deleted.
	 */
	public void testStudyUpdateOfTreatmentAssignmentRemove(){
		
		createStudy("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateStudyTest.xml");
		
		try {
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/StudyUpdateOfTreatmentAssignmentRemove.xml")[0].getFile();
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			
			List<gov.nih.nci.cabig.caaers.webservice.Study> studyList = studies.getStudy();
			
			if(studyList!=null && !studyList.isEmpty()){
				Iterator<gov.nih.nci.cabig.caaers.webservice.Study> iterator = studyList.iterator();
				while(iterator.hasNext()){
					gov.nih.nci.cabig.caaers.webservice.Study studyDto = iterator.next();
					studyProcessorImpl.updateStudy(studyDto);
				}
			}
			
			updatedStudy = studyDao.getByShortTitle("Study_PCS");
			updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
			
			assertNotNull(updatedStudy);
			
			assertEquals(1, updatedStudy.getTreatmentAssignments().size());
			
			for(TreatmentAssignment treatmentAssignment : updatedStudy.getTreatmentAssignments()){
				if(treatmentAssignment.getCode().equals("theCode1")){
					assertEquals(1, treatmentAssignment.getDoseLevelOrder().intValue());
					assertEquals("description1", treatmentAssignment.getDescription());
					assertEquals("Comments1", treatmentAssignment.getComments());
				}
			}
			
			if(updatedStudy != null){
				studyDao.delete(updatedStudy);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	
	
	/**
	 * 2 ctepStudyDiseases with term "Chondrosarcoma" and "Medulloblastoma" exists.
	 * Tests the addition of an other ctepStudyDisease with term "Osteosarcoma"
	 */
	public void testStudyUpdateOfCtepStudyDiseasesAdd(){
		
		createStudy("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateStudyTest.xml");
		
		try {
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/StudyUpdateOfCtepStudyDiseasesAdd.xml")[0].getFile();
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			
			List<gov.nih.nci.cabig.caaers.webservice.Study> studyList = studies.getStudy();
			
			if(studyList!=null && !studyList.isEmpty()){
				Iterator<gov.nih.nci.cabig.caaers.webservice.Study> iterator = studyList.iterator();
				while(iterator.hasNext()){
					gov.nih.nci.cabig.caaers.webservice.Study studyDto = iterator.next();
					studyProcessorImpl.updateStudy(studyDto);
				}
			}
			
			updatedStudy = studyDao.getByShortTitle("Study_PCS");
			updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
			
			assertNotNull(updatedStudy);
			
			assertEquals(3, updatedStudy.getCtepStudyDiseases().size());
			
			for(CtepStudyDisease ctepStudyDisease : updatedStudy.getCtepStudyDiseases()){
				if(ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Chondrosarcoma")){
					assertFalse(ctepStudyDisease.getLeadDisease());
				}
				if(ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Osteosarcoma")){
					assertFalse(ctepStudyDisease.getLeadDisease());
				}
				if(ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Medulloblastoma")){
					assertTrue(ctepStudyDisease.getLeadDisease());
				}
			}
			
			if(updatedStudy != null){
				studyDao.delete(updatedStudy);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	
	
	
	/**
	 * 2 ctepStudyDiseases with terms "Chondrosarcoma" and "Medulloblastoma" exists.
	 * Tests the deletion of ctepStudyDisease with term "Chondrosarcoma"
	 */
	public void testStudyUpdateOfCtepStudyDiseasesRemove(){
		
		createStudy("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateStudyTest.xml");
		
		try {
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/StudyUpdateOfCtepStudyDiseasesRemove.xml")[0].getFile();
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			
			List<gov.nih.nci.cabig.caaers.webservice.Study> studyList = studies.getStudy();
			
			if(studyList!=null && !studyList.isEmpty()){
				Iterator<gov.nih.nci.cabig.caaers.webservice.Study> iterator = studyList.iterator();
				while(iterator.hasNext()){
					gov.nih.nci.cabig.caaers.webservice.Study studyDto = iterator.next();
					studyProcessorImpl.updateStudy(studyDto);
				}
			}
			
			updatedStudy = studyDao.getByShortTitle("Study_PCS");
			updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
			
			assertNotNull(updatedStudy);
			
			assertEquals(1, updatedStudy.getCtepStudyDiseases().size());
			
			for(CtepStudyDisease ctepStudyDisease : updatedStudy.getCtepStudyDiseases()){
				if(ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Chondrosarcoma")){
					assertFalse(ctepStudyDisease.getLeadDisease());
				}
				if(ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Medulloblastoma")){
					assertTrue(ctepStudyDisease.getLeadDisease());
				}
			}
			
			if(updatedStudy != null){
				studyDao.delete(updatedStudy);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	
	
	/**
	 * 2 ctepStudyDiseases with terms "Chondrosarcoma" and "Medulloblastoma" exists.
	 * leadDisease = false for Chondrosarcoma 
	 * leadDisease = true for  Medulloblastoma
	 * 
	 * Tests the leadDisease change.
	 * leadDisease = true for Chondrosarcoma
	 * leadDisease = false for  Medulloblastoma
	 */
	public void testStudyUpdateOfCtepStudyDiseasesUpdate(){
		
		createStudy("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateStudyTest.xml");
		
		try {
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/StudyUpdateOfCtepStudyDiseasesUpdate.xml")[0].getFile();
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			
			List<gov.nih.nci.cabig.caaers.webservice.Study> studyList = studies.getStudy();
			
			if(studyList!=null && !studyList.isEmpty()){
				Iterator<gov.nih.nci.cabig.caaers.webservice.Study> iterator = studyList.iterator();
				while(iterator.hasNext()){
					gov.nih.nci.cabig.caaers.webservice.Study studyDto = iterator.next();
					studyProcessorImpl.updateStudy(studyDto);
				}
			}
			
			updatedStudy = studyDao.getByShortTitle("Study_PCS");
			updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
			
			assertNotNull(updatedStudy);
			
			assertEquals(2, updatedStudy.getCtepStudyDiseases().size());
			
			for(CtepStudyDisease ctepStudyDisease : updatedStudy.getCtepStudyDiseases()){
				System.out.println("Term  " + ctepStudyDisease.getDiseaseTerm().getCtepTerm());
				if(ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Chondrosarcoma")){
					assertTrue(ctepStudyDisease.getLeadDisease());
				}
				if(ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Medulloblastoma")){
					assertFalse(ctepStudyDisease.getLeadDisease());
				}
			}
			
			if(updatedStudy != null){
				studyDao.delete(updatedStudy);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	
	
	private void createStudy(String studyXmlLocation){
		
		try {
			xmlFile = getResources(studyXmlLocation)[0].getFile();
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
