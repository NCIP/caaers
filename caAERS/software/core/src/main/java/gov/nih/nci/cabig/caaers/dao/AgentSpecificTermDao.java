/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * This class implements the Data access related operations for the AgentTerm domain object.
 * @author Ion C. Olaru
 * 
 */
@Transactional(readOnly=true)
public class AgentSpecificTermDao extends GridIdentifiableDao<AgentSpecificTerm> {

    /**
     * The domain class this Dao represents in this case an AgentSpecificTerm
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
        List<AgentSpecificTerm> results = getHibernateTemplate().find("from AgentSpecificTerm  at where at.agent.id = ?", id);
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
     * @param id  - The db id of the agent. 
     * @return
     */
    public List<AgentSpecificCtcTerm> getCtcTermsByAgentID(Integer id) {
        List<AgentSpecificCtcTerm> results = getHibernateTemplate().find("from AgentSpecificCtcTerm at where at.agent.id = ?", id);
        return results;
    }

    /**
     * Get all CTC AgentTerms for a specific agent
     * @param id  - The DB id of the agent.
     * @param ctcVersion  - The CTC version. 
     * @return  - Will return an AgentSpecificCtcTerm. 
     */
    public List<AgentSpecificCtcTerm> getCtcTermsByAgentID(Integer id, Integer ctcVersion) {
        List<AgentSpecificCtcTerm> results = getHibernateTemplate().find("from AgentSpecificCtcTerm at where at.agent.id = ? and at.term.category.ctc.id = ?", new Object[] {id, ctcVersion});
        return results;
    }

    /**
     * Will find all the AgentSpecificCtcTerm objects associated to an Agent and a Term. 
     * @param agentID - The Agent db id.
     * @param termID  - The Term db id.
     * @see gov.nih.nci.cabig.caaers.domain.CtcTerm
     * @see gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm
     * @return
     */
    public List<AgentSpecificCtcTerm> getCtcTerm(Integer agentID, Integer termID) {
        List<AgentSpecificCtcTerm> results = getHibernateTemplate().find("from AgentSpecificCtcTerm at where at.agent.id = ? and at.term.id = ?", new Object[] {agentID, termID});
        return results;
    }

    /**
     * Will save the AgentSpecificTerm
     * @param o
     */
    @Override
    @Transactional(readOnly = false)
    public void save(AgentSpecificTerm o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    /**
     * Will delete the AgentSpecificCtcTerm
     * @param o
     */
    @Transactional(readOnly = false)
    public void delete(AgentSpecificTerm o) {
        getHibernateTemplate().delete(o);
    }

    /**
     * Will delete all the AgentSpecificTerm objects. 
     */
    @Transactional(readOnly = false)
    public void deleteAll() {
        final String HQL = "delete from AgentSpecificTerm";
        getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                Query hQuery = session.createQuery(HQL);
                hQuery.executeUpdate();
                return null;
            }
        });
    }
}
