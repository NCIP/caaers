package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Investigator;

/**
 * @author Kulasekaran
 * 
 */
public class InvestigatorDao extends GridIdentifiableDao<Investigator> {
    
    @Override
    public Class<Investigator> domainClass() {
        return Investigator.class;
    }
    
    public void save(Investigator investigator) {
        getHibernateTemplate().saveOrUpdate(investigator);                        
    }     
}