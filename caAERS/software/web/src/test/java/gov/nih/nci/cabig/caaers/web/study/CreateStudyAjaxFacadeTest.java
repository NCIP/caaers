package gov.nih.nci.cabig.caaers.web.study;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import static org.easymock.EasyMock.aryEq;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;

import java.util.Arrays;
import java.util.List;

import org.easymock.classextension.EasyMock;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@CaaersUseCases( { CREATE_STUDY })
public class CreateStudyAjaxFacadeTest extends DwrFacadeTestCase {
    private CreateStudyAjaxFacade facade;

    protected Study study;
    protected StudyCommand command;

    private SiteInvestigatorDao siteInvestigatorDao;

    private InvestigationalNewDrugDao investigationalNewDrugDao;
    
    private InvestigatorRepository investigatorRepository;
    private ResearchStaffRepository researchStaffRepository;
    
    private StudyDao studyDao;
    StudyRepository studyRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        siteInvestigatorDao = registerDaoMockFor(SiteInvestigatorDao.class);
        investigationalNewDrugDao = registerDaoMockFor(InvestigationalNewDrugDao.class);
        investigatorRepository = this.registerMockFor(InvestigatorRepositoryImpl.class);
        researchStaffRepository = registerMockFor(ResearchStaffRepository.class);
        studyRepository = registerMockFor(StudyRepository.class);
        
        facade = new CreateStudyAjaxFacade();
        facade.setSiteInvestigatorDao(siteInvestigatorDao);
        facade.setInvestigationalNewDrugDao(investigationalNewDrugDao);
        facade.setInvestigatorRepository(investigatorRepository);
        facade.setResearchStaffRepository(researchStaffRepository);
        facade.setStudyRepository(studyRepository);
        
        
        studyDao = registerDaoMockFor(StudyDao.class);
        command = new StudyCommand(studyDao,investigationalNewDrugDao);
        command.setStudyRepository(studyRepository);
        command.setStudyDao(studyDao);
        study = new LocalStudy();
        command.setStudy(study);
        request.getSession().setAttribute(CreateStudyController.class.getName() + ".FORM.command", command);
    }

    public void testSiteInvestigatorsReduced() throws Exception {
        SiteInvestigator expectedInvestigator = new SiteInvestigator();
        expectedInvestigator.setId(34);
        Investigator investigator = new LocalInvestigator();
        investigator.setFirstName("Joe");
        investigator.setLastName("Something");
        expectedInvestigator.setInvestigator(investigator);

        StudySite studySite = new StudySite();
        studySite.setOrganization(setId(6, new LocalOrganization()));
        study.addStudySite(studySite);

        expect(investigatorRepository.getBySubnames(aryEq(new String[] { "foo" }), eq(6))).andReturn(Arrays.asList(expectedInvestigator));
        replayMocks();
        List<SiteInvestigator> results = facade.matchSiteInvestigator("foo", 0);
        verifyMocks();

        assertEquals("Wrong number of results", 1, results.size());
        SiteInvestigator actual = results.get(0);
        assertEquals("id not copied to result", 34, (int) actual.getId());
        assertEquals("First name not copied", "Joe", actual.getInvestigator().getFirstName());
        assertEquals("Last name not copied", "Something", actual.getInvestigator().getLastName());
    }

    public void testResearchStaffReduced() throws Exception {
        // TODO
    }

    public void testMatchINDs() throws Exception {
        InvestigationalNewDrug expectedIND = new InvestigationalNewDrug();
        expectedIND.setId(32);
        expectedIND.setIndNumber(99);

        expect(investigationalNewDrugDao.findByIds(aryEq(new String[] { "9" }))).andReturn(
                        Arrays.asList(expectedIND));
        replayMocks();
        List<InvestigationalNewDrug> results = facade.matchINDs("9");
        verifyMocks();

        assertEquals("Wrong number of results", 1, results.size());
        InvestigationalNewDrug actual = results.get(0);
        assertEquals("IND number is not matching", (int) actual.getIndNumber(), 99);
        //
    }
    
    public void testOpenStudy(){
    	assertEquals("Inprogress", command.getDataEntryStatus());
    	
    	EasyMock.expect(studyRepository.merge(command.getStudy())).andReturn(command.getStudy());
		EasyMock.expect(studyDao.initialize(command.getStudy())).andReturn(command.getStudy());
		replayMocks();
    	assertEquals("Complete", facade.openStudy());
    	verifyMocks();
    	
    }
    
    public void testMatchSiteResearchStaff(){
    	
    	Organization organization = Fixtures.createOrganization("test");
    	organization.setId(new Integer(5));
    	
    	StudyOrganization studyOrg = Fixtures.createStudyCoordinatingCenter(organization);
    	studyOrg.setId(new Integer(5));
    
    	
    	ResearchStaff rs1 = Fixtures.createResearchStaff(organization, Arrays.asList(UserGroupType.caaers_admin), "Jank");
    	rs1.setId(1);
    	
    	SiteResearchStaff srs1 = rs1.getSiteResearchStaffs().get(0);
    	srs1.setId(1);
    	srs1.setGridId("89899");
    	
    	ResearchStaff rs2 = Fixtures.createResearchStaff(organization, Arrays.asList(UserGroupType.caaers_admin), "Sank");
    	rs2.setId(2);
    	SiteResearchStaff srs2 = rs2.getSiteResearchStaffs().get(0);
    	srs2.setId(2);
    	srs2.setGridId("9900e");
    	

    	List<SiteResearchStaff> srsList = Arrays.asList(srs1, srs2);

    	command.getStudy().getStudyOrganizations().add(studyOrg);
    	
    	String text = "abcd";
    	
    	expect(researchStaffRepository.getSiteResearchStaffBySubnames(aryEq(new String[] { "abcd" }), eq(5))).andReturn(srsList);
    	
    	replayMocks();
    	
    	assertFalse(srsList.get(0).getResearchStaff().getSiteResearchStaffs().isEmpty());
    	assertFalse(srsList.get(1).getResearchStaff().getSiteResearchStaffs().isEmpty());
    	
    	
    	List<SiteResearchStaff> reducedList = facade.matchSiteResearchStaff(text, 0);
    	
    	
    	assertFalse(srsList.get(0).getResearchStaff().getSiteResearchStaffs().isEmpty());
    	assertFalse(srsList.get(1).getResearchStaff().getSiteResearchStaffs().isEmpty());
    	
    	
    	assertNotNull(reducedList);
    	assertEquals(2, reducedList.size());
    	
    	assertEquals(new Integer(1), reducedList.get(0).getId());
    	assertEquals(new Integer(2), reducedList.get(1).getId());
    	
    	assertNull(reducedList.get(0).getGridId());
    	assertNull(reducedList.get(1).getGridId());
    	
    	assertNotNull(reducedList.get(0).getResearchStaff());
    	assertNotNull(reducedList.get(1).getResearchStaff());
    	
     	assertNull(reducedList.get(0).getOrganization());
    	assertNull(reducedList.get(1).getOrganization());
   
    	
    	assertTrue(reducedList.get(0).getResearchStaff().getSiteResearchStaffs().isEmpty());
    	assertTrue(reducedList.get(1).getResearchStaff().getSiteResearchStaffs().isEmpty());
    	
    	assertNull(reducedList.get(0).getResearchStaff().getNciIdentifier());
    	assertNull(reducedList.get(1).getResearchStaff().getNciIdentifier());
    	
    	assertNotNull(reducedList.get(0).getResearchStaff().getFirstName());
    	assertNotNull(reducedList.get(1).getResearchStaff().getFirstName());
    	
    	//verifyMocks();
    	
    	
    }
    
    //will test deleting study agent with wrong index
    public void testRemove_WithWrongIndex() throws Exception{
    	replayMocks();
    	AjaxOutput output = facade.remove("study.studyAgents", 5, "Study Agents");
    	assertTrue(output.getError());
    	assertEquals("Unable to delete. Attempted to delete beyond the end; 5 >= 0", output.getErrorMessage());
    	verifyMocks();
    }
    
    //will test deleting study agent with correct index
    public void testRemoveCorrectIndex() throws Exception{
    	replayMocks();
    	study.addStudyAgent(Fixtures.createStudyAgent("test"));
    	AjaxOutput output = facade.remove("study.studyAgents", 0, "Study Agents");
    	assertFalse(output.getError());
    	assertEquals(1, output.getChanges().size());
    	assertEquals(new Integer(0), output.getChanges().get(0).getOriginal());
    	assertEquals(null, output.getChanges().get(0).getCurrent());
    	verifyMocks();
    }
    
  //will test deleting study agent with correct index (hard delete)
    public void testRemove() throws Exception{
    	replayMocks();
    	study.addStudyAgent(Fixtures.createStudyAgent("test0"));
    	study.addStudyAgent(Fixtures.createStudyAgent("test1"));
    	study.addStudyAgent(Fixtures.createStudyAgent("test2"));
    	AjaxOutput output = facade.remove("study.studyAgents", 1, "Study Agents");
    	assertFalse(output.getError());
    	assertEquals(2, output.getChanges().size());
    	assertEquals(2, study.getStudyAgents().size());
    	assertEquals(new Integer(2), output.getChanges().get(1).getOriginal());
    	assertEquals(new Integer(1), output.getChanges().get(1).getCurrent());
    	assertEquals(new Integer(1), output.getChanges().get(0).getOriginal());
    	assertEquals(null, output.getChanges().get(0).getCurrent());
    	verifyMocks();
    }
    
}
