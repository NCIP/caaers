package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.AgentRepository;
import gov.nih.nci.cabig.caaers.domain.repository.DeviceRepository;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.classextension.EasyMock;

public class SearchStudyAjaxFacadeTest extends DwrFacadeTestCase{
	
	private SearchStudyAjaxFacade facade ;
	private StudyRepository studyRepository;
    private StudyDao studyDao;
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
    private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;
    private ParticipantDao participantDao;
    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;
    private InvestigatorDao investigatorDao;
    private InvestigatorRepository investigatorRepository; 
    private ResearchStaffDao researchStaffDao;
    private ResearchStaffRepository researchStaffRepository;
    private AdverseEventDao adverseEventDao;
    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    private InvestigationalNewDrugDao investigationalNewDrugDao;
    private AgentRepository agentRepository;
    private DeviceRepository deviceRepository;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studyDao = registerDaoMockFor(StudyDao.class);
		participantDao = registerDaoMockFor(ParticipantDao.class);
		adverseEventDao = registerDaoMockFor(AdverseEventDao.class);
		expeditedAdverseEventReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		organizationDao = registerDaoMockFor(OrganizationDao.class);
		facade = new SearchStudyAjaxFacade(studyDao,participantDao,adverseEventDao,expeditedAdverseEventReportDao,organizationDao);
		
		investigationalNewDrugDao = registerDaoMockFor(InvestigationalNewDrugDao.class);
		facade.setInvestigationalNewDrugDao(investigationalNewDrugDao);
		organizationRepository = registerMockFor(OrganizationRepository.class);
		facade.setOrganizationRepository(organizationRepository);
		agentRepository = registerMockFor(AgentRepository.class);
		facade.setAgentRepository(agentRepository);
		deviceRepository = registerMockFor(DeviceRepository.class);
		facade.setDeviceRepository(deviceRepository);
	}
	
	public void testConstructor() throws Exception{
		assertNotNull(facade.getStudyDao());
		assertNotNull(facade.getParticipantDao());
		assertNotNull(facade.getAdverseEventDao());
		assertNotNull(facade.getExpeditedAdverseEventReportDao());
		assertNotNull(facade.getOrganizationDao());
	}
	
	public void testGetINDTable() throws Exception{
		Map map = new HashMap<String,String>();
		map.put("type1","text1");
		map.put("type2","text2");
		List<InvestigationalNewDrug> inds = new ArrayList<InvestigationalNewDrug>();
		InvestigationalNewDrug ind1 = new InvestigationalNewDrug();
		ind1.setHolderName("abc");
		ind1.setIndNumber(123);
		ind1.setStatus("Active");
		
		inds.add(ind1);
		
		EasyMock.expect(investigationalNewDrugDao.searchInvestigationalNewDrugs(map)).andReturn(inds);
		replayMocks();
		List<InvestigationalNewDrug> reducedInds= facade.getINDTable(new HashMap<String,Object>(), "type1,type2", "text1,text2", request);
		verifyMocks();
		
		assertNotNull(reducedInds);
		assertEquals(1,reducedInds.size());
		assertNull(reducedInds.get(0).getStatus());
		assertEquals("abc",reducedInds.get(0).getHolderName());
		assertEquals(new Integer(123),reducedInds.get(0).getIndNumber());
	}
	
	
	public void testGetOrganizationTable() throws Exception{
		List<Organization> orgs = new ArrayList<Organization>();
		LocalOrganization org = new LocalOrganization();
		org.setName("Duke");
		org.setNciInstituteCode("NC010");
		org.setId(1);
		org.setExternalId("external1");
		org.setRetiredIndicator(false);
		orgs.add(org);
		
		EasyMock.expect(organizationRepository.searchOrganization((OrganizationQuery) EasyMock.anyObject())).andReturn(orgs);
		replayMocks();
		List<Organization> results = facade.getOrganizationTable(new HashMap<String,Object>(), "name,nciInstituteCode", "Duke,NC010", request);
		verifyMocks();
		assertNotNull(results);
		assertEquals(1,results.size());
		assertEquals("external1",results.get(0).getExternalId());
		assertEquals("NC010",results.get(0).getNciInstituteCode());
	}
	
	public void testGetAgentsTable() throws Exception{
		List<Agent> agents = new ArrayList<Agent>();
		Agent agent = new Agent();
		agent.setDescription("description");
		agent.setDisplayName("Agent 1");
		agent.setNscNumber("1234");
		agents.add(agent);
		EasyMock.expect(agentRepository.getAgentsByNameAndNsc("agent1", "nscNumber", false)).andReturn(agents);
		replayMocks();
		List<Agent> results = facade.getAgentsTable(new HashMap<String,Object>(), "agent1", "nscNumber", request);
		verifyMocks();
		assertNotNull(results);
		assertEquals(1,results.size());
		assertEquals("1234",results.get(0).getNscNumber());
		assertNull(results.get(0).getDescription());
		assertFalse(results.get(0).getRetiredIndicator());
	}
	
	public void testGetDevices() throws Exception{
		List<Device> devices = new ArrayList<Device>();
		Device device = new Device();
		device.setCommonName("cn");
		device.setBrandName("bn");
		device.setType("type");
		devices.add(device);
		EasyMock.expect(deviceRepository.getByMatchText("cn", false)).andReturn(devices);
		replayMocks();
		List<Device> results = facade.getDevices(new HashMap<String,Object>(), "cn", request);
		verifyMocks();
		assertNotNull(results);
		assertEquals(1,results.size());
		assertEquals("cn",results.get(0).getCommonName());
		assertEquals("bn",results.get(0).getBrandName());
		assertFalse(results.get(0).getRetiredIndicator());
	}

}
