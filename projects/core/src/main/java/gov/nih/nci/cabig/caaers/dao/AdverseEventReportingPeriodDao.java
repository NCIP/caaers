package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import org.springframework.transaction.annotation.Transactional;


/**
 * This class implements the Data access related operations for the AdverseEventReportingPeriod domain
 * object.
 * 
 * @author Sameer Sawant
 */

@Transactional(readOnly = true)
public class AdverseEventReportingPeriodDao extends GridIdentifiableDao<AdverseEventReportingPeriod>
                implements MutableDomainObjectDao<AdverseEventReportingPeriod>{
	
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
     * @param The
     *                adverse event reporting period.
     */
    @Transactional(readOnly = false)
    public void save(final AdverseEventReportingPeriod reportingPeriod) {
        getHibernateTemplate().saveOrUpdate(reportingPeriod);
        //for (AdverseEvent ae : reportingPeriod.getAdverseEvents()) {
        //    getHibernateTemplate().saveOrUpdate(ae);
        //}
    }
}