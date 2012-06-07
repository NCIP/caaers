package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ion C. Olaru
 * 
 */
public class AgentSpecificAdverseEventListServiceImpl implements AgentSpecificAdverseEventListService {

    private AgentSpecificTermDao agentSpecificTermDao;


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
     * @param sa - StudySgent
     * @param action - if AgentSpecificTerm.EXPTECTED_AE_DELETED, synchronization is being done for a just deleted agent, 
     * 		if AgentSpecificTerm.EXPTECTED_AE_UPDATED, synchronization is being done to update expected profiled, otherwise for addition
     */
    public void synchronizeStudyWithAgent(StudyAgent sa, String action) {
    	Study s = sa.getStudy();
    	Agent a = sa.getAgent();
        if (a == null || s == null) return;
        List<AgentSpecificTerm> l = a.getAgentSpecificTerms();
        for (AgentSpecificTerm at : l) {
            synchronizeStudyWithAgentTerm(sa, at, action);
        }
    }

//    /**
//     * Synchronize this ASAE term with the Study Expected AE list
//     * @param s - Study
//     * @param at - Agent Specific AE Term
//     * 
//     * */
//    public void synchronizeStudyWithAgentTerm(StudyAgent sa, AgentSpecificTerm at) {
//        synchronizeStudyWithAgentTerm(sa, at, AgentSpecificTerm.EXPTECTED_AE_ADDED);
//    }
    
    
    /**
     * Synchronize this ASAE term with the Study Expected AE list
     * Get the term associated with the agent and add this to the list of
     * the Expected AE Terms of the Study when Study has this agent
     * @param at - Agent Specific AE Term
     * @partam deleted - if true, synchronization is being done for a just deleted ASAE term, otherwise for addition 
     *
     */
    public void synchronizeStudyWithAgentTerm(StudyAgent sa, AgentSpecificTerm at, String action) {
    	sa.syncTreatmentAssignmentAgentSpecificTerm(at, action);
    	Study s = sa.getStudy();
        if (at instanceof AgentSpecificCtcTerm) {
            CtcTerm t = ((AgentSpecificCtcTerm)at).getTerm();
            List<ExpectedAECtcTerm> l = s.getExpectedAECtcTerms();
            // System.out.println("size: " + l.size());
            for (ExpectedAECtcTerm aeT : l) {
                if (aeT.getCtcTerm().getTerm().equals(t.getTerm())) {
                    if (action.equals(AgentSpecificTerm.EXPTECTED_AE_DELETED) && !isTermInMultipleStudyAgents(sa, t)) {
                        l.remove(aeT);
                    }
                    return;
                }
            }

            if (action.equals(AgentSpecificTerm.EXPTECTED_AE_DELETED) || !at.isExpected()) return;
            
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
                    if (action.equals(AgentSpecificTerm.EXPTECTED_AE_DELETED)) l.remove(aeT);
                    return;
                }
            }

            if (action.equals(AgentSpecificTerm.EXPTECTED_AE_DELETED) || !at.isExpected()) return;

            ExpectedAEMeddraLowLevelTerm aeT = new ExpectedAEMeddraLowLevelTerm();
            aeT.setStudy(s);
            aeT.setLowLevelTerm(t);
            l.add(aeT);
        }
    }
    
    private boolean isTermInMultipleStudyAgents(StudyAgent givenStudyAgent, DomainObject term){
    	int count = 0;
    	for(StudyAgent sa : givenStudyAgent.getStudy().getStudyAgents()){
    		if(sa.getAgent() == null || sa.getRetiredIndicator() || sa.equals(givenStudyAgent)) continue;
    		for(AgentSpecificTerm ast : sa.getAgent().getAgentSpecificTerms()){
    			if(ast.getTerm().getId().equals(term.getId())){
    				count++;
    			}
    			if(count == 1) return true;
    		}
    	}
    	return false;
    }


    public AgentSpecificTermDao getAgentSpecificTermDao() {
        return agentSpecificTermDao;
    }

    public void setAgentSpecificTermDao(AgentSpecificTermDao agentSpecificTermDao) {
        this.agentSpecificTermDao = agentSpecificTermDao;
    }

}
