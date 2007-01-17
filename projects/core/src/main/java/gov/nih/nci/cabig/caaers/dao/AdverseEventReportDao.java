package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventReportDao extends CaaersDao<AdverseEventReport> {
    public Class<AdverseEventReport> domainClass() {
        return AdverseEventReport.class;
    }

    public void save(AdverseEventReport report) {
        getHibernateTemplate().saveOrUpdate(report);
    }
}
