package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.INDType;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

public class StudyAgentMigratorTest extends AbstractTestCase {
	
	Study xstreamStudy;
	DomainObjectImportOutcome<Study> outcome;
	Study dest;
	private AgentDao agentDao;
	private InvestigationalNewDrugDao investigationalNewDrugDao;
	StudyAgentMigrator migrator;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		migrator = new StudyAgentMigrator();
		agentDao = registerDaoMockFor(AgentDao.class);
		investigationalNewDrugDao = registerDaoMockFor(InvestigationalNewDrugDao.class);
		migrator.setAgentDao(agentDao);
		migrator.setInvestigationalNewDrugDao(investigationalNewDrugDao);
		outcome = new  DomainObjectImportOutcome<Study>();
		dest = new Study();
		xstreamStudy = Fixtures.createStudy("abcd");
		
	}
	
	public void testMigrate() {
		StudyAgent sa = Fixtures.createStudyAgent("abcd");
		sa.setIndType(INDType.NA);
		
		List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
		studyAgents.add(sa);
		Agent agent = Fixtures.createAgent("abcd", studyAgents);
		xstreamStudy.addStudyAgent(sa);
		
		EasyMock.expect(agentDao.getByName("abcd")).andReturn(agent);
		replayMocks();
		
		migrator.migrate(xstreamStudy, dest, outcome);
		verifyMocks();
		
		assertEquals("Incorrect number of study agents", 1,dest.getStudyAgents().size());
		assertTrue(outcome.getMessages().isEmpty());
	}
	
	public void testMigrate_CtepIND() {
		StudyAgent sa = Fixtures.createStudyAgent("abcd");
		sa.setIndType(INDType.CTEP_IND);
		sa.setPartOfLeadIND(true);
		
		List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
		studyAgents.add(sa);
		Agent agent = Fixtures.createAgent("abcd", studyAgents);
		xstreamStudy.addStudyAgent(sa);
		
		//for easymock
		InvestigationalNewDrug ind = new InvestigationalNewDrug();
		ind.setHolderName("kkkk");
		List<InvestigationalNewDrug> indList = new ArrayList<InvestigationalNewDrug>();
		indList.add(ind);
		
		EasyMock.expect(agentDao.getByName("abcd")).andReturn(agent);
		EasyMock.expect(investigationalNewDrugDao.findByIds((String[])EasyMock.anyObject())).andReturn(indList);
		
		replayMocks();
		
		migrator.migrate(xstreamStudy, dest, outcome);
		verifyMocks();
		System.out.println(outcome.getMessages());
		assertEquals("Incorrect number of study agents", 1,dest.getStudyAgents().size());
		assertTrue(outcome.getMessages().isEmpty());
	}
	
	public void testMigrate_CtepINDButNotPartOfLeadInd() {
		StudyAgent sa = Fixtures.createStudyAgent("abcd");
		sa.setIndType(INDType.CTEP_IND);
		
		List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
		studyAgents.add(sa);
		Agent agent = Fixtures.createAgent("abcd", studyAgents);
		xstreamStudy.addStudyAgent(sa);
		
		//for easymock
		InvestigationalNewDrug ind = new InvestigationalNewDrug();
		ind.setHolderName("kkkk");
		List<InvestigationalNewDrug> indList = new ArrayList<InvestigationalNewDrug>();
		indList.add(ind);
		
		EasyMock.expect(agentDao.getByName("abcd")).andReturn(agent);
		EasyMock.expect(investigationalNewDrugDao.findByIds((String[])EasyMock.anyObject())).andReturn(indList);
		
		replayMocks();
		
		migrator.migrate(xstreamStudy, dest, outcome);
		verifyMocks();
		System.out.println(outcome.getMessages());
		assertEquals(1, outcome.getMessages().size());
	}
	
	public void testMigrate_DcpIND() {
		StudyAgent sa = Fixtures.createStudyAgent("abcd");
		sa.setIndType(INDType.DCP_IND);
		sa.setPartOfLeadIND(true);
		
		List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
		studyAgents.add(sa);
		Agent agent = Fixtures.createAgent("abcd", studyAgents);
		xstreamStudy.addStudyAgent(sa);
		
		//for easymock
		InvestigationalNewDrug ind = new InvestigationalNewDrug();
		ind.setHolderName("kkkk");
		List<InvestigationalNewDrug> indList = new ArrayList<InvestigationalNewDrug>();
		indList.add(ind);
		
		EasyMock.expect(agentDao.getByName("abcd")).andReturn(agent);
		EasyMock.expect(investigationalNewDrugDao.findByIds((String[])EasyMock.anyObject())).andReturn(indList);
		
		replayMocks();
		
		migrator.migrate(xstreamStudy, dest, outcome);
		verifyMocks();
		System.out.println(outcome.getMessages());
		assertEquals("Incorrect number of study agents", 1,dest.getStudyAgents().size());
		assertTrue(outcome.getMessages().isEmpty());
	}	
	

}
