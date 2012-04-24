package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogQuery;
import gov.nih.nci.cabig.caaers.domain.IntegrationLog;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the IntegrationLog domain object.
 *
 * @author Ramakrishna
 */
@Transactional(readOnly = true)
public class IntegrationLogDao extends GridIdentifiableDao<IntegrationLog> implements MutableDomainObjectDao<IntegrationLog> {

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     *
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<IntegrationLog> domainClass() {
        return IntegrationLog.class;
    }

    /**
     * Save or update the integrationLog in the db.
     *
     * @param The integrationLog.
     */
    @Transactional(readOnly = false)
    public void save(final IntegrationLog integrationLog) {
        getHibernateTemplate().saveOrUpdate(integrationLog);
    }

    /**
     * Search for integration logs using query.
     *
     * @param query The query used to search for integration logs
     * @return The list of integration logs.
     */
    @SuppressWarnings("unchecked")
    public List<IntegrationLog> searchIntegrationLog(final IntegrationLogQuery query, int firstrow, int maxrows) {
        String queryString = query.getQueryString();
        log.debug(">>> " + queryString.toString());
        return (List<IntegrationLog>) super.search(query, firstrow, maxrows);
    }
    
    public List<IntegrationLog> searchIntegrationLog(final IntegrationLogQuery query) {
        String queryString = query.getQueryString();
        log.debug(">>> " + queryString.toString());
        return (List<IntegrationLog>) super.search(query);
    }
}
