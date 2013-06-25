/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
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
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<AdverseEventReportingPeriod> domainClass() {
        return AdverseEventReportingPeriod.class;
    }
    
    /**
     * Save or update the adverse event reporting periods in the db.
     * 
     * @param reportingPeriod - An adverse event reporting period.
     */
    @Transactional(readOnly = false)
    public void save(final AdverseEventReportingPeriod reportingPeriod) {
        getHibernateTemplate().saveOrUpdate(reportingPeriod);
      
    }
    
    /**
     * Save or update the adverse event reporting periods in the db.
     * 
     * @param reportingPeriod - An adverse event reporting period.
     */
    @Transactional(readOnly = false)
    public void modifyOrSaveReviewStatusAndComments(final AdverseEventReportingPeriod reportingPeriod) {
        getHibernateTemplate().saveOrUpdate(reportingPeriod);
      
    }


    /**
     * Gets the list of AdverseEventReportingPeriods based on the Assignment.
     * @param assignment - A StudyParticipantAssignment
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<AdverseEventReportingPeriod> getByAssignment(StudyParticipantAssignment assignment) {
        List<AdverseEventReportingPeriod> results = getHibernateTemplate().find("from AdverseEventReportingPeriod where assignment_id= ? order by start_date desc", assignment.getId());
        return results;
    }
    
    
    /**
     * Gets the list of AdverseEventReportingPeriods based on the external id, study identifier and study subject identifier.
     * @param assignment - A StudyParticipantAssignment
     * @return
     */
    @SuppressWarnings("unchecked")
	public AdverseEventReportingPeriod getByExternalId(String externalId) {
        List<AdverseEventReportingPeriod> results = getHibernateTemplate().find("from AdverseEventReportingPeriod where external_id like ? order by start_date desc", externalId);
        if(results != null && !results.isEmpty()){
        	results.get(0).getAssignment().getId();
        	return results.get(0);
        }
        return null;
    }
    
    
    /**
     * Gets the list of AdverseEventReportingPeriods based on the external id, study identifier and study subject identifier.
     * @param assignment - A StudyParticipantAssignment
     * @return
     */
    @SuppressWarnings("unchecked")
	public AdverseEventReportingPeriod getByStudySubjectIdAndStudyIdAndExternalId(String externalId, String studySubjectId, String studyId) {
    	return CollectionUtils.firstElement((List<AdverseEventReportingPeriod>) getHibernateTemplate().find("Select aerp from AdverseEventReportingPeriod aerp, Identifier i where aerp.externalId like ? and " +
        		"aerp.assignment.studySubjectIdentifier = ? and i = any elements (aerp.assignment.studySite.study.identifiers) and i.value = ? " +
        		"and i.type like 'Protocol Authority Identifier'", new Object[]{externalId,studySubjectId,studyId}));
    }
    

    /**
     * Will reassociate an AdverseEventReportingPeriod object to the running Hibernate Session. 
     * @param o - an AdverseEventReportingPeriod
     */
    @Override
    public void reassociate(AdverseEventReportingPeriod o) {
    	getHibernateTemplate().lock(o, LockMode.NONE);
    }

    /**
     * Will execute the query, and returns the matched AdverseEventReportingPeriod objects. 
     * @param query  - AdverseEventReportingPeriod
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<AdverseEventReportingPeriod> findAdverseEventReportingPeriods(final AdverseEventReportingPeriodForReviewQuery query){
        return (List<AdverseEventReportingPeriod>) find(query);
    }

    /**
     * Will execute the query, and returns the matched AdverseEventReportingPeriod objects. 
     * @param query
     * @return
     */
    @SuppressWarnings("unchecked")
	public Object find(final AbstractQuery query){
         log.debug("::: " + query.getQueryString());
         return super.search(query);
    }

    /**
     * Checks if the given AdverseEvent is present in the DB. 
     * @param ae
     * @return
     */
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
