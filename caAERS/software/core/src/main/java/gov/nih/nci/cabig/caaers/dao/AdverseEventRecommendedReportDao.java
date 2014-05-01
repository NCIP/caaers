/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.AdverseEventRecommendedReportQuery;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventRecommendedReport;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
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
public class AdverseEventRecommendedReportDao extends GridIdentifiableDao<AdverseEventRecommendedReport> implements MutableDomainObjectDao<AdverseEventRecommendedReport> {

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     *
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<AdverseEventRecommendedReport> domainClass() {
        return AdverseEventRecommendedReport.class;
    }

    /**
     * Save or update the adverseEventRecommendedReport in the db.
     *
     * @param The adverseEventRecommendedReport.
     */
    @Transactional(readOnly = false)
    public void save(final AdverseEventRecommendedReport adverseEventRecommendedReport) {
        getHibernateTemplate().saveOrUpdate(adverseEventRecommendedReport);
    }

    /**
     * Search for integration logs using query.
     *
     * @param query The query used to search for integration logs
     * @return The list of integration logs.
     */
    @SuppressWarnings("unchecked")
    public List<AdverseEventRecommendedReportQuery> searchAdverseEventRecommendedReports(final AdverseEventRecommendedReportQuery query, int firstrow, int maxrows) {
        String queryString = query.getQueryString();
        log.debug(">>> " + queryString.toString());
        return (List<AdverseEventRecommendedReportQuery>) super.search(query, firstrow, maxrows);
    }
    
    public List<AdverseEventRecommendedReport> searchAdverseEventRecommendedReports(final AdverseEventRecommendedReportQuery query) {
        String queryString = query.getQueryString();
        log.debug(">>> " + queryString.toString());
        return (List<AdverseEventRecommendedReport>) super.search(query);
    }
    
    public List<AdverseEventRecommendedReport> searchAdverseEventRecommendedReportsByAdverseEvent(AdverseEvent adverseEvent){
    	AdverseEventRecommendedReportQuery query = new AdverseEventRecommendedReportQuery();
    	query.filterByAdverseEvent(adverseEvent.getId());
    	return searchAdverseEventRecommendedReports(query);
    }
    
    public List<AdverseEventRecommendedReport> searchAdverseEventRecommendedReportsByReportDefinition(ReportDefinition reportDefinition){
    	AdverseEventRecommendedReportQuery query = new AdverseEventRecommendedReportQuery();
    	query.filterByReportingDefinition(reportDefinition.getId());
    	return searchAdverseEventRecommendedReports(query);
    }
    
    public List<AdverseEventRecommendedReport> getAllUnreportedRecords(){
    	return getHibernateTemplate().find("from AdverseEventRecommendedReport aerr where aerr.aeReported = 'false'");
    }
    
    public List<ReportDefinition> getAllRecommendedReportsNotReported(){
    	List<ReportDefinition> rds = getHibernateTemplate().find("select distinct aerr.reportDefinition from AdverseEventRecommendedReport aerr where aerr.aeReported = 'false'");
    	if(rds != null) {
	    	for(ReportDefinition rd : rds){
	    		for(PlannedNotification pn:rd.getPlannedNotifications()){
	    			pn.getRecipients().size();
	    		}
	    	}
    	}
    	
    	return rds;
    }
    
    public List<AdverseEventRecommendedReport> getAllAdverseEventsGivenReportDefinition(ReportDefinition rd){
    	List<AdverseEventRecommendedReport> aeRecomReports = getHibernateTemplate().find("select distinct aerr from AdverseEventRecommendedReport aerr where aerr.reportDefinition.id = ? and " +
    			"aerr.aeReported = 'false'",new Object[]{rd.getId()});
    	if(aeRecomReports != null) {
	    	for(AdverseEventRecommendedReport aeRecomReport : aeRecomReports){
	    		aeRecomReport.getAdverseEvent().getReportingPeriod().getAssignment().getParticipant().getIdentifiers().size();
	    		aeRecomReport.getAdverseEvent().getReportingPeriod().getAssignment().getStudySite().getStudy().getIdentifiers().size();
	    	}
    	}
    	
    	return aeRecomReports;
    }
    
    @Transactional(readOnly = false)
    public void delete(AdverseEventRecommendedReport adverseEventRecommendedReport) {
        getHibernateTemplate().delete(adverseEventRecommendedReport);
    }

    
}
