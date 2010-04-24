package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class implements the Data access related operations for the AgentTerm domain object.
 * @author Ion C. Olaru
 * 
 */
@Transactional(readOnly=true)
public class AgentSpecificTermDao extends GridIdentifiableDao<AgentSpecificTerm> {

    /**
     * 
     */
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<AgentSpecificTerm> domainClass() {
        return AgentSpecificTerm.class;
    }

    /**
     * Get the list of all agent terms.
     * @return return the list of agent terms.
     * 
     */
    public List<AgentSpecificTerm> getAll() {
        return getHibernateTemplate().find("from AgentSpecificTerm");
    }

    /**
     * Get all AgentTerms for a specific agent
     */
    public List<AgentSpecificTerm> getByAgentID(Integer id) {
        List<AgentSpecificTerm> results = getHibernateTemplate().find("from AgentSpecificTerm at where at.agent.id = ?", id);
        return results;
    }

    /**
     * Get all Meddra AgentTerms for a specific agent
     */
    public List<AgentSpecificMeddraLowLevelTerm> getMeddraTermsByAgentID(Integer id) {
        List<AgentSpecificMeddraLowLevelTerm> results = getHibernateTemplate().find("from AgentSpecificMeddraLowLevelTerm at where at.agent.id = ?", id);
        return results;
    }

    /**
     * Get all Meddra AgentTerms for a specific agent
     */
    public List<AgentSpecificMeddraLowLevelTerm> getMeddraTermsByAgentID(Integer id, int meddraVersion) {
        List<AgentSpecificMeddraLowLevelTerm> results = getHibernateTemplate().find("from AgentSpecificMeddraLowLevelTerm at where at.agent.id = ? and at.term.meddraVersion.id = ?", new Object[] {id, meddraVersion});
        return results;
    }

    /**
     * Get all CTC AgentTerms for a specific agent
     */
    public List<AgentSpecificCtcTerm> getCtcTermsByAgentID(Integer id) {
        List<AgentSpecificCtcTerm> results = getHibernateTemplate().find("from AgentSpecificCtcTerm at where at.agent.id = ?", id);
        return results;
    }

    /**
     * Get all CTC AgentTerms for a specific agent
     */
    public List<AgentSpecificCtcTerm> getCtcTermsByAgentID(Integer id, Integer ctcVersion) {
        List<AgentSpecificCtcTerm> results = getHibernateTemplate().find("from AgentSpecificCtcTerm at where at.agent.id = ? and at.term.category.ctc.id = ?", new Object[] {id, ctcVersion});
        return results;
    }

    /**
     * Get CTC AgentTerms by its Agent & Term
     */
    public List<AgentSpecificCtcTerm> getCtcTerm(Integer agentID, Integer termID) {
        List<AgentSpecificCtcTerm> results = getHibernateTemplate().find("from AgentSpecificCtcTerm at where at.agent.id = ? and at.term.id = ?", new Object[] {agentID, termID});
        return results;
    }

    @Override
    @Transactional(readOnly = false)
    public void save(AgentSpecificTerm o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    @Transactional(readOnly = false)
    public void delete(AgentSpecificTerm o) {
        getHibernateTemplate().delete(o);
    }
}