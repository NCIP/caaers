package gov.nih.nci.cabig.caaers.web.study;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.web.tabs.StaticTabConfigurer;
import org.easymock.classextension.EasyMock;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@CaaersUseCases( { CREATE_STUDY })
public class CreateStudyControllerTest extends WebTestCase {

    private CreateStudyController controller;
    private StudyCommand command;

    protected StudyDao studyDao;
    private OrganizationDao organizationDao;
    private AgentDao agentDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;
    private SiteResearchStaffDao siteResearchStaffDao;
    private CtcDao ctcDao;
    protected InvestigationalNewDrugDao investigationalNewDrugDao;
    private MeddraVersionDao meddraVersionDao;
    private ConditionDao conditionDao;
    private StudyRepository studyRepository;
    private LowLevelTermDao lowLevelTermDao;

    private Configuration configuration;
    protected ConfigPropertyRepositoryImpl configPropertyRepository;
    private ConfigProperty configProperty;
    private Map<String, List<Lov>> map;
    protected WebControllerValidator webControllerValidator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerDaoMockFor(StudyDao.class);
        investigationalNewDrugDao = registerDaoMockFor(InvestigationalNewDrugDao.class);
        configuration = registerMockFor(Configuration.class);
        configPropertyRepository = registerMockFor(ConfigPropertyRepositoryImpl.class);
        organizationDao = registerDaoMockFor(OrganizationDao.class);
        agentDao = registerDaoMockFor(AgentDao.class);
        siteInvestigatorDao = registerDaoMockFor(SiteInvestigatorDao.class);
        researchStaffDao = registerDaoMockFor(ResearchStaffDao.class);
        siteResearchStaffDao = registerDaoMockFor(SiteResearchStaffDao.class);
        ctcDao = registerDaoMockFor(CtcDao.class);
        meddraVersionDao = registerDaoMockFor(MeddraVersionDao.class);
        conditionDao = registerDaoMockFor(ConditionDao.class);
        studyRepository = registerMockFor(StudyRepository.class);
        lowLevelTermDao = registerDaoMockFor(LowLevelTermDao.class);
        configProperty = registerMockFor(ConfigProperty.class);
        map = registerMockFor(Map.class);
        webControllerValidator = registerMockFor(WebControllerValidator.class);

        //create the command
        command = new StudyCommand(studyDao, investigationalNewDrugDao);
        Study study = new LocalStudy();
        study.setDataEntryStatus(false);
        command.setStudy(study);
        command.setStudyRepository(studyRepository);
        
        controller = new CreateStudyController();
        controller.setConfiguration(configuration);
        controller.setConfigPropertyRepository(configPropertyRepository);
        controller.setOrganizationDao(organizationDao);
        controller.setAgentDao(agentDao);
        controller.setSiteInvestigatorDao(siteInvestigatorDao);
        controller.setSiteResearchStaffDao(siteResearchStaffDao);
        controller.setResearchStaffDao(researchStaffDao);
        controller.setCtcDao(ctcDao);
        controller.setMeddraVersionDao(meddraVersionDao);
        controller.setConditionDao(conditionDao);
        controller.setLowLevelTermDao(lowLevelTermDao);
        controller.setStudyRepository(studyRepository);
        controller.setStudyDao(studyDao);
        controller.setInvestigationalNewDrugDao(investigationalNewDrugDao);
        controller.setWebControllerValidator(webControllerValidator);



        StaticTabConfigurer tabConfigurer = new StaticTabConfigurer(ctcDao, organizationDao,
                        studyDao, agentDao, researchStaffDao, siteInvestigatorDao, meddraVersionDao);
        tabConfigurer.addBean("configurationProperty", configProperty);

        controller.setTabConfigurer(tabConfigurer);

        expect(configProperty.getMap()).andReturn(map).anyTimes();
        expect(map.get(EasyMock.anyObject())).andReturn(new ArrayList<Lov>()).anyTimes();

    }
    

    /*
     * Will test the first request to create flow.
     * - invoke form backing
     * - command should be kept in session.
     */
    public void testCreateFlow_GET() throws Exception{
       request.setMethod("GET");
       expect(ctcDao.getAll()).andReturn(new ArrayList<Ctc>()).anyTimes();
       expect(meddraVersionDao.getAll()).andReturn(new ArrayList<MeddraVersion>()).anyTimes();
       expect(configPropertyRepository.getByType(ConfigPropertyType.RESEARCH_STAFF_ROLE_TYPE)).andReturn(new ArrayList<gov.nih.nci.cabig.caaers.domain.ConfigProperty>());
       expect(configPropertyRepository.getByType(ConfigPropertyType.INVESTIGATOR_ROLE_TYPE)).andReturn(new ArrayList<gov.nih.nci.cabig.caaers.domain.ConfigProperty>());
       expect(configuration.get(Configuration.ENABLE_WORKFLOW)).andReturn(false);
       
       replayMocks();

       ModelAndView mv  = controller.handleRequest(request, response);
       assertEquals("study/study_details", mv.getViewName());
       assertNotNull(mv.getModel().get("fieldGroups"));
       assertNotNull(mv.getModel().get("command"));
       assertNotNull(session.getAttribute("gov.nih.nci.cabig.caaers.web.study.CreateStudyController.FORM.command"));
       assertTrue(session.getAttribute("gov.nih.nci.cabig.caaers.web.study.CreateStudyController.FORM.command") instanceof StudyCommand);

        verifyMocks();
    }

    /*
     * Will make a second request to the page
     *  - Current page is 'Therapies'
     *  - Target page is 'Therapies'
     *
     */
    public void testSaveInCreateFlow() throws Exception {

      Study newStudy = new LocalStudy();

      newStudy.addStudyOrganization(Fixtures.createStudyCoordinatingCenter(null));

      assertNull(session.getAttribute("gov.nih.nci.cabig.caaers.web.study.CreateStudyController.FORM.command"));
      session.setAttribute("gov.nih.nci.cabig.caaers.web.study.CreateStudyController.FORM.command", command);
      session.setAttribute("gov.nih.nci.cabig.caaers.web.study.CreateStudyController.PAGE.command", command);

      request.addParameter("_page", "1");
      request.setAttribute("_page", "1");

      request.addParameter("_target1","1");
      request.setAttribute("_target1","1");
      request.setMethod("POST");


      expect(studyRepository.merge(command.getStudy())).andReturn(newStudy);
      webControllerValidator.validate(EasyMock.eq(request), EasyMock.eq(command), (BindException)EasyMock.anyObject());
      EasyMock.expectLastCall().anyTimes();
      expect(studyDao.initialize(newStudy)).andReturn(newStudy);

      replayMocks();

      ModelAndView mv  = controller.handleRequest(request, response);
      assertSame(command, session.getAttribute("gov.nih.nci.cabig.caaers.web.study.CreateStudyController.FORM.command"));
      assertSame(newStudy, command.getStudy());
      assertEquals("study/study_therapies", mv.getViewName());
      assertEquals("Information saved successfully", mv.getModel().get("flashMessage"));

      verifyMocks();
        
    }



}
