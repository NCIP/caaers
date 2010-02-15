package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.InvestigatorHeldIND;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationHeldIND;
import gov.nih.nci.cabig.caaers.domain.RemoteStudy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.workflow.StudySiteWorkflowConfig;
import gov.nih.nci.cabig.caaers.resolver.CoppaConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */
public class StudyRepositoryIntegrationTest extends CaaersDbNoSecurityTestCase {

    private OrganizationDao organizationDao;

    private ParticipantDao participantDao;
    
    private InvestigationalNewDrugDao investigationalNewDrugDao;

    StudyRepository studyRepository;

    
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		organizationDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
		participantDao = (ParticipantDao) getApplicationContext().getBean("participantDao");
		investigationalNewDrugDao = (InvestigationalNewDrugDao) getApplicationContext().getBean("investigationalNewDrugDao");
		studyRepository = (StudyRepository) getApplicationContext().getBean("studyRepository");
	}
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
  
   
	public void test_verifyandSaveIND(){
    	
    	RemoteStudy remoteStudy = new RemoteStudy();
    	InvestigationalNewDrug indInvestigationalNewDrug = new InvestigationalNewDrug();
    	LocalOrganization localOrg = new LocalOrganization();
        indInvestigationalNewDrug.setIndNumber(Integer.parseInt("43210"));
        localOrg.setNciInstituteCode("NCI");
        OrganizationHeldIND holder = new OrganizationHeldIND();
        holder.setOrganization(localOrg);
        holder.setInvestigationalNewDrug(indInvestigationalNewDrug);
        indInvestigationalNewDrug.setINDHolder(holder);
        remoteStudy.addInvestigationalNewDrug(indInvestigationalNewDrug);
        
        InvestigationalNewDrug indInvestigationalNewDrug1 = new InvestigationalNewDrug();
        LocalInvestigator localInvestigator = new LocalInvestigator();
		indInvestigationalNewDrug1.setIndNumber(Integer.parseInt("876543"));
		localInvestigator.setNciIdentifier(CoppaConstants.DUMMY_INVESTIGATOR_IDENTIFIER);
		InvestigatorHeldIND holder1 = new InvestigatorHeldIND();
		holder1.setInvestigator(localInvestigator);
		holder1.setInvestigationalNewDrug(indInvestigationalNewDrug1);
		indInvestigationalNewDrug1.setINDHolder(holder1);
		remoteStudy.addInvestigationalNewDrug(indInvestigationalNewDrug1);
		
		studyRepository.verifyAndSaveIND(remoteStudy);
		
		interruptSession();
		{
			List<InvestigationalNewDrug> indList =  investigationalNewDrugDao.getAll();
			assertNotNull(indList);
			assertEquals(2, indList.size());
		}
    }
}