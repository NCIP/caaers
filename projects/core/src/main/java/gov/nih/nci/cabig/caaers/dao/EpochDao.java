package gov.nih.nci.cabig.caaers.dao;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ion C. Olaru
 *
 */

@Transactional(readOnly = true)
public class EpochDao extends GridIdentifiableDao<Epoch> implements MutableDomainObjectDao<Epoch> {
	
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<Epoch> domainClass() {
        return Epoch.class;
    }
    
    /**
     * Save or update the epochs in the db.
     * @param epoch
     */
    @Transactional(readOnly = false)
    public void save(final Epoch epoch) {
        getHibernateTemplate().saveOrUpdate(epoch);
    }

    /**
     * @param id The id of the Epoch we are counting teh children Reporting Periods for.
     */
    public int getCountReportingPeriodsByEpochId(int id) {
        return getHibernateTemplate().find("from AdverseEventReportingPeriod aerp where aerp.epoch.id = ?", id).size();        
    }
}