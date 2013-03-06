/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.ExternalAdverseEventReportingPeriodQuery;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEventReportingPeriod;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the External AdverseEvent Reporting Period
 *  domain object.
 * 
 * @author Ramakrishna
 */
@Transactional(readOnly = true)
public class ExternalAdverseEventReportingPeriodDao extends CaaersDao<ExternalAdverseEventReportingPeriod> {
	
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<ExternalAdverseEventReportingPeriod> domainClass() {
        return ExternalAdverseEventReportingPeriod.class;
    }

    /**
     * Save the Adverse Event.
     * 
     * @param event
     *                The event to be saved.
     */
    @Transactional(readOnly = false)
    public void save(final ExternalAdverseEventReportingPeriod externalAdverseEventReportingPeriod) {
        getHibernateTemplate().saveOrUpdate(externalAdverseEventReportingPeriod);
    }
    
    public List<ExternalAdverseEventReportingPeriod> searchExternalAEReportingPeriods(final 
    		ExternalAdverseEventReportingPeriodQuery query) {
        String queryString = query.getQueryString();
        log.debug(">>> " + queryString.toString());
        return (List<ExternalAdverseEventReportingPeriod>) super.search(query);
    }

}
