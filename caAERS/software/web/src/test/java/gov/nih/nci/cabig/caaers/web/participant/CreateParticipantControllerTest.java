package gov.nih.nci.cabig.caaers.web.participant;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.dao.AbstractStudyDiseaseDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.dao.ChemoAgentDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.validation.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.participant.CreateParticipantController;
import gov.nih.nci.cabig.caaers.web.participant.CreateParticipantTab;
import gov.nih.nci.cabig.caaers.web.participant.ParticipantInputCommand;
import gov.nih.nci.cabig.caaers.web.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.StaticTabConfigurer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class CreateParticipantControllerTest extends WebTestCase{
	
	protected CreateParticipantController controller;
	private StudyDao studyDao;
    private OrganizationDao organizationDao;
    private StudySiteDao studySiteDao;
    private ParticipantDao participantDao;
    private ResearchStaffDao rsDao;
    private InvestigatorDao investigatorDao;
    
    private PriorTherapyDao priorTherapyDao;
    private AnatomicSiteDao anatomicSiteDao;
    private PreExistingConditionDao preExistingConditionDao;
    private AbstractStudyDiseaseDao abstractStudyDiseaseDao;
    private ChemoAgentDao chemoAgentDao;
    private AgentDao agentDao;
    private OrganizationRepository orgRepository;

    private ConfigProperty configProperty;
    private Errors errors;

    private Configuration configuration;
    protected ConfigPropertyRepositoryImpl configPropertyRepository;
    private Map<String, List<Lov>> map;
    protected WebControllerValidator webControllerValidator;
    protected CreateParticipantTab createParticipantTab;
    protected ParticipantInputCommand newParticipantCommand;
    
  
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		SecurityTestUtils.switchToSuperuser();
		configProperty = registerMockFor(ConfigProperty.class);
		studyDao = registerDaoMockFor(StudyDao.class);
		organizationDao = registerDaoMockFor(OrganizationDao.class);
		studySiteDao = registerDaoMockFor(StudySiteDao.class);
		participantDao = registerDaoMockFor(ParticipantDao.class);
		rsDao = registerDaoMockFor(ResearchStaffDao.class);
		investigatorDao = registerDaoMockFor(InvestigatorDao.class);
		
		priorTherapyDao = registerDaoMockFor(PriorTherapyDao.class);
		anatomicSiteDao = registerDaoMockFor(AnatomicSiteDao.class);
		preExistingConditionDao = registerDaoMockFor(PreExistingConditionDao.class);
		abstractStudyDiseaseDao = registerDaoMockFor(AbstractStudyDiseaseDao.class);
		chemoAgentDao = registerDaoMockFor(ChemoAgentDao.class);
		agentDao = registerDaoMockFor(AgentDao.class);
		orgRepository = registerMockFor(OrganizationRepository.class);

		StaticTabConfigurer tabConfigurer = new StaticTabConfigurer(studyDao, organizationDao, studySiteDao, participantDao, rsDao, investigatorDao,
				priorTherapyDao,anatomicSiteDao,preExistingConditionDao,abstractStudyDiseaseDao,chemoAgentDao,agentDao);
	    tabConfigurer.addBean("configurationProperty", configProperty);
	    
	    errors = registerMockFor(Errors.class);
	    createParticipantTab = new CreateParticipantTab(){
	    	@Override
	    	public void setOrganizationRepository(OrganizationRepository organizationRepository) {organizationRepository = orgRepository;};
			@Override
			public Map referenceData(Object command) {
				return new HashMap(){{put("Participant Details", "Participant Details");}};
			}
			
			@Override
			public Map createFieldGroups(ParticipantInputCommand command) {
				FieldValidator fv = FieldValidator.ALPHANUMERIC_VALIDATOR; 
				InputFieldGroup participantFieldGroup;
				participantFieldGroup = new DefaultInputFieldGroup("participant");
				participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.firstName", "First Name", FieldValidator.NOT_NULL_VALIDATOR, fv));
		        participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.lastName", "Last Name", FieldValidator.NOT_NULL_VALIDATOR, fv));
		        InputFieldGroupMap map = new InputFieldGroupMap();
		        map.addInputFieldGroup(participantFieldGroup);
		        return map;
			};
			
			@Override
			public ModelAndView postProcessAsynchronous(
					HttpServletRequest request, Object command, Errors error)
					throws Exception {
				ModelAndView mv = new ModelAndView("some_name");
				mv.getModel().put("ajax", "test");
				return mv;
			}
		};
		
		controller = new CreateParticipantController() {
			@Override
			public gov.nih.nci.cabig.ctms.web.tabs.FlowFactory<ParticipantInputCommand> getFlowFactory() {return
					new FlowFactory<ParticipantInputCommand>() {
						@Override
						public Flow<ParticipantInputCommand> createFlow(ParticipantInputCommand arg0) {
							Flow flow = new Flow("Create Participant Flow");
							flow.addTab(createParticipantTab);
							return flow;
						}
					};
			}
			
			@Override
			public Map referenceData(HttpServletRequest request,
					Object command, Errors errors, int page) throws Exception {
				Map refdata = super.referenceData(request, command, errors, page);
				
				 refdata.put("unidentifiedMode", true);
				 return refdata;
			}
			
		};

	    controller.setTabConfigurer(tabConfigurer);
	    
		/*map = registerMockFor(Map.class);
		
	    expect(configProperty.getMap()).andReturn(map).anyTimes();*/
	 //   expect(map.get(EasyMock.anyObject())).andReturn(new ArrayList<Lov>()).anyTimes();
		
		configuration = registerMockFor(Configuration.class);
		newParticipantCommand = registerMockFor(ParticipantInputCommand.class);
		
		controller.setConfiguration(configuration);
        controller.setOrganizationDao(organizationDao);
        controller.setStudySiteDao(studySiteDao);
        controller.setStudyDao(studyDao);
        controller.setParticipantDao(participantDao);
        controller.setConfigurationProperty(configProperty);
        controller.setPriorTherapyDao(priorTherapyDao);
        controller.setAnatomicSiteDao(anatomicSiteDao);
        controller.setPreExistingConditionDao(preExistingConditionDao);
        controller.setAbstractStudyDiseaseDao(abstractStudyDiseaseDao);
        controller.setChemoAgentDao(chemoAgentDao);
        controller.setAgentDao(agentDao);
	}
	
	public void testGet() throws Exception{
		Map<String,List<Lov>> configProperties = new HashMap<String,List<Lov>>();
		configProperties.put("participantIdentifiersType", Arrays.asList(new Lov[]{new Lov("Cooperative Group","Cooperative Group"),
				new Lov("Default","Default"), new Lov("MRN","MRN")}));
        expect(configuration.get(configuration.UNIDENTIFIED_MODE)).andReturn(true).anyTimes();
        expect(configProperty.getMap()).andReturn(configProperties);
  //      expect(orgRepository.getOrganizationsHavingStudySites()).andReturn(new ArrayList<Organization>());

        replayMocks();

        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("par/par_create_participant", mv.getViewName());
    //    assertNotNull(mv.getModel().get("fieldGroups"));
        assertNotNull(mv.getModel().get("command"));
        assertNotNull(session.getAttribute("gov.nih.nci.cabig.caaers.web.participant.CreateParticipantControllerTest$2.FORM.command"));
        assertTrue(session.getAttribute("gov.nih.nci.cabig.caaers.web.participant.CreateParticipantControllerTest$2.FORM.command") instanceof ParticipantInputCommand);

        verifyMocks();
	}
	
	public void testRefData() throws Exception{
		
		Map<Object,Object> refData = controller.referenceData(request, newParticipantCommand,errors,0);
    	assertTrue((Boolean)refData.get("unidentifiedMode"));
	}

}
