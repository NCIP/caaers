package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEventReportingPeriod;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the AdverseEvent domain object.
 * 
 * @author Rhett Sutphin
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
    public void save(final AdverseEvent event) {
        getHibernateTemplate().saveOrUpdate(event);
    }

}
