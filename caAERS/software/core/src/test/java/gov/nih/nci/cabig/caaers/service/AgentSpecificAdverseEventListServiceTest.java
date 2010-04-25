package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.Study;
import org.dbunit.operation.DatabaseOperation;

import java.util.List;

/**
 *
 * @author Ion C. Olaru
 *
 */
public class AgentSpecificAdverseEventListServiceTest extends CaaersDbTestCase {
    StudyDao studyDao;
    AgentDao agentDao;
    AgentSpecificTermDao agentSpecificTermDao;
    AgentSpecificAdverseEventListServiceImpl service;

    protected void setUp() throws Exception {
        super.setUp();
        studyDao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");
        agentDao = (AgentDao)getDeployedApplicationContext().getBean("agentDao");
        agentSpecificTermDao = (AgentSpecificTermDao)getDeployedApplicationContext().getBean("agentSpecificTermDao");

        service = new AgentSpecificAdverseEventListServiceImpl();
        service.setAgentDao(agentDao);
        service.setStudyDao(studyDao);
        service.setAgentSpecificTermDao(agentSpecificTermDao);
    }
    
    public void testNumberOfStudyAgents() {
        Study s = studyDao.getById(-2);
        assertEquals(2, s.getStudyAgents().size());
    }

    public void testNumberOfAgentTerms() {
        List l = service.getListByAgent(-990);
        assertEquals(5, l.size());
    }

    public void testNumberOfStudyExpectedAEs() {
        Study s = studyDao.getById(-2);
        assertEquals(2, s.getExpectedAECtcTerms().size());
    }

    public void testSynchronizeStudyExpectedAEsWithNotExistingTerm() {
        Study s = studyDao.getById(-2);
        assertEquals(2, s.getExpectedAECtcTerms().size());
        AgentSpecificTerm t = agentSpecificTermDao.getById(-4);
        service.synchronizeStudyWithAgentTerm(s, t);
        // studyDao.save(s);
        assertEquals(3, s.getExpectedAECtcTerms().size());
    }

    public void testSynchronizeStudyExpectedAEsWithExistingTerm() {
        Study s = studyDao.getById(-2);
        assertEquals(2, s.getExpectedAECtcTerms().size());
        AgentSpecificTerm t = agentSpecificTermDao.getById(-5);
        service.synchronizeStudyWithAgentTerm(s, t);
        // studyDao.save(s);
        assertEquals(2, s.getExpectedAECtcTerms().size());
    }

    public void testSynchronizeStudyExpectedAEsWithAgent() {
        Study s = studyDao.getById(-2);
        Agent a = agentDao.getById(-990);
        assertEquals(2, s.getExpectedAECtcTerms().size());
        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
        service.synchronizeStudyWithAgent(s, a);
        // studyDao.save(s);
        assertEquals(6, s.getExpectedAECtcTerms().size());
        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
    }

/*
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }
*/
}