package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogDetailQuery;
import gov.nih.nci.cabig.caaers.domain.IntegrationLogDetail;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the IntegrationLogDetail domain object.
 *
 * @author Ramakrishna
 */
@Transactional(readOnly = true)
public class IntegrationLogDetailDao extends GridIdentifiableDao<IntegrationLogDetail> implements MutableDomainObjectDao<IntegrationLogDetail> {

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     *
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<IntegrationLogDetail> domainClass() {
        return IntegrationLogDetail.class;
    }

    /**
     * Save or update the integration log details in the db.
     *
     * @param The integrationLog.
     */
    @Transactional(readOnly = false)
    public void save(final IntegrationLogDetail integrationLog) {
        getHibernateTemplate().saveOrUpdate(integrationLog);
    }

    /**
     * Search for integration log details using query.
     *
     * @param query The query used to search for integration log details
     * @return The list of integration logs.
     */
    @SuppressWarnings("unchecked")
    public List<IntegrationLogDetail> searchIntegrationLogDetail(final IntegrationLogDetailQuery query, int firstrow, int maxrows) {
        String queryString = query.getQueryString();
        log.debug(">>> " + queryString.toString());
        return (List<IntegrationLogDetail>) super.search(query, firstrow, maxrows);
    }
    
    public List<IntegrationLogDetail> searchIntegrationLogDetail(final IntegrationLogDetailQuery query) {
        String queryString = query.getQueryString();
        log.debug(">>> " + queryString.toString());
        return (List<IntegrationLogDetail>) super.search(query);
    }
    
    public List<IntegrationLogDetail> searchIntegrationDetailLogsInLastGivenNumberOfDays(int days){
    	Date today = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(today);
    	cal.set(Calendar.DATE, cal.get(Calendar.DATE)-days);
    	IntegrationLogDetailQuery query = new IntegrationLogDetailQuery();
    	query.filterByLoggedOn(cal.getTime(), ">");
    	return searchIntegrationLogDetail(query);
    }
    
    public List<IntegrationLogDetail> searchIntegrationDetailLogsForFailed(){
    	IntegrationLogDetailQuery query = new IntegrationLogDetailQuery();
    	query.filterByOutcome("failure");
    	return searchIntegrationLogDetail(query);
    }
    
    public List<IntegrationLogDetail> searchIntegrationDetailLogsForDateRange(Date startDate, Date endDate){
    	IntegrationLogDetailQuery query = new IntegrationLogDetailQuery();
    	query.filterByLoggedOnStartDateAndEndDate(startDate, endDate);
    	return searchIntegrationLogDetail(query);
    }
}
