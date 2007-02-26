package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Agent;



import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * @author Krikor Krumlian
 */
public class AgentDao extends GridIdentifiableDao<Agent> {
    
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays
			.asList("name", "nscNumber");

	private static final List<String> EXACT_MATCH_PROPERTIES = Collections
			.emptyList();

	public Class<Agent> domainClass() {
        return Agent.class;
    }

    public List<Agent> getAll() {
        return getHibernateTemplate().find("from Agent");
    }    
    
    public void save(Agent agent) {
        getHibernateTemplate().saveOrUpdate(agent);
    }
    
    public List<Agent> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
    
    public Agent getByName(String name) {
        List<Agent> results = getHibernateTemplate().find("from Agent where name= ?", name);
        return results.get(0);
    }
    
}
