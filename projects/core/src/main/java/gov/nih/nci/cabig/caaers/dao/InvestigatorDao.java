package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kulasekaran
 * 
 */
@Transactional(readOnly=true)
public class InvestigatorDao extends GridIdentifiableDao<Investigator> {
    public Class<Investigator> domainClass() {
        return Investigator.class;
    }
    
    @Transactional(readOnly=false)
    public void save(Investigator investigator) {
        getHibernateTemplate().saveOrUpdate(investigator);                        
    }     
}