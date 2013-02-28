/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.UpdatedExternalAdverseEventsStatusQuery;
import gov.nih.nci.cabig.caaers.domain.ExternalAEReviewStatus;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEvent;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the External AdverseEvent domain object.
 * 
 * @author Ramakrishna Gundala
 */
@Transactional(readOnly = true)
public class ExternalAdverseEventDao extends CaaersDao<ExternalAdverseEvent> {
	

    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<ExternalAdverseEvent> domainClass() {
        return ExternalAdverseEvent.class;
    }

    /**
     * Save the Adverse Event.
     * 
     * @param externalAdverseEvent
     *                The event to be saved.
     */
    @Transactional(readOnly = false)
    public void save(final ExternalAdverseEvent externalAdverseEvent) {
        getHibernateTemplate().saveOrUpdate(externalAdverseEvent);
    }
    
    @Transactional(readOnly = false)
    public void updateStatus(ExternalAEReviewStatus oldStatus,ExternalAEReviewStatus newStatus, List<String> externalIds, Integer domainReportingPeriodId) {
    	UpdatedExternalAdverseEventsStatusQuery updateQuery = new UpdatedExternalAdverseEventsStatusQuery(UpdatedExternalAdverseEventsStatusQuery.UPDATE_AE_STATUS_HQL);

    	Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(updateQuery.getQueryString());
        query.setParameter("newStatus", newStatus);
        query.setParameter("oldStatus", oldStatus);
        query.setParameter("domainReportingPeriodId", domainReportingPeriodId);
        query.setParameterList("externalIds", externalIds);
       	query.executeUpdate();
    }

}
