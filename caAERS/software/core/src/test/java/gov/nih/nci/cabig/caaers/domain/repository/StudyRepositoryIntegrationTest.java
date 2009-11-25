package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.workflow.StudySiteWorkflowConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */
public class StudyRepositoryIntegrationTest extends CaaersDbNoSecurityTestCase {

    private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean(
            "organizationDao");

    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean(
            "participantDao");

    StudyRepository studyRepository = (StudyRepository) getApplicationContext().getBean("studyRepository");

    public String getTestDataFileName() {
        String fileName = "testdata/StudyRepositoryTest.xml";
        return fileName;
    }
    
    public void testGet(){
    }

    public void testSearchStudyByExample() throws Exception {
        Study study = new LocalStudy();
        study.setShortTitle("Gemcitabine");
        OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
        organizationAssignedIdentifier.setType("NCI assigned identifier");
        organizationAssignedIdentifier.setValue("NCI-2009-00008");
        study.addIdentifier(organizationAssignedIdentifier);
        List<Study> studies = studyRepository.search(study);
        assertNotNull("Studes is null", studies);
    }
    
    public void testFind(){
    	StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
    	query.filterStudiesWithMatchingIdentifierOnly("NCI-2009-00009");
    	List<Object[]> objects = studyRepository.search(query,"idtf","NCI-2009-00009");
    	assertNotNull(objects);
    	
    }
    
    //will test pasing empty sites to associate method. (should not fail with NPE)
    public void testAssociateSiteToWorkflowConfig_WhenNoSitesPresent(){
    	studyRepository.associateSiteToWorkflowConfig(null);
    	studyRepository.associateSiteToWorkflowConfig(new ArrayList<StudySite>());
    }
    
    //will do nothing if site is already associated to workflow.
    public void testAssociateSiteToWorkflowConfig_AlreadyAssociatedToAWorkflow(){
    	StudySiteWorkflowConfig cfg = new StudySiteWorkflowConfig();
    	ArrayList<StudySite> ssList =new ArrayList<StudySite>();
    	StudySite ss1 = new StudySite();
    	ss1.addStudySiteWorkflowConfig(cfg);
    	ssList.add(ss1);
    	StudySite ss2 = new StudySite();
    	ss2.addStudySiteWorkflowConfig(cfg);
    	ssList.add(ss2);
    	studyRepository.associateSiteToWorkflowConfig(ssList);
    	
    	assertEquals(1, ss1.getStudySiteWorkflowConfigs().size());
    	assertEquals(1, ss2.getStudySiteWorkflowConfigs().size());
    	assertSame(cfg, ss1.getStudySiteWorkflowConfigs().get(0));
    	assertSame(cfg, ss2.getStudySiteWorkflowConfigs().get(0));
    }
    
    //retrieve a study, and then save it, again retrieve it to see that workflow got associated to it. 
    public void testSave_GettingAssociatedToWorkflow(){
    	//retrieve a study
    	StudyQuery query = new StudyQuery();
    	query.filterByShortTitle("New Study");
    	{

        	List<? extends Study> studies = studyRepository.find(query);
        	assertNotNull(studies);
        	assertFalse(studies.isEmpty());
        	assertEquals(1, studies.size());
        	
        	//verify it is not associated to workflow.
        	assertTrue(studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().isEmpty());
        	
        	//associate to workflow by calling save. 
        	studyRepository.save(studies.get(0));
        	assertFalse(studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().isEmpty());
        	assertEquals(2, studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().size());
    	}
    	interruptSession();
    	{
    		//retrieve
    		List<? extends Study> studies = studyRepository.find(query);
    		assertFalse(studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().isEmpty());
    		assertEquals("reportingPeriod", studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().get(0).getName());
    		assertEquals("report", studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().get(1).getName());
    	}
    	
    }
    
    
    //retrieve a study, and then save it, again retrieve it to see that workflow got associated to it. 
    public void testMerge_GettingAssociatedToWorkflow(){
    	//retrieve a study
    	StudyQuery query = new StudyQuery();
    	query.filterByShortTitle("New Study");
    	{

        	List<? extends Study> studies = studyRepository.find(query);
        	assertNotNull(studies);
        	assertFalse(studies.isEmpty());
        	assertEquals(1, studies.size());
        	
        	//verify it is not associated to workflow.
        	assertTrue(studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().isEmpty());
        	
        	//associate to workflow by calling save. 
        	Study mergedStudy = studyRepository.merge(studies.get(0));
        	assertFalse(mergedStudy.getStudySites().get(0).getStudySiteWorkflowConfigs().isEmpty());
        	assertEquals(2, mergedStudy.getStudySites().get(0).getStudySiteWorkflowConfigs().size());
    	}
    	interruptSession();
    	{
    		//retrieve
    		List<? extends Study> studies = studyRepository.find(query);
    		assertFalse(studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().isEmpty());
    		assertEquals("reportingPeriod", studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().get(0).getName());
    		assertEquals("report", studies.get(0).getStudySites().get(0).getStudySiteWorkflowConfigs().get(1).getName());
    	}
    	
    }
}