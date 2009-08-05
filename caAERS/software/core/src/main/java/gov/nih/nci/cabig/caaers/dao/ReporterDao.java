package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Reporter;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the Reporter domain object.
 * 
 * @author Kulasekaran
 */
@Transactional(readOnly = true)
public class ReporterDao extends GridIdentifiableDao<Reporter> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<Reporter> domainClass() {
        return Reporter.class;
    }

    /**
     * Save or update the reporter in the db.
     * 
     * @param The
     *                reporter.
     */
    @Transactional(readOnly = false)
    public void save(Reporter reporter) {
        getHibernateTemplate().saveOrUpdate(reporter);
    }
}
