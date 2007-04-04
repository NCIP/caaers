package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventReportDao extends CaaersDao<AdverseEventReport> {
    public Class<AdverseEventReport> domainClass() {
        return AdverseEventReport.class;
    }

    public void save(AdverseEventReport report) {
        getHibernateTemplate().saveOrUpdate(report);
        for (AdverseEvent ae : report.getAdverseEvents()) {
            getHibernateTemplate().saveOrUpdate(ae);
        }
    }
}
