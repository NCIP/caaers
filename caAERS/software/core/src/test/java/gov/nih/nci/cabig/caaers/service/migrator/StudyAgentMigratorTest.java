/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import org.dbunit.operation.DatabaseOperation;

public class StudyAgentMigratorTest extends DaoTestCase {
	
	DomainObjectImportOutcome<Study> outcome;
	Study dest;
	private AgentDao agentDao;
	private StudyDao studyDao;
	private InvestigationalNewDrugDao investigationalNewDrugDao;
	StudyAgentMigrator migrator;

	@Override
	protected void setUp() throws Exception {
        super.setUp();
        migrator = (StudyAgentMigrator)applicationContext.getBean("studyAgentMigrator");
        agentDao = (AgentDao)applicationContext.getBean("agentDao");
        studyDao = (StudyDao)applicationContext.getBean("studyDao");
        outcome = new DomainObjectImportOutcome<Study>();
    }

    public void testMigrateAddAgentByNSC() {
        Study dbStudy = studyDao.getStudyDesignById(-2);
        assertEquals(2, agentDao.getAll().size());
        assertEquals(2, dbStudy.getStudyAgents().size());

        Study s = getStudy();
        s.getStudyAgents().get(0).getAgent().setNscNumber("nsc-01");

        migrator.migrate(s, dbStudy, outcome);

        assertEquals(2, agentDao.getAll().size());
        assertEquals(3, dbStudy.getStudyAgents().size());

    }

    public void testMigrateAddAgentByName() {
        Study dbStudy = studyDao.getStudyDesignById(-2);
        assertEquals(2, agentDao.getAll().size());
        assertEquals(2, dbStudy.getStudyAgents().size());

        Study s = getStudy();
        s.getStudyAgents().get(0).getAgent().setName("A-01");
        s.getStudyAgents().get(0).getAgent().setNscNumber("new nsc-01");

        migrator.migrate(s, dbStudy, outcome);

        assertEquals(3, agentDao.getAll().size());
        assertEquals(3, dbStudy.getStudyAgents().size());

    }

    public void testMigrateOneNewAgent() {
        Study dbStudy = studyDao.getStudyDesignById(-2);
        assertEquals(2, agentDao.getAll().size());
        assertEquals(2, dbStudy.getStudyAgents().size());

        Study s = getStudy();
        s.getStudyAgents().get(0).getAgent().setNscNumber("NEW AGENT");
        s.getStudyAgents().get(0).getAgent().setName("NEW NAME");

        migrator.migrate(s, dbStudy, outcome);

        assertEquals(3, agentDao.getAll().size());
        assertEquals(3, dbStudy.getStudyAgents().size());

    }

    public void testMigrateUpdateAgent() {
        Study dbStudy = studyDao.getStudyDesignById(-2);
        assertEquals(2, agentDao.getAll().size());
        assertEquals(2, dbStudy.getStudyAgents().size());

        Study s = getStudy();
        s.getStudyAgents().get(0).getAgent().setNscNumber("nsc-01");
        s.getStudyAgents().get(0).getAgent().setName("NEW NAME FOR NSC-01");

        migrator.migrate(s, dbStudy, outcome);

        assertEquals(2, agentDao.getAll().size());
        assertEquals(3, dbStudy.getStudyAgents().size());

    }

    private Study getStudy() {
        Study s = new LocalStudy();
        s.addStudyAgent(new StudyAgent());
        Agent a = new Agent();
        s.getStudyAgents().get(0).setAgent(a);
        return s;
    }

/*
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		migrator = new StudyAgentMigrator();
		agentDao = registerDaoMockFor(AgentDao.class);
		investigationalNewDrugDao = registerDaoMockFor(InvestigationalNewDrugDao.class);
		migrator.setAgentDao(agentDao);
		migrator.setInvestigationalNewDrugDao(investigationalNewDrugDao);
		outcome = new  DomainObjectImportOutcome<Study>();
		dest = new LocalStudy();
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
		assertEquals(0, outcome.getMessages().size());
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
	
	
	public void testMigrate_OtherIND(){
		StudyAgent sa = Fixtures.createStudyAgent("abcd");
		sa.setIndType(INDType.OTHER);
		
		List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
		studyAgents.add(sa);
		Agent agent = Fixtures.createAgent("abcd", studyAgents);
		xstreamStudy.addStudyAgent(sa);
		
		EasyMock.expect(agentDao.getByName("abcd")).andReturn(agent);
		replayMocks();
		migrator.migrate(xstreamStudy, dest, outcome);
		verifyMocks();
		assertTrue(outcome.getMessages().isEmpty());
	}

*/

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }
}
