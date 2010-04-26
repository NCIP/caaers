package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.util.List;

/**
 * 
 * @author Ion C. Olaru
 * 
 */
public class AgentSpecificAdverseEventListServiceImpl implements AgentSpecificAdverseEventListService {

    private AgentSpecificTermDao agentSpecificTermDao;
    private StudyDao studyDao;
    private AgentDao agentDao;

    public List<AgentSpecificTerm> getListByAgent(Integer agentID) {
        return agentSpecificTermDao.getByAgentID(agentID);
    }

    /*
    * Get all the terms associated with the agent and add these to the list of
    * the Expected AE Terms of the Study when Study has this agent
    *
    * */
    public void synchronizeStudyWithAgent(Study s, Agent a) {
        List<AgentSpecificTerm> l = getListByAgent(a.getId());
        for (AgentSpecificTerm at : l) {
            synchronizeStudyWithAgentTerm(s, at);
        }
    }

    public void synchronizeStudyWithAgentTerm(Study s, AgentSpecificTerm at) {
        synchronizeStudyWithAgentTerm(s, at, false);
    }
    
    /*
    * Get the term associated with the agent and add this to the list of
    * the Expected AE Terms of the Study when Study has this agent
    *
    * */
    public void synchronizeStudyWithAgentTerm(Study s, AgentSpecificTerm at, boolean deleted) {
        if (at instanceof AgentSpecificCtcTerm) {
            CtcTerm t = ((AgentSpecificCtcTerm)at).getTerm();
            List<ExpectedAECtcTerm> l = s.getExpectedAECtcTerms();
            for (ExpectedAECtcTerm aeT : l) {
                if (aeT.getCtcTerm().getTerm().equals(t.getTerm())) {
                    if (deleted) l.remove(aeT);
                    return;
                }
            }

            if (deleted) return;

            ExpectedAECtcTerm aeT = new ExpectedAECtcTerm();
            aeT.setStudy(s);
            aeT.setCtcTerm(t);
            l.add(aeT);
        } else {
            LowLevelTerm t = ((AgentSpecificMeddraLowLevelTerm)at).getTerm();
            List<ExpectedAEMeddraLowLevelTerm> l = s.getExpectedAEMeddraLowLevelTerms();
            for (ExpectedAEMeddraLowLevelTerm aeT : l) {
                if (aeT.getTerm().getFullName().equals(t.getFullName())) return;
            }

            ExpectedAEMeddraLowLevelTerm aeT = new ExpectedAEMeddraLowLevelTerm();
            aeT.setStudy(s);
            aeT.setLowLevelTerm(t);
            l.add(aeT);
        }
    }

    /*
    * Delete the Term from the studies having this agent.
    * 
    * */
    public void postDeleteAgentSpecificTerm(AgentSpecificTerm at) {
        
    }

    public AgentSpecificTermDao getAgentSpecificTermDao() {
        return agentSpecificTermDao;
    }

    public void setAgentSpecificTermDao(AgentSpecificTermDao agentSpecificTermDao) {
        this.agentSpecificTermDao = agentSpecificTermDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }
}
