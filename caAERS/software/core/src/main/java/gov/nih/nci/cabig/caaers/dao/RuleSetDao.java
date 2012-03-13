package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.RuleSetQuery;
import gov.nih.nci.cabig.caaers.domain.RuleSet;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Will manage the lifecycle of RuleSet object
 */
@Transactional(readOnly = false)
public class RuleSetDao extends CaaersDao<RuleSet> {
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<RuleSet> domainClass() {
        return RuleSet.class;
    }
    
    public RuleSet getByBindURI(String bindURI){
        RuleSetQuery ruleSetQuery = new RuleSetQuery();
        ruleSetQuery.filterByRuleBindURI(bindURI);
        List<RuleSet> ruleSets = (List<RuleSet>) search(ruleSetQuery);
        if(!ruleSets.isEmpty()) return ruleSets.get(0);
        return null;
    }
    @Transactional(readOnly = false)
    public void deleteRuleSet(String bindURI){
        RuleSet ruleSet = getByBindURI(bindURI);
        if(ruleSet != null) getHibernateTemplate().delete(ruleSet);
    }
}
