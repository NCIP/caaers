/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    /**
     * Get the list of Terms associated with the given agent
     * @param agentID - ID of the agent to get the terms for
     * @return List
     * 
     * */
    public List<AgentSpecificTerm> getListByAgent(Integer agentID) {
        return agentSpecificTermDao.getByAgentID(agentID);
    }

    /**
     * Synchronize Expected AE terms list from the study with the expected AE Terms list associated with the agent
     * Get all the terms associated with the agent and add these to the list of
     * the Expected AE Terms of the Study when Study has this agent
     * @param s - Study
     * @param a - Agent
     * @param deleted - if true, synchronization is being done for a just deleted agent, otherwise for addition
     */
    public void synchronizeStudyWithAgent(Study s, Agent a, boolean deleted) {
        if (a == null || s == null) return;
        List<AgentSpecificTerm> l = getListByAgent(a.getId());
        for (AgentSpecificTerm at : l) {
            synchronizeStudyWithAgentTerm(s, at, deleted);
        }
    }

    /**
     * Synchronize Expected AE terms list from the study with the expected AE Terms list associated with the agent
     * Get all the terms associated with the agent and add these to the list of
     * the Expected AE Terms of the Study when Study has this agent
     * @param s - Study
     * @param a - Agent
     */
    public void synchronizeStudyWithAgent(Study s, Agent a) {
        synchronizeStudyWithAgent(s, a, false);
    }

    /**
     * Synchronize this ASAE term with the Study Expected AE list
     * @param s - Study
     * @param at - Agent Specific AE Term
     * 
     * */
    public void synchronizeStudyWithAgentTerm(Study s, AgentSpecificTerm at) {
        synchronizeStudyWithAgentTerm(s, at, false);
    }
    
    /**
     * Synchronize this ASAE term with the Study Expected AE list
     * Get the term associated with the agent and add this to the list of
     * the Expected AE Terms of the Study when Study has this agent
     * @param s - Study
     * @param at - Agent Specific AE Term
     * @partam deleted - if true, synchronization is being done for a just deleted ASAE term, otherwise for addition 
     *
     */
    public void synchronizeStudyWithAgentTerm(Study s, AgentSpecificTerm at, boolean deleted) {
        if (at instanceof AgentSpecificCtcTerm) {
            CtcTerm t = ((AgentSpecificCtcTerm)at).getTerm();
            List<ExpectedAECtcTerm> l = s.getExpectedAECtcTerms();
            // System.out.println("size: " + l.size());
            for (ExpectedAECtcTerm aeT : l) {
                if (aeT.getCtcTerm().getTerm().equals(t.getTerm())) {
                    if (deleted) {
                        l.remove(aeT);
                    }
                    return;
                }
            }

            if (deleted) return;

            ExpectedAECtcTerm aeT = new ExpectedAECtcTerm();
            aeT.setStudy(s);
            aeT.setCtcTerm(t);
            aeT.setOtherToxicity(at.getOtherToxicity());
            if (t.isOtherRequired()) {
                aeT.setOtherMeddraTerm(((AgentSpecificCtcTerm)at).getOtherMeddraTerm());
            }
            l.add(aeT);
        } else {
            LowLevelTerm t = ((AgentSpecificMeddraLowLevelTerm)at).getTerm();
            List<ExpectedAEMeddraLowLevelTerm> l = s.getExpectedAEMeddraLowLevelTerms();
            for (ExpectedAEMeddraLowLevelTerm aeT : l) {
                if (aeT.getTerm().getFullName().equals(t.getFullName())) {
                    if (deleted) l.remove(aeT);
                    return;
                }
            }

            if (deleted) return;

            ExpectedAEMeddraLowLevelTerm aeT = new ExpectedAEMeddraLowLevelTerm();
            aeT.setStudy(s);
            aeT.setLowLevelTerm(t);
            l.add(aeT);
        }
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
