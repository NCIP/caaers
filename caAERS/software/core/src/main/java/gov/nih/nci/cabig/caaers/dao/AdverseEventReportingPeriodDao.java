package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.AdverseEventExistQuery;
import gov.nih.nci.cabig.caaers.dao.query.AdverseEventReportingPeriodForReviewQuery;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class implements the Data access related operations for the AdverseEventReportingPeriod domain
 * object.
 * 
 * @author Sameer Sawant
 */

@Transactional(readOnly = true)
public class AdverseEventReportingPeriodDao extends GridIdentifiableDao<AdverseEventReportingPeriod> implements MutableDomainObjectDao<AdverseEventReportingPeriod>{
	
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<AdverseEventReportingPeriod> domainClass() {
        return AdverseEventReportingPeriod.class;
    }
    
    /**
     * Save or update the adverse event reporting periods in the db.
     * 
     * @param The adverse event reporting period.
     */
    @Transactional(readOnly = false)
    public void save(final AdverseEventReportingPeriod reportingPeriod) {
        getHibernateTemplate().saveOrUpdate(reportingPeriod);
      
    }
    
    /**
     * Save or update the adverse event reporting periods in the db.
     * 
     * @param The adverse event reporting period.
     */
    @Transactional(readOnly = false)
    public void modifyOrSaveReviewStatusAndComments(final AdverseEventReportingPeriod reportingPeriod) {
        getHibernateTemplate().saveOrUpdate(reportingPeriod);
      
    }
    
    
    /**
     * Get the list of AdverseEventReportingPeriods based on the Assignment.
     * This is needed to rightly update the dropdown on addition of a new reporting period.
     */
    @SuppressWarnings("unchecked")
	public List<AdverseEventReportingPeriod> getByAssignment(StudyParticipantAssignment assignment) {
        List<AdverseEventReportingPeriod> results = getHibernateTemplate().find("from AdverseEventReportingPeriod where assignment_id= ? order by start_date desc", assignment.getId());
        return results;
    }
    
    @Override
    public void reassociate(AdverseEventReportingPeriod o) {
    	getHibernateTemplate().lock(o, LockMode.NONE);
    }
    
    @SuppressWarnings("unchecked")
	public List<AdverseEventReportingPeriod> findAdverseEventReportingPeriods(final AdverseEventReportingPeriodForReviewQuery query){
        return (List<AdverseEventReportingPeriod>) find(query);
    }
    
    @SuppressWarnings("unchecked")
	public Object find(final AbstractQuery query){
         log.debug("::: " + query.getQueryString());
         return getHibernateTemplate().execute(new HibernateCallback(){
        	  public Object doInHibernate(final Session session) throws HibernateException,SQLException {
        		  org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
        		  Map<String, Object> queryParameterMap = query.getParameterMap();
        		  for (String key : queryParameterMap.keySet()) {
        			  Object value = queryParameterMap.get(key);
        			  hiberanteQuery.setParameter(key, value);

        		  }
        		  return hiberanteQuery.list();
        	  } 
         });
    }
    
    public boolean isAdverseEventPresent(AdverseEvent ae){
    	AdverseEventExistQuery query = new AdverseEventExistQuery();
    	query.filterByDifferentAdverseEventId(ae.getId());
    	query.filterByAdverseEventTerm(ae.getAdverseEventTerm());
    	query.filterByLowLevelTerm(ae.getLowLevelTerm());
    	query.filterByReportingPeriodId(ae.getReportingPeriod().getId());
    	List<AdverseEvent> aeList = (List<AdverseEvent>) find(query);
    	return (aeList != null && !aeList.isEmpty());
    }
}